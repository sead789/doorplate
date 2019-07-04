package com.peitu.commons.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.peitu.doorplateqrcode.entity.AdministrativeCode;
import lombok.Data;

import java.util.List;

/**
 * @author Rising
 * @date 2019/6/12
 */
@Data
public class Response {

    public String ret;
    public String msg;
    public List<AdministrativeCode> data;

    @JsonProperty("log_id")
    public String logId;

}
