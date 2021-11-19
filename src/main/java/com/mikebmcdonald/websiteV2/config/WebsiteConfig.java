package com.mikebmcdonald.websiteV2.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
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
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
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
	     registry.addResourceHandler("/images/**").addResourceLocations("/images/");
	 }
	
	@Bean
	public DataSource siteDataSource() {
		// Create db Connection Pool
		ComboPooledDataSource siteDataSource = new ComboPooledDataSource();
		
		// JDBC driver setting
		try {
			siteDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}
		
		// DB connection property import
		siteDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		siteDataSource.setUser(env.getProperty("jdbc.user"));
		siteDataSource.setPassword(env.getProperty("jdbc.password"));
		
		// Connection pool Property import, parsing property string to int inline
		siteDataSource.setInitialPoolSize(Integer.parseInt(env.getProperty("connection.pool.initialPoolSize")));
		siteDataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("connection.pool.maxPoolSize")));
		siteDataSource.setMinPoolSize(Integer.parseInt(env.getProperty("connection.pool.minPoolSize")));
		siteDataSource.setMaxIdleTime(Integer.parseInt(env.getProperty("connection.pool.maxIdleTime")));
		
		return siteDataSource;	
	}
	
	private Properties hibernateProperties() {
		Properties hbProps = new Properties();
		hbProps.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		hbProps.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		hbProps.setProperty("hibernate.current_session_context_class", env.getProperty("hibernate.current_session_context_class"));
		return hbProps;
	}
	
	@Bean 
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		sessionFactory.setDataSource(siteDataSource());
		System.out.println(env.getProperty("hibernate.packagesToScan"));
		sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
		
	}
	
	@Bean
	public HibernateTransactionManager transManager(SessionFactory sessionFactory) {
		// setup transaction manager based on session factory
		HibernateTransactionManager txMngr = new HibernateTransactionManager();
		txMngr.setSessionFactory(sessionFactory);
		return txMngr;
	}
	
}
