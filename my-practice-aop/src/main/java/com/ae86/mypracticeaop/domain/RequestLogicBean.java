package com.ae86.mypracticeaop.domain;

/**
 * @author Keifer
 */
public class RequestLogicBean {
    /**
     * 验证参数是否需要非空校验
     * @return
     */
    private boolean paramNotNull;

    /**
     * 是否需要打印复杂对象
     * 实体，集合
     * 默认false,基本类型的入参默认打印
     * @return
     */
    private boolean logInParam;

    /**
     * 是否需要打印复杂对象
     * 实体，集合
     * 默认false,基本类型的入参默认打印
     * @return
     */
    private boolean logOutParam;

    /**
     * 是否继续方法耗时计算
     * 默认faslse
     * @return
     */
    private boolean exeTime;

    /**
     * 参数验证模式
     */
    private String [] validModel;

    /**
     * 参数验证失败后返回的模式
     * @see com.daojia.qypt.springboot.daop.enums.ErrorReturnModel
     */
    private int errorReturnModel;

    /**
     * 方法参数列表
     */
    private String parameterNames;

    public String getParameterNames() {
        return parameterNames;
    }

    public void setParameterNames(String parameterNames) {
        this.parameterNames = parameterNames;
    }

    public int getErrorReturnModel() {
        return errorReturnModel;
    }

    public void setErrorReturnModel(int errorReturnModel) {
        this.errorReturnModel = errorReturnModel;
    }

    public String[] getValidModel() {
        return validModel;
    }

    public void setValidModel(String[] validModel) {
        this.validModel = validModel;
    }

    public boolean isLogInParam() {
        return logInParam;
    }

    public void setLogInParam(boolean logInParam) {
        this.logInParam = logInParam;
    }

    public boolean isLogOutParam() {
        return logOutParam;
    }

    public void setLogOutParam(boolean logOutParam) {
        this.logOutParam = logOutParam;
    }

    public boolean isParamNotNull() {
        return paramNotNull;
    }

    public void setParamNotNull(boolean paramNotNull) {
        this.paramNotNull = paramNotNull;
    }

    public boolean isExeTime() {
        return exeTime;
    }

    public void setExeTime(boolean exeTime) {
        this.exeTime = exeTime;
    }

    @Override
    public String toString() {
        return "RequestLogicBean{" +
                "paramNotNull=" + paramNotNull +
                ", logInParam=" + logInParam +
                ", logOutParam=" + logOutParam +
                ", exeTime=" + exeTime +
                '}';
    }
}

