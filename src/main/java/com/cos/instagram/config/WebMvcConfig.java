package com.cos.instagram.config;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cos.instagram.config.auth.CosAnnotation;
import com.cos.instagram.config.auth.LoginUserAnnotation;
import com.cos.instagram.config.auth.dto.LoginUser;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
//이곳에 필터를 걸수 있다.
public class WebMvcConfig implements WebMvcConfigurer{
	
	private final HttpSession httpSession;
	
	
	//매개변수 분석 
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		
		resolvers.add(new HandlerMethodArgumentResolver() {
			
			@Override
			public boolean supportsParameter(MethodParameter parameter) {
				boolean isConAnnotation = parameter.getParameterAnnotation(CosAnnotation.class) != null;
				
				return isConAnnotation;
			}
			
			@Override
			public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
					NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
				return "cos";
			}
		});
		
		
		
		
		resolvers.add(new HandlerMethodArgumentResolver() {
			@Override
			public boolean supportsParameter(MethodParameter parameter) { //요청온 함수에 있는 파라메터를 담는다.
				//1. 어노테이션이 있으면 true가 떨어진다. 
				boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUserAnnotation.class) != null;
				//2. 타입이 loginUser면 true가 떨어짐
				boolean isUserClass = LoginUser.class.equals(parameter.getParameterType());
				//3. 
				return isLoginUserAnnotation && isUserClass;
			}
			
			//supportsParameter가 true가 리턴되면 함수 실행
			//세션을 만들어서 @LoginUserAnnotation 에 주입해준다.
			@Override
			public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
					NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

				return httpSession.getAttribute("loginUser");
			}
		});
	}
	
}
