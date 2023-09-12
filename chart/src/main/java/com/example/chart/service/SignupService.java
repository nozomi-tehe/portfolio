package com.example.chart.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.chart.entity.UserInfo;
import com.example.chart.form.SignupForm;
import com.example.chart.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignupService {

	/** ユーザー情報テーブルDAO */
	private final UserInfoRepository repository;

	/** PasswordEncoder */
	private final PasswordEncoder passwordEncoder;


	/**
	 * ユーザ情報テーブル 新規登録
	 * 
	 * @param form 入力情報
	 * @return 登録情報(ユーザー情報Entity)、既に同じユーザIDで登録がある場合はEmpty
	 */
	public Optional<UserInfo> resistUserInfo(SignupForm form) {
		var userInfoExistedOpt = repository.findById(form.getLoginId());
		if (userInfoExistedOpt.isPresent()) {
			return Optional.empty();
		}
		LocalDate nowDate = LocalDate.now();
		
		UserInfo userInfo = new UserInfo(
			form.getLoginId(),
			form.getPassword(),
			form.getUserId(),null,null);

		var encodedPassword = passwordEncoder.encode(form.getPassword());
		userInfo.setPassword(encodedPassword);

		return Optional.of(repository.save(userInfo));
	}
}