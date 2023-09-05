package com.example.chart.form;

import lombok.Data;

/**
 * ログイン画面
 * 
 * @author 3030627
 *
 */

@Data
public class LoginForm {
	
	/** ログインID */
	private String loginId;

	/** パスワード */
	private String password;

}
