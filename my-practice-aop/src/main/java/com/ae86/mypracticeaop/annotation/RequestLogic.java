package com.ae86.mypracticeaop.annotation;

import java.lang.annotation.*;

/**
 * @author Keifer
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestLogic {
    boolean paramNotNull() default false;

    String []validModel() default "";

    boolean logInParam() default false;

    boolean logOutParam() default false;

    boolean exeTime() default false;
}
