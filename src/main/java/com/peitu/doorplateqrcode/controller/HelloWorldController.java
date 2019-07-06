package com.peitu.doorplateqrcode.controller;

import com.peitu.doorplateqrcode.entity.Test;
import com.peitu.doorplateqrcode.service.api.TestService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Rising
 * @date 2019/6/11
 */
@Controller
public class HelloWorldController {

    @Autowired
    TestService testService;

    @ApiOperation(value = "HelloWorld", notes = "测试-API")
    @GetMapping("hello")
    public String helloWorld() {
        try {
            System.out.println("hello-world");
            String.valueOf(null);
        } catch (Exception ex) {
            throw ex;
        }
        return "hello-world";
    }

    @ApiOperation(value = "一个测试API", notes = "第一个测试的api")
    @RequestMapping("test-api/{msg}")
    @ResponseBody
    public String test(@PathVariable String msg) {
        Test test = new Test();
        test.setCode("2000");
        test.setMsg(msg);
        testService.insert(test);
        return "It's OK!";
    }

    public static void main(String[] args) {

    }


}
