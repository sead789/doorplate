package com.peitu.doorplateqrcode.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Rising
 * @date 2019/6/11
 */
@Data
public class AdministrativeCode {

    /*** 区域ID*/
    public Integer id;

    public String pinyin;

    /*** 纬度*/
    public String lng;

    /*** 行政区域等级*/
    public int level;

    @JsonProperty("parent_id")
    public int parentId;

    /*** 行政区域代码*/
    @JsonProperty("area_code")
    public String areaCode;

    /*** 区域名称*/
    public String name;

    /*** 全量区域*/
    @JsonProperty("merger_name")
    public String mergerName;

    /*** 电话区号*/
    @JsonProperty("city_code")
    public String cityCode;

    @JsonProperty("short_name")
    public String shortName;

    /*** 邮政编码*/
    @JsonProperty("zip_code")
    public String zipCode;

    /*** 经度*/
    public String lat;

}