package com.cos.instagram.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping("/test/join")
	public User join() {
		User user = User.builder()
				.name("이병근")
				.password("1234")
				.phone("0102222")
				.bio("안녕!")
				.role(UserRole.USER)
				.build();
		User userEntity = userRepository.save(user);
		return userEntity;
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
}
