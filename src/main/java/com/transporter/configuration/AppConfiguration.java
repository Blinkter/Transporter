package com.transporter.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfiguration implements WebMvcConfigurer {

	@Bean
	public ViewResolver viewResolver() {

		final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	// private Properties getHibernateProperties() {
	//
	// // set hibernate properties
	// Properties props = new Properties();
	//
	// props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
	// props.setProperty("hibernate.show_sql",
	// env.getProperty("hibernate.show_sql"));
	//
	// return props;
	// }
	//
	// @Bean
	// @Autowired
	// public HibernateTransactionManager transactionManager(SessionFactory
	// sessionFactory) {
	//
	// // setup transaction manager based on session factory
	// HibernateTransactionManager txManager = new HibernateTransactionManager();
	// txManager.setSessionFactory(sessionFactory);
	//
	// return txManager;
	// }
}