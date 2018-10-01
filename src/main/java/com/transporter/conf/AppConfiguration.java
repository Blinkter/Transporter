package com.transporter.conf;

import java.beans.PropertyVetoException;
import java.util.Locale;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.transporter" })
@PropertySource("classpath:persistence-mysql.properties")
// @EnableJpaRepositories(basePackages = "com.transporter")
public class AppConfiguration implements WebMvcConfigurer{

	// set up variable to hold the properties
	@Autowired
	private Environment env;

	@Bean
	public ViewResolver viewResolver() {

		final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean
	public DataSource dataSource() {

		ComboPooledDataSource dataSource = new ComboPooledDataSource();

		// set the jdbc driver class
		try {
			dataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException exc) {
			throw new RuntimeException(exc);
		}

		// set database connection props
		dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		dataSource.setUser(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		// set connection pool props
		dataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));

		dataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));

		dataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));

		dataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

		return dataSource;

	}

	private int getIntProperty(String propName) {

		String propVal = env.getProperty(propName);

		// now conver to int
		int intPropVal = Integer.parseInt(propVal);

		return intPropVal;
	}

	private Properties getHibernateProperties() {

		// set hibernate properties
		Properties props = new Properties();

		props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));

		return props;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {

		// create session factorys
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

		// set the properties
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		sessionFactory.setHibernateProperties(getHibernateProperties());

		return sessionFactory;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {

		// setup transaction manager based on session factory
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		return txManager;
	}

	// @Override
	// public void addFormatters(FormatterRegistry registry) {
	// registry.addConverter(new LocalDateConverter("yyyy-MM-dd"));
	// }
	/*
	 * @Bean public Validator validator() { return new LocalValidatorFactoryBean();
	 * }
	 * 
	 * @Override public void addFormatters(final FormatterRegistry registry) {
	 * registry.addConverter(authorConverter()); }
	 * 
	 * @Bean public AuthorConverter authorConverter() { return new
	 * AuthorConverter(); }
	 * 
	 * @Override public void addResourceHandlers(final ResourceHandlerRegistry
	 * registry) { registry .addResourceHandler("/resources/**")
	 * .addResourceLocations("/WEB-INF/resources/"); }
	 * 
	 * @Override public void
	 * configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	 * configurer.enable(); }
	 * 
	 * @Bean public LocalEntityManagerFactoryBean entityManagerFactory() { final
	 * LocalEntityManagerFactoryBean emfb = new LocalEntityManagerFactoryBean();
	 * emfb.setPersistenceUnitName("transporterPersistenceUnit"); return emfb; }
	 * 
	 * @Bean public JpaTransactionManager transactionManager(final
	 * EntityManagerFactory emf) { final JpaTransactionManager tm = new
	 * JpaTransactionManager(emf); return tm; }
	 * 
	 * @Bean(name = "localeResolver") public LocaleContextResolver
	 * getLocaleContextResolver() {
	 * 
	 * final SessionLocaleResolver localeResolver = new SessionLocaleResolver();
	 * localeResolver.setDefaultLocale(new Locale("pl", "PL"));
	 * 
	 * return localeResolver; }
	 */
}