package com.ae86.mypracticeaop.service.paramvalid;

/**
 * @author Keifer
 */
public interface ValidErrorController {
    /**
     * 自定义返回错误信息的服务接口，当业务校验失败的时候自定义返回错误的消息体
     * 该接口返回的类型需要与接口的返回类型一致，否则报错
     * @return
     */
    public Object returnErrorDto();
}
