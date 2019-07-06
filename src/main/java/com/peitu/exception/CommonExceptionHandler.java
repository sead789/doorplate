package com.peitu.exception;

import com.peitu.commons.result.ResultCode;
import com.peitu.commons.result.ResultEntity;
import com.peitu.commons.result.ResultUtil;
import org.apache.tomcat.util.descriptor.web.LoginConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常捕获
 *
 * @author Rising
 * @date 2019/7/5
 */
@ControllerAdvice
public class CommonExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(LoginConfig.class);

    /**
     * 系统异常捕获处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultEntity javaExceptionHandler(Exception ex) {
        //异常日志入库
        LOG.error("捕获到Exception异常：" + ex.toString());
        return ResultUtil.setMsg(ResultCode.系统异常, ex.toString());
    }

    /**
     * 入参异常捕获
     * 参数 @RequestBody 校验异常类
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultEntity handleBindException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        LOG.error("参数校验异常:{}({})", fieldError.getDefaultMessage(), fieldError.getField());
        return ResultUtil.setMsg(ResultCode.请求参数有误, fieldError.getDefaultMessage());
    }

    /**
     * 入参异常捕获
     * 参数 @RequestParam 校验异常类
     * 校验 除了 @RequestBody 注解方式的参数校验 对应的 bindingresult 为 BeanPropertyBindingResult
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResultEntity handleBindException(BindException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        LOG.error("必填校验异常:{}({})", fieldError.getDefaultMessage(), fieldError.getField());
        return ResultUtil.setMsg(ResultCode.请求参数有误, fieldError.getDefaultMessage());
    }

}
