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
	private final Mapper mapper;
	private final PasswordEncoder passwordEncoder;
	
	public Optional<UserInfo> resistUserInfo(SignupForm form) {
		var userInfoExistedOpt = repository.findById(form.getLoginId());
		if (userInfoExistedOpt.isPresent()) {
			return Optional.empty();
		}

		var userInfo = mapper.map(form, UserInfo.class);
		var encodedPassword = passwordEncoder.encode(form.getPassword());
		userInfo.setPassword(encodedPassword);

		return Optional.of(repository.save(userInfo));
	}
	

}
