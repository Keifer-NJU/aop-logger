package com.ae86.mypracticeaop.aspect;

import com.ae86.mypracticeaop.Constant;
import com.ae86.mypracticeaop.context.RequestBusLogicContext;
import com.ae86.mypracticeaop.domain.RequestLogicBean;
import com.ae86.mypracticeaop.enums.ErrorReturnModel;
import com.ae86.mypracticeaop.exceptions.ParamValidErrorException;
import com.ae86.mypracticeaop.service.paramvalid.ValidParamHelperService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Keifer
 */
@Aspect
@Component
public class RequestLogicParamValidAspect {
    @Resource
    private RequestBusLogicContext baseBusLogicContext;

    @Resource
    private ValidParamHelperService validParamHelperService;

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestLogicParamValidAspect.class);

    @Pointcut("@annotation(com.ae86.mypracticeaop.annotation.RequestLogic)")
    public void myParamValidCut(){

    }
    @Around("myParamValidCut()")
    public Object doRequestLogicParamValid(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = new Object[0];
        MethodInvocationProceedingJoinPoint mjp = (MethodInvocationProceedingJoinPoint) joinPoint;
        MethodSignature signature = (MethodSignature) mjp.getSignature();
        Method method = signature.getMethod();
        RequestLogicBean baseBusLogicBean = null;

        //接口验证失败后会调用两次，第二次调用注册的默认异常返回方法，因此需要过滤掉
        if (!"returnErrorDto".equals(method.getName())) {
            String key = baseBusLogicContext.getKey(joinPoint);
            args = joinPoint.getArgs();
            baseBusLogicBean = baseBusLogicContext.getRequestLogicMap().get(key.hashCode());
        }
        if (baseBusLogicBean == null) {
            //如果没有注解上下文则通过
            return joinPoint.proceed(args);
        }
        boolean valid = validParamHelperService.validInputParam(baseBusLogicBean, args);
        if (valid == false) {
            //默认返回空
            if (baseBusLogicBean.getErrorReturnModel() == ErrorReturnModel.NULL.getModel()) {
                return null;
            }
            //返回接口定义的返回值
            if (baseBusLogicBean.getErrorReturnModel() == ErrorReturnModel.ORIGINAL.getModel()) {
                Class<?> clazz = method.getReturnType();
                if (clazz.getSimpleName().equals(Constant.LIST)) {
                    return new ArrayList();
                } else if (clazz.getSimpleName().equals(Constant.SET)) {
                    return new HashSet<>(0);
                } else if (clazz.getSimpleName().equals(Constant.MAP)) {
                    return new HashMap<>(0);
                } else {
                    return clazz.newInstance();
                }
            }

            //抛异常
            if (baseBusLogicBean.getErrorReturnModel() == ErrorReturnModel.THROWEXCEPTION.getModel()) {
                LOGGER.error("validate params error,baseBusLogicBean = {}", baseBusLogicBean.toString());
                new ParamValidErrorException("validate params error!").printStackTrace();
                throw new ParamValidErrorException("validate params error!");
            }

            //自定义
            if (baseBusLogicBean.getErrorReturnModel() == ErrorReturnModel.YOUDEFINE.getModel()) {
                return null;
                //return baseBusLogicContext.invokeErrorMsg();
            }
        }
        //如果没有报错，放行
        return joinPoint.proceed(args);
    }
}
