package com.mikebmcdonald.websiteV2.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.mikebmcdonald")
@PropertySource("classpath:dbconn.properties")
public class WebsiteConfig implements WebMvcConfigurer{

	@Autowired
	private Environment env;
	
	// set up view resolver for Hbs templates
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	 public void addResourceHandlers(ResourceHandlerRegistry registry) {
	     registry.addResourceHandler("/css/**").addResourceLocations("/css/");
	     registry.addResourceHandler("/images/**").addResourceLocations("/images");
	 }
	
	@Bean
	public DataSource loginDataSource() {
		// Create db Connection Pool
		ComboPooledDataSource loginDataSource = new ComboPooledDataSource();
		
		// JDBC driver setting
		try {
			loginDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}
		
		// DB connection property import
		loginDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		loginDataSource.setUser(env.getProperty("jdbc.user"));
		loginDataSource.setPassword(env.getProperty("jdbc.password"));
		
		// Connection pool Property import, parsing property string to int inline
		loginDataSource.setInitialPoolSize(Integer.parseInt(env.getProperty("connection.pool.initialPoolSize")));
		loginDataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("connection.pool.maxPoolSize")));
		loginDataSource.setMinPoolSize(Integer.parseInt(env.getProperty("connection.pool.minPoolSize")));
		loginDataSource.setMaxIdleTime(Integer.parseInt(env.getProperty("connection.pool.maxIdleTime")));
		
		return loginDataSource;	
	}
	
}
