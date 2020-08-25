package com.cos.instagram.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


// JPaRepository가 extends 되면 @Repository 필요가없다. IOC 자동 등록이 된다.
public interface UserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByUsername(String username);
}
