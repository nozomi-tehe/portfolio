package com.example.chart.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.chart.entity.UserInfo;
import com.example.chart.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserInfoService {
	
	private final UserInfoRepository repository;
	
	private final PasswordEncoder passwordEncoder;
	
	public Optional<UserInfo> searchUserById(String id) {
		return repository.findById(id);
	}
	

}
