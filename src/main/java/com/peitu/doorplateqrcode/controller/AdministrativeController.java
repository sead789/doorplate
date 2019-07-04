package com.peitu.doorplateqrcode.controller;

import com.peitu.doorplateqrcode.service.api.AdministrativeService;
import org.apache.tomcat.util.descriptor.web.LoginConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Rising
 * @date 2019/6/12
 */
@Controller
@ResponseBody
public class AdministrativeController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginConfig.class);

    @Autowired
    AdministrativeService administrativeService;

    @RequestMapping("insert/{parentId}")
    public Object insert(@PathVariable int parentId) {
        return administrativeService.insert(parentId);
    }


}
