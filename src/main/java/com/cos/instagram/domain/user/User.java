package com.cos.instagram.domain.user;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private String name;
	private String website;
	private String bio;
	private String phone;
	private String gender;
	private String profileImage;
	@Enumerated(EnumType.STRING)
	private UserRole role;
	private String provider;
	private String providerId;
	@CreationTimestamp //DB에 넣을때 자동으로 현재시간이 들어간다.
	private Timestamp createDate;
	
}
