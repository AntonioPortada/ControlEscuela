package com.escuela.spring.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		
		PasswordEncoder encoder = passwordEncoder();
		UserBuilder users = User.builder().passwordEncoder(encoder :: encode);
		
		builder.inMemoryAuthentication()
		.withUser(users.username("admin").password("1234").roles("ADMIN", "MAESTRO", "ALUMNO"))
		.withUser(users.username("alumno").password("1234").roles("ALUMNO"))
		.withUser(users.username("maestro").password("1234").roles("MAESTRO"));
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/css/**", "/js/**", "/alumno/listar", "/profesor/listar").permitAll()
		.antMatchers("/alumno/**", "/profesor/**").hasAnyRole("ADMIN")
		.antMatchers("**/detalle/**").hasAnyRole("ALUMNO")
		.antMatchers("**/detalle/**").hasAnyRole("MAESTRO")
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login").permitAll()
		.and()
		.logout().permitAll()
		;
	}
}
