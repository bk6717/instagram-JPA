package com.cos.instagram.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // 메서드의 파라메터로만 들어가야한다.
@Retention(RetentionPolicy.RUNTIME) //작동시점을 정한다. spring 서버를 실행할때부터 활성화
public @interface LoginUserAnnotation {

}
