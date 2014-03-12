package com.xiaolei.webapp.share;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)		// 用于在方法上注解
public @interface ApiPath {

	public abstract String value();
}
