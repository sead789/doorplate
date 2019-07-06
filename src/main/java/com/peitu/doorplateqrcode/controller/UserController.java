package com.peitu.doorplateqrcode.controller;

import com.peitu.commons.cookies.CookiesUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.peitu.commons.redis.RedisUtils;
import com.peitu.commons.result.ResultEntity;
import com.peitu.doorplateqrcode.service.api.UserService;
import com.peitu.doorplateqrcode.vo.UserRequest;
import org.apache.tomcat.util.descriptor.web.LoginConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author Rising
 * @date 2019/6/13
 */
@RestController
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginConfig.class);

    @Autowired
    UserService userService;
    @Autowired
    RedisUtils redisUtils;

    /**
     * 登录请求
     *
     * @param request
     * @param req
     * @return
     */
    @PostMapping(value = "login")
    public Object login(@Valid UserRequest request, HttpServletRequest req) throws Exception {
        ResultEntity entity = (ResultEntity) userService.verify(request);
        //存储用户信息于redis中
//        if (entity.getData() != null){
//            ObjectMapper mapper = new ObjectMapper();
//            try {
//                String content = mapper.writeValueAsString(entity.getData());
//                String cookiesId = CookiesUtils.getCookie(req, "Cookie");
//                redisUtils.set(cookiesId, content);
//            } catch (Exception e) {
//                throw e;
//            }
//        }
        try {
            String.valueOf(null);
        } catch (Exception ex){
            throw ex;
        }
        return entity;
    }

}
