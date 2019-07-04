package com.peitu.doorplateqrcode.entity;

import lombok.Data;

/**
 * @author Rising
 * @date 2019/6/11
 */
@Data
public class PicInfo {
    private Integer id;

    private String gmtCreate;

    private String gmtModified;

    private Integer doorplateId;

    private String picPath;

    private String state;

}