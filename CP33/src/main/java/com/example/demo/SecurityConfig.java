package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	//전체 권한
		http.authorizeHttpRequests()
		.requestMatchers("/","/**","/page/**").permitAll()
		.requestMatchers("/admin/**").hasRole("admin")
		.requestMatchers("/user/**").hasRole("user")
		.anyRequest().authenticated();
	//login 권한
		http.formLogin()
		.loginPage("/page/login")
		.permitAll()
		.defaultSuccessUrl("/login");
	//logout 권한
		http.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/page/logout"))
		.invalidateHttpSession(true)
		.logoutSuccessUrl("/page/login");
	//프롬프트 없이 바로 인증, 보안절차 생략코드
		http.httpBasic();
		return http.build();
	}
}
