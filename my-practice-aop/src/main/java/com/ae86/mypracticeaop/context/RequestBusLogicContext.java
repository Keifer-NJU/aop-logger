package com.ae86.mypracticeaop.context;

import com.ae86.mypracticeaop.domain.RequestLogicBean;
import io.micrometer.core.instrument.util.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Keifer
 */
@Component
public class RequestBusLogicContext {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 扫描注解：RequestLogic中的属性，
     * key:方法上带有RequestLogic注解的类路径,egg:com.xx.xxImpl.xxMethod，转换为hash值作为key
     * value:与注解RequestLogic对应的RequestLogicBean实体
     * 适用于http接口层
     */
    private Map<Integer, RequestLogicBean> baseBusLogicMap = new HashMap<>();

    public Map<Integer, RequestLogicBean> getRequestLogicMap() {
        return baseBusLogicMap;
    }


    /**
     * 获取调用方法的key的hash值
     *
     * @return
     */
    public String getKey(JoinPoint joinPoint) {
        String methodPath = joinPoint.getSignature().toString();
        String className = joinPoint.getTarget().getClass().getName();
        int index = methodPath.lastIndexOf(".");
        String key = "";
        if (index >= 0) {
            String methodStr = methodPath.substring(index);
            key = className + methodStr;
        }
        return key;
    }
    public String getTmpKey(JoinPoint joinPoint, String key) {
        String className = joinPoint.getTarget().getClass().getName();
        String tmpKey = className.substring(className.lastIndexOf(".") + 1) + "." + key.substring(key.lastIndexOf(".") + 1);
        return tmpKey;
    }
    public String getTmpKey(JoinPoint joinPoint) {
        String key = getKey(joinPoint);
        return getTmpKey(joinPoint, key);
    }
    /**
     * 根据上下文获取业务逻辑bean
     *
     * @return
     */
    public RequestLogicBean getLogicBean(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        int hashKey = key.hashCode();
        RequestLogicBean baseBusLogicBean = this.getRequestLogicMap().get(hashKey);
        if (baseBusLogicBean == null) {
            return null;
        }
        return baseBusLogicBean;
    }
}

