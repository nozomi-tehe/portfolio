package com.example.chart.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final PasswordEncoder passwordEncoder;

	private final UserDetailsService userDetailsService;
	
	private final MessageSource messageSource;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
		.authorizeHttpRequests(authz -> authz
                .requestMatchers("/login").permitAll()
                .anyRequest().authenticated()
		).formLogin(login -> login
                .loginProcessingUrl("/login")
                .loginPage("/login")
                .usernameParameter("loginId")
                .defaultSuccessUrl("/admin/student/display-list")
                .failureUrl("/login?failure")
                .permitAll()
        ).logout(logout -> logout
                .logoutSuccessUrl("/")
        ).exceptionHandling(ex ->
            ex.accessDeniedPage("/access-denied.html")
		
         );
		return http.build();
    }
	
	@Bean
	AuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		provider.setMessageSource(messageSource);
		return provider;
	}
	
}
