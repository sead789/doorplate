package com.peitu.doorplateqrcode.entity;

import lombok.Data;

/**
 * @author Rising
 * @date 2019/6/11
 */
@Data
public class User {
    private Integer id;

    private String gmtCreate;

    private String gmtModified;

    private String userName;

    private String password;

    private String showName;

}