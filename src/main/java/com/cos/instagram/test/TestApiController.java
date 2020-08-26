package com.cos.instagram.test;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Soundbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.instagram.config.auth.PrincipalDetails;
import com.cos.instagram.config.handler.ex.MyUsernameNotFoundException;
import com.cos.instagram.domain.follow.Follow;
import com.cos.instagram.domain.follow.FollowRepository;
import com.cos.instagram.domain.image.Image;
import com.cos.instagram.domain.image.ImgRepository;
import com.cos.instagram.domain.tag.Tag;
import com.cos.instagram.domain.tag.TagRepository;
import com.cos.instagram.domain.user.User;
import com.cos.instagram.domain.user.UserRepository;
import com.cos.instagram.domain.user.UserRole;

@RestController
public class TestApiController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ImgRepository imgRepository;
	
	@Autowired
	private TagRepository tagRepostory;
	
	@Autowired
	private FollowRepository followRepository;
	
	@GetMapping("/test/facebook2")
	public @ResponseBody String facebook2(@AuthenticationPrincipal PrincipalDetails principal) {
		System.out.println(principal.getUser());
		return "facebook 로그인 완료2";
	}
	//서비스를 타야 AuthenticationPrincipal 이 생김
	@GetMapping("/test/facebook")
	public @ResponseBody String facebook(Authentication authentication) {
		PrincipalDetails principalDetails 
								= (PrincipalDetails)authentication.getDetails();
		System.out.println("principalDetails :" + authentication.getPrincipal()); //facebook
		System.out.println("principalDetails :" + principalDetails.getUser());
		return "facebook 로그인 완료";
	}

	
	@PostMapping("/test/join")
	public User join(@RequestBody User user) {
		
		user.setRole(UserRole.USER);
		
		User userEntity = userRepository.save(user);
		return userEntity;
		
	}
	
	@PostMapping("/test/api/input")
	public Image input(@RequestBody Image image) {
		
		Image imageEntity = imgRepository.save(image);
		
		return imageEntity;
		
	}
	
	
	
	@GetMapping("/test/image")
	public String image() {
		
		User userEntity = userRepository.findById(1).get();
		
		Image image = Image.builder()
				.location("한국")
				.caption("설명")
				.user(userEntity)
				.build();
		Image imageEntity = imgRepository.save(image);
		
		List<Tag> tags = new ArrayList<>();
		
		Tag tag1 = Tag.builder()
				.name("#다낭")
				.image(imageEntity)
				.build();
		Tag tag2 = Tag.builder()
				.name("#여행")
				.image(imageEntity)
				.build();
		
		tags.add(tag1);
		tags.add(tag2);
		// 중요한것은 
		
		tagRepostory.saveAll(tags);
		
		return "img 인서트 잘됨"; // MessgeConverter의 Jackson 발동
	}
	
	@GetMapping("test/image/list")
	public List<Image> imageList(){
		return imgRepository.findAll();
	}
	
	@GetMapping("test/tag/list")
	public List<Tag> tagList(){
		return tagRepostory.findAll();
	}
	
	@PostMapping("/test/api/follow/{fromUserId}/{toUserId}")
	public String follow(@PathVariable int fromUserId, @PathVariable int toUserId) {
		
		User fromUserEntity = userRepository.findById(fromUserId).get();
		User toUserEntity = userRepository.findById(toUserId).get();
		
		Follow follow = Follow.builder()
				.fromUser(fromUserEntity)
				.toUser(toUserEntity)
				.build();
		
		followRepository.save(follow);
		
		return fromUserEntity.getUsername() +" 이 "
		+ toUserEntity.getUsername()+"을 팔로우 하였습니다.";
	}
	
	@GetMapping("/test/username/{username}")
	public String test9(@PathVariable String username){
		if (!username.equals("test")) {
				throw new MyUsernameNotFoundException("유저네임 못찾음");
		}
		return "username test";
	}

}
