package com.mikebmcdonald.websiteV2.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SiteSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource loginDataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// JDBC Authentication for users
		auth.jdbcAuthentication().dataSource(loginDataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/delete", "/saveProject").hasRole("ADMIN")
			.antMatchers("/addNewPjkt", "/update").authenticated()
			.antMatchers("/", "/css/**", "/images/**").permitAll()
			.and()
			.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/authenticate").defaultSuccessUrl("/Projects")
				.permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/Error")
			.and()
			.logout().logoutSuccessUrl("/Projects").permitAll();
		
	}
	
	
	
}
