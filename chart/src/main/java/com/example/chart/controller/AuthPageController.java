package com.example.chart.controller;


import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.chart.form.LoginForm;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/**
 * ログイン画面 Controller
 * 
 * @author 3030627
 *
 */
@Controller
@RequiredArgsConstructor
public class AuthPageController {
	
	private final HttpSession session;
	
	/**
	 * 初期表示
	 * @return
	 */
	@GetMapping("/login")
	public String loginForm(Model model,LoginForm loginForm) {
		return "loginForm";
	}
	
	@GetMapping(value = "/login", params = "error")
	public String loginFormWithError(Model model, LoginForm form) {
		Exception errorInfo = (Exception)session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		model.addAttribute("errorMsg",errorInfo.getMessage());
		return "loginForm";		
	}

}
