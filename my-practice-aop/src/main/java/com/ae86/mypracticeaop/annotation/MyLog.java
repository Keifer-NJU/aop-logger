package com.ae86.mypracticeaop.annotation;

import java.lang.annotation.*;

/**
 * @author Keifer
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog {
    String operModule() default "";

    String operType() default "";

    String operDesc() default "";
}
