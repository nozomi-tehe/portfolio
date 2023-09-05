package com.example.chart.config;



import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authz -> authz
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/").permitAll()
                //.requestMatchers("/manager").hasRole("MANAGER")
                //.requestMatchers("/admin").hasRole("ADMIN")
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
	
	/**
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails taro = User.builder()
				.username("taro").password("{noop}taro123").roles("ADMIN").build();
		UserDetails jiro = User.builder()
				.username("jiro").password("{noop}jiro123").roles("EMPLOYEE").build();
		UserDetails saburo = User.builder()
				.username("saburo").password("{noop}saburo123").roles("GUEST").build();
		
		return new InMemoryUserDetailsManager(taro,jiro,saburo);
	}
	*/

    

}
