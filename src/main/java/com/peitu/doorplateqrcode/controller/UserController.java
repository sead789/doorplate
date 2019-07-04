package com.peitu.doorplateqrcode.controller;

import com.peitu.commons.cookies.CookiesUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.peitu.commons.redis.RedisUtils;
import com.peitu.commons.result.MsgUtil;
import com.peitu.commons.result.ResultCode;
import com.peitu.commons.result.ResultEntity;
import com.peitu.doorplateqrcode.service.api.UserService;
import com.peitu.doorplateqrcode.vo.UserRequest;
import org.apache.tomcat.util.descriptor.web.LoginConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Object login(UserRequest request, HttpServletRequest req) {
        if (StringUtils.isEmpty(request.getUserName())) {
            return MsgUtil.retMsg(ResultCode.用户名不能为空);
        } else if (StringUtils.isEmpty(request.getPassword())) {
            return MsgUtil.retMsg(ResultCode.密码不能为空);
        }
        ResponseEntity<ResultEntity> entity = (ResponseEntity<ResultEntity>) userService.verify(request);
        //存储用户信息于redis中
        if (entity.getBody().getData() != null){
            ObjectMapper mapper = new ObjectMapper();
            try {
                String content = mapper.writeValueAsString(entity.getBody().getData());
                String cookiesId = CookiesUtils.getCookie(req, "Cookie");
                redisUtils.set(cookiesId, content);
            } catch (JsonProcessingException e) {
                LOG.info("服务器错误");
                e.printStackTrace();
            }
        }
        return entity;
    }

}
