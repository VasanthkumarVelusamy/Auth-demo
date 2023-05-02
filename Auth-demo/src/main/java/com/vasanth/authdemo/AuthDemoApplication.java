package com.vasanth.authdemo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@SpringBootApplication
public class AuthDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthDemoApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter(
				new JwtAuthenticationManager(),
				new JwtAuthenticationConverter()
		);
	}
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().disable().csrf().disable()
				.authorizeHttpRequests()
				.requestMatchers(HttpMethod.GET, "/about").permitAll()
				.requestMatchers(HttpMethod.POST, "/user", "/user/login").permitAll()
				.requestMatchers("/*/**").authenticated()
				.and()
				.addFilterBefore(jwtAuthenticationFilter(), AnonymousAuthenticationFilter.class)
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
