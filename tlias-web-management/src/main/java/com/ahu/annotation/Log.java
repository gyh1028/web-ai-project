package com.ahu.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)         //原注解，代表在方法上生效
@Retention(RetentionPolicy.RUNTIME) //原注解，代表这个注解什么时候生效，即运行
public @interface Log {
}
