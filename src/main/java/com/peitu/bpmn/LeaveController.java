package com.peitu.bpmn;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Rising
 * @date 2019/7/1
 */
@RestController
public class LeaveController {

    @Autowired
    ProcessEngine processEngine;

    /**
     * 1、部署流程
     * 2、启动流程实例
     * 3、请假人发出请假申请
     * 4、班主任查看任务
     * 5、班主任审批
     * 6、最终的教务处Boss审批
     */

    /**
     * 1：部署一个Activiti流程
     * 运行成功后，查看之前的数据库表，就会发现多了很多内容
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public void createActivitiTask() {
        //加载的那两个内容就是我们之前已经弄好的基础内容哦。
        //得到了流程引擎
        processEngine.getRepositoryService()
                .createDeployment()
                .addClasspathResource("processes/leave.bpmn")
                .addClasspathResource("processes/leave.png")
                .deploy();
    }

    /**
     * 2：启动流程实例
     */
    @RequestMapping(value = "start", method = RequestMethod.GET)
    public void startProcessInstance(String processDefinitionId) {
        //这个是查看数据库中act_re_procdef表
        processEngine.getRuntimeService().startProcessInstanceById(processDefinitionId);
    }

    /**
     * 完成请假申请
     */
    @RequestMapping(value = "leave", method = RequestMethod.GET)
    public void leave(String taskId) {
        //查看act_ru_task表
        processEngine.getTaskService().complete(taskId);
    }

    /**
     * 小明学习的班主任小毛查询当前正在执行任务
     */
    @RequestMapping(value = "check", method = RequestMethod.GET)
    public void queryTask() {
        //下面代码中的小毛，就是我们之前设计那个流程图中添加的班主任内容
        List<Task> tasks = processEngine.getTaskService()
                .createTaskQuery()
                .list();
        for (Task task : tasks) {
            System.out.println(task.getName());
        }
    }

    /**
     * 班主任小毛完成任务
     */
    @RequestMapping(value = "finish/leader", method = RequestMethod.GET)
    public void finishTask_leader(String taskId) {
        //查看act_ru_task数据表
        processEngine.getTaskService().complete(taskId);
    }

    /**
     * 教务处的大毛完成的任务
     */
    @RequestMapping(value = "finish/boss", method = RequestMethod.GET)
    public void finishTask_Boss(String taskId) {
        //查看act_ru_task数据表
        processEngine.getTaskService().complete(taskId);
    }

}
