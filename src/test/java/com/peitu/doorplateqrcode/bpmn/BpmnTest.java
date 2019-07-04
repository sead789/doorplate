package com.peitu.doorplateqrcode.bpmn;

import org.activiti.engine.*;
import org.activiti.engine.task.Task;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rising
 * @date 2019/6/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BpmnTest {

    public static void main(String[] args) {
        System.out.println("4564545");
    }

    ProcessEngine processEngine = null;
    RepositoryService repositoryService = null;
    RuntimeService runtimeService = null;
    TaskService taskService = null;

    public void init() {
        processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();
        repositoryService = processEngine.getRepositoryService();
        runtimeService = processEngine.getRuntimeService();
        taskService = processEngine.getTaskService();
    }

    /**
     * 部署流程定义
     */
    public void deploy() {
        repositoryService.createDeployment().addClasspathResource("processes/leave.bpmn").deploy();
        System.out.println("***************部署流程定义完成***************");
    }

    /**
     * 启动一个请假流程
     */
    public void start() {
        Map<String, Object> variables = new HashMap<String, Object>(3);
        //请假人
        variables.put("employeeId", "Kermit");
        String processId = runtimeService.startProcessInstanceByKey("leave", variables).getId();
        System.out.println("***************启动一个请假流程完成***************" + processId);
    }

    /**
     * 提交请假申请
     */
    public void apply() {
        System.out.println("***************提交请假申请开始***************");
        List<Task> tasks = taskService.createTaskQuery().taskAssignee("Kermit").list();
        System.out.println(tasks.size());
        for (Task task : tasks) {
            System.out.println("Kermit的任务taskname:" + task.getName() + ",id:" + task.getId());
            //设置请假天数
            taskService.setVariable(task.getId(), "day", 4);
            //完成任务
            taskService.complete(task.getId());
            System.out.println("请假4 天");
        }
        System.out.println("***************提交请假申请完成***************");
    }

    public void step2manager() {
        System.out.println("***************经理组审批流程开始***************");
        // 经理组的任务
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("manager").list();
        System.out.println("经理组的任务：" + tasks.size());
        for (Task task : tasks) {
            System.out.println("经理组的任务taskname:" + task.getName() + ",id:" + task.getId());
            //申领任务
            taskService.claim(task.getId(), "李四");
            //true批准，false不批准
            taskService.setVariable(task.getId(), "flag", false);
            Object applyUser = taskService.getVariable(task.getId(), "employeeId");
            Object day = taskService.getVariable(task.getId(), "day");
            System.out.println(String.format("%s请假%s天", applyUser, day));
            //完成任务
            taskService.complete(task.getId());
        }
        System.out.println("***************经理组审批流程结束***************");
    }

    public void step2Boss() {
        System.out.println("***************总经理组审批流程开始***************");
        // 总经理组的任务
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("boss").list();
        System.out.println("总经理组的任务：" + tasks.size());
        for (Task task : tasks) {
            System.out.println("manager的任务taskname:" + task.getName() + ",id:" + task.getId());
            //申领任务
            taskService.claim(task.getId(), "王五");
            //true批准,false不批准
            taskService.setVariable(task.getId(), "flag", true);
            Object applyUser = taskService.getVariable(task.getId(), "employeeId");
            Object day = taskService.getVariable(task.getId(), "day");
            System.out.println(String.format("%s请假%s天", applyUser, day));
            //完成任务
            taskService.complete(task.getId());
        }
        System.out.println("***************总经理组审批流程结束***************");
    }


}
