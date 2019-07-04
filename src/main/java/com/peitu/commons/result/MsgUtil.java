package com.peitu.commons.result;

import org.springframework.http.ResponseEntity;

/**
 * @author Rising
 * @date 2019/6/21
 */
public class MsgUtil {

    public static ResponseEntity<ResultEntity> retMsg(ResultCode e) {
        return ResponseEntity.ok(new ResultEntity(e.getCode(), e.name(), null));
    }

    public static ResponseEntity<ResultEntity> retMsg(ResultCode e, String msg) {
        return ResponseEntity.ok(new ResultEntity(e.getCode(), msg, null));
    }

    public static ResponseEntity<ResultEntity> retMsg(ResultCode e, Object obj) {
        return ResponseEntity.ok(new ResultEntity(e.getCode(), e.name(), obj));
    }

}
