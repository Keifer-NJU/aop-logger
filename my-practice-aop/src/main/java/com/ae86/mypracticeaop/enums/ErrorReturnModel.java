package com.ae86.mypracticeaop.enums;

/**
 * @author Keifer
 */

public enum  ErrorReturnModel {
    /**
     * 默认返回为null,
     */
    NULL(0),
    /**
     * 返回接口定义的返回类型
     */
    ORIGINAL(1),
    /**
     * 自定义返回类型
     * 需要实现 ValidErrorService接口中的方法
     */
    YOUDEFINE(2),
    /**
     * 抛出异常
     */
    THROWEXCEPTION(3);

    private int model;
    ErrorReturnModel(int model){
        this.model = model;
    }

    public int getModel() {
        return model;
    }
}
