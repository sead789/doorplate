package com.peitu.doorplateqrcode.service.api;

import com.peitu.doorplateqrcode.vo.UserRequest;

/**
 * @author Rising
 * @date 2019/6/13
 */
public interface UserService {

    Object verify(UserRequest userRequest);

}
