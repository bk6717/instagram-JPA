package com.cos.instagram.web;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.instagram.config.auth.CosAnnotation;
import com.cos.instagram.config.auth.LoginUserAnnotation;
import com.cos.instagram.config.auth.PrincipalDetails;
import com.cos.instagram.config.auth.dto.LoginUser;
import com.cos.instagram.domain.user.User;

@Controller
public class ImageController {
	
	@GetMapping({"","/","/image/feed"})
	public String feed(@LoginUserAnnotation LoginUser loginUser
			           ) {
		//User에는 패스워드가 담겨있기때문에 
		// 오브젝트를 새로만든다.
		System.out.println("loginUser :" + loginUser);
		return "image/feed";
	}
	
	@GetMapping({"/testcos"})
	public String test(@CosAnnotation String cos) {
		//User에는 패스워드가 담겨있기때문에 
		// 오브젝트를 새로만든다.
		return cos;
	}
}
