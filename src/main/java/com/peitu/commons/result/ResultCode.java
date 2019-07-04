package com.peitu.commons.result;

/**
 * @author Rising
 * @date 2019/6/21
 */
public enum ResultCode {

    成功("1"),
    请求参数有误("0001"),
    签名异常("0002"),
    用户已存在("0003"),
    用户名不能为空("0004"),
    密码不能为空("0005"),
    密码错误("0006"),
    用户不存在("0019"),
    用户注册失败("0007"),
    短信验证码失效("0008"),
    短信验证码错误("0009"),
    短信验证码发送过于频繁("0010"),
    短信验证码发送失败("0017"),
    接口异常("9999"),
    系统异常("9999"),
    支付失败("8888"),
    重复支付("6666"),
    验签失败("6000"),
    未创建用户("0018"),
    已创建用户("0019"),
    创建用户成功("0020");

    /**
     * 状态码
     */
    private String code;

    /**
     * 有参构造
     */
    ResultCode(String code) {
        this.code = code;
    }

    /**
     * get set 方法
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
