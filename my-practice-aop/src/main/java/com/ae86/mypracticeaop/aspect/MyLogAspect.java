package com.ae86.mypracticeaop.aspect;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONUtil;
import com.ae86.mypracticeaop.domain.MyLogDao;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Keifer
 */
@Aspect
@Component
public class MyLogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyLogAspect.class);

    @Pointcut("@annotation( com.ae86.mypracticeaop.annotation.MyLog)")
    public void myLogCut(){

    }

    @Around("myLogCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{
        long startTime = System.currentTimeMillis();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        MyLogDao myLogDao = new MyLogDao();
        Object result = joinPoint.proceed();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if(method.isAnnotationPresent(ApiOperation.class)){
            ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
            myLogDao.setOperDesc(apiOperation.value());
        }
        long endTime = System.currentTimeMillis();
        String url = request.getRequestURL().toString();
        myLogDao.setBasePath(StrUtil.removeSuffix(url, URLUtil.url(url).getPath()));
        myLogDao.setIp(request.getRemoteUser());
        myLogDao.setRequestType(request.getMethod());
        myLogDao.setRequestParam(getParameter(method, joinPoint.getArgs()));
        myLogDao.setReturnResult(result);
        myLogDao.setConsumeTime((endTime - startTime));
        myLogDao.setUri(request.getRequestURI());
        myLogDao.setUrl(request.getRequestURL().toString());
        LOGGER.info("{}", JSONUtil.parse(myLogDao));
        return result;
    }

    private Object getParameter(Method method, Object[] args) {
        List<Object> argList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if(requestBody!=null){
                argList.add(args[i]);
            }
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if(requestParam!=null){
                Map<String , Object>map = new HashMap<>();
                String key = parameters[i].getName();
                if(StringUtils.hasLength(requestParam.value())){
                    key = requestParam.value();
                }
                map.put(key,args[i]);
                argList.add(map);
            }
        }
        if(argList.size()==0){
            return null;
        }else if (argList.size() == 1) {
            return argList.get(0);
        } else {
            return argList;
        }
    }

}
