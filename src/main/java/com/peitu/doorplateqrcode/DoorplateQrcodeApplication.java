package com.peitu.doorplateqrcode;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author Rising
 * @date 2019/6/20
 */
@SpringBootApplication(scanBasePackages = {
        "com.peitu.doorplateqrcode",
        "com.peitu.commons.redis",
        "com.peitu.commons.activiti",
        "com.peitu.swagger",
        "com.peitu.bpmn"
}, exclude = SecurityAutoConfiguration.class)
@MapperScan("com.peitu.doorplateqrcode.mapper")
@EnableCaching
public class DoorplateQrcodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoorplateQrcodeApplication.class, args);
    }


}
