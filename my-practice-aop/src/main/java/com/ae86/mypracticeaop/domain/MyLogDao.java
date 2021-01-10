package com.ae86.mypracticeaop.domain;

import lombok.Data;

/**
 * @author Keifer
 */

@Data
public class MyLogDao {
    private String operModule;

    private String operType;

    private String operDesc;

    private String operUser;

    private Long consumeTime;

    private String basePath;

    private String uri;

    private String url;

    private String requestType;

    private String ip;

    private Object requestParam;

    private Object returnResult;
}
