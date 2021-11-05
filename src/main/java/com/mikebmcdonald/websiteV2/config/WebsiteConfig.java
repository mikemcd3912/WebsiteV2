package com.mikebmcdonald.websiteV2.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.mikebmcdonald.websiteV2")
@PropertySource("classpath:dbconn.properties")
public class WebsiteConfig {

	@Autowired
	private Environment env;
	
	// set up view resolver for Hbs templating
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setSuffix(".hbs");
		viewResolver.setPrefix("/WEB-INF/view/");
		return viewResolver;
	}
	
	@Bean
	public DataSource loginDataSource() {
		// Create db Connection Pool
		ComboPooledDataSource loginDataSource = new ComboPooledDataSource();
		
		// JDBC driver setting
		try {
			loginDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
