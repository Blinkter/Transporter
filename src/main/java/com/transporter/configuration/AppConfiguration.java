package com.transporter.configuration;

import java.util.Properties;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.transporter")
@PropertySource("classpath:application.properties")
public class AppConfiguration implements WebMvcConfigurer {

	@Autowired
	private Environment environment;

	@Value("${datasource.sampleapp.maxPoolSize:10}")
	private int maxPoolSize = 5;

	/*
	 * Configure BCrypt password encoder.
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Bean
    @Primary
    @ConfigurationProperties(prefix = "datasource.sampleapp")
    public DataSourceProperties dataSourceProperties(){
        return new DataSourceProperties();
    }
	
	
	
	 @Bean
	    public DataSource dataSource() {
	        DataSourceProperties dataSourceProperties = dataSourceProperties();
	            HikariDataSource dataSource = (HikariDataSource) DataSourceBuilder
	                    .create(dataSourceProperties.getClassLoader())
	                    .driverClassName(dataSourceProperties.getDriverClassName())
	                    .url(dataSourceProperties.getUrl())
	                    .username(dataSourceProperties.getUsername())
	                    .password(dataSourceProperties.getPassword())
	                    .type(HikariDataSource.class)
	                    .build();
	            dataSource.setMaximumPoolSize(maxPoolSize);
	            return dataSource;
	    }

	/*
	 * Entity Manager Factory setup.
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setPackagesToScan(new String[] { "com.transporter.model" });
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
	    factoryBean.setJpaProperties(jpaProperties());
		return factoryBean;
	}
	
	@Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        return hibernateJpaVendorAdapter;
    }

	/*
	 * Specify any provider specific properties.
	 */
	private Properties jpaProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("datasource.sampleapp.hibernate.dialect"));
		properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("datasource.sampleapp.hibernate.hbm2ddl.method"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("datasource.sampleapp.hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("datasource.sampleapp.hibernate.format_sql"));
		if (StringUtils.isNotEmpty(environment.getRequiredProperty("datasource.sampleapp.defaultSchema"))) {
			properties.put("hibernate.default_schema", environment.getRequiredProperty("datasource.sampleapp.defaultSchema"));
		}
		return properties;
	}

	@Bean
	@Autowired
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(emf);
		return txManager;
	}
}