package com.peitu.doorplateqrcode.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author Rising
 * @date 2019/6/13
 */
@Data
public class UserRequest {

    @NotBlank(message = "用户名不能为空")
    public String userName;

    @NotBlank(message = "密码不能为空")
    @Length(min = 6, message = "密码长度不能小于6")
    public String password;

    public Integer rx;

}
