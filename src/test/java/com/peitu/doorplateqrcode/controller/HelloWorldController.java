package com.peitu.doorplateqrcode.controller;

import com.peitu.doorplateqrcode.entity.Test;
import com.peitu.doorplateqrcode.service.provider.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @author Rising
 * @date 2019/6/11
 */
@Controller
public class HelloWorldController {

    @Autowired
    TestServiceImpl testService;

    @RequestMapping("test6/{msg}")
    @ResponseBody
    public String test01(@PathVariable String msg) {
        Test test = new Test();
        test.setCode("2000");
        test.setMsg(msg);
        testService.insert(test);
        return "It's OK!";
    }

    public static void main(String[] args) throws IOException {
        test01();
    }

    public static void test01() throws IOException {
        String content = "{\n" +
                "      \"pinyin\": \"GuangZhou\",\n" +
                "      \"lng\": \"113.264434\",\n" +
                "      \"level\": 1,\n" +
                "      \"parent_id\": 483250,\n" +
                "      \"area_code\": \"440100000000\",\n" +
                "      \"name\": \"广州市\",\n" +
                "      \"merger_name\": \"广东,广州\",\n" +
                "      \"city_code\": \"020\",\n" +
                "      \"short_name\": \"广州\",\n" +
                "      \"id\": 483251,\n" +
                "      \"zip_code\": \"510000\",\n" +
                "      \"lat\": \"23.129162\"\n" +
                "    }";

//        ObjectMapper mapper = new ObjectMapper();
//        AdministrativeCode code = mapper.readValue(content, AdministrativeCode.class);
//        System.out.println(code);
//        Response res = mapper.readValue(content, Response.class);
//        for (AdministrativeCode code : res.getData()) {
//            if ("揭阳市".equals(code.getName())) {
//                System.out.println(code);
//                break;
//            }
//        }
    }

}
