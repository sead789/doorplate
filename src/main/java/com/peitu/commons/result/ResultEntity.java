package com.peitu.commons.result;

import lombok.Data;

/**
 * @author Rising
 * @date 2019/6/21
 */
@Data
public class ResultEntity {

    /**
     * 返回状态码
     */
    private String returnCode;

    /**
     * 返回信息
     */
    private String returnMessage;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * 无参构造
     */
    public ResultEntity() {
    }

    /**
     * 有参构造
     */
    public ResultEntity(String returnCode, String returnMessage, Object data) {
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
        this.data = data;
    }

}
