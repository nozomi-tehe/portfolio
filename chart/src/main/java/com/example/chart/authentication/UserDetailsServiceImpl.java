package com.example.chart.authentication;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.chart.entity.UserInfo;
import com.example.chart.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final UserInfoRepository repository;
	


	/**
	 * ユーザー情報生成
	 * 
	 * @param username ログインID
	 * @throws UsernameNotFoundException
	 * 
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserInfo userInfo = repository.findById(username)
				.orElseThrow(()-> new UsernameNotFoundException(username));
		
		return User.withUsername(userInfo.getLoginId())
				.password(userInfo.getPassword())
				.roles("ADMIN")
				.build();

	}

	
}
