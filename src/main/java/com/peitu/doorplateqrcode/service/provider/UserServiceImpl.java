package com.peitu.doorplateqrcode.service.provider;

import com.peitu.doorplateqrcode.entity.User;
import com.peitu.doorplateqrcode.mapper.UserMapper;
import com.peitu.commons.result.ResultUtil;
import com.peitu.commons.result.ResultCode;
import com.peitu.doorplateqrcode.service.api.UserService;
import com.peitu.doorplateqrcode.vo.UserRequest;
import org.apache.tomcat.util.descriptor.web.LoginConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Rising
 * @date 2019/6/13
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(LoginConfig.class);

    @Autowired
    UserMapper userMapper;

    @Override
    public Object verify(UserRequest userRequest) {
        User user = userMapper.selectByPrimaryKey(1);
        if (!user.getUserName().equals(userRequest.getUserName())) {
            return ResultUtil.setMsg(ResultCode.用户不存在);
        } else if (!user.getPassword().equals(userRequest.getPassword())) {
            return ResultUtil.setMsg(ResultCode.密码错误);
        } else {
            return ResultUtil.setMsg(user);
        }
    }
}
