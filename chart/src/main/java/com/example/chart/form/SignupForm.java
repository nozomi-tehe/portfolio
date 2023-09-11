package com.example.chart.form;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class SignupForm {

	/** ログインID */
	private String loginId;

	/** パスワード */
	@Length(min = 8, max = 20)
	private String password;
	
	/** 生徒ID */
	private Integer userId;
}