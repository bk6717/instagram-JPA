package com.cos.instagram.domain.image;


import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.instagram.domain.tag.Tag;
import com.cos.instagram.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String location; // 위치
	private String caption; // 사진설명
	private String imageUrl;
	
	@ManyToOne(fetch = FetchType.EAGER) //많은 이미지는 유저한명이 등록할 수 있다. n : 1
	@JoinColumn(name = "userId") // userId라는 이름으로 등록
	//타입은 User오브젝트의 pk타입
	private User user; //Object로 관계를 맺는다. ORM
	// 이미지하나에 여러개의 태그가 들어갈 수 있다.
	@OneToMany(mappedBy = "image", fetch = FetchType.LAZY) //연관관계 주인의 변수명을 적는다.
	//mappedBy : FK가 아니라고 알려준다. FK의 주인을 정할때 사용한다.
	
	@JsonIgnoreProperties({"image"}) // image만 json이 getter호출안함
	private List<Tag> tags; //태그는 여러개 등록해야하기때문
	
	@CreationTimestamp //DB에 insert 할때 현재시간 들어감 
	private Timestamp createDate;
	
}