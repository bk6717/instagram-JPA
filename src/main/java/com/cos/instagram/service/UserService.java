package com.cos.instagram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.instagram.domain.user.User;
import com.cos.instagram.domain.user.UserRepository;
import com.cos.instagram.web.dto.JoinReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional //open-in-view를 true 로하면  트랜잭션이 끝나도 
	//db커넥션이 유지된다.
	public void 회원가입(JoinReqDto joinReqDto) {
		//password 인코딩
		String encPassword =
					bCryptPasswordEncoder.encode(joinReqDto.getPassword());
		joinReqDto.setPassword(encPassword);
		//joinReqDto를 UserEntity로 바꾸어야함
		userRepository.save(joinReqDto.toEntity());
	}
	
}
