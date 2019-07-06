package com.peitu.commons.result;

/**
 * @author Rising
 * @date 2019/6/21
 */
public class ResultUtil {

//    public static ResponseEntity<ResultEntity> setMsg(ResultCode e) {
//        return ResponseEntity.ok(new ResultEntity(e.getCode(), e.name(), null));
//    }
//
//    public static ResponseEntity<ResultEntity> setMsg(ResultCode e, String msg) {
//        return ResponseEntity.ok(new ResultEntity(e.getCode(), msg, null));
//    }
//
//    public static ResponseEntity<ResultEntity> setMsg(ResultCode e, Object obj) {
//        return ResponseEntity.ok(new ResultEntity(e.getCode(), e.name(), obj));
//    }


    public static ResultEntity setMsg(ResultCode e) {
        return new ResultEntity(e.getCode(), e.name(), null);
    }

    public static ResultEntity setMsg(ResultCode e, String msg) {
        return new ResultEntity(e.getCode(), msg, null);
    }

    public static ResultEntity setMsg(ResultCode e, Object obj) {
        return new ResultEntity(e.getCode(), e.name(), obj);
    }

    public static ResultEntity setMsg(Object obj) {
        return new ResultEntity(ResultCode.成功.toString(), ResultCode.成功.getCode(), obj);
    }

}
