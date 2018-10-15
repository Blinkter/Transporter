package com.transporter.configuration;

import java.util.Properties;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class AppConfiguration implements WebMvcConfigurer {

	@Autowired
	private Environment environment;

	private int maxPoolSize = 5;

	/*
	 * Configure BCrypt password encoder.
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	/*
	 * Populate SpringBoot DataSourceProperties object directly from properties
	 * file.
	 */
	@Bean
	@Primary
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}

	/*
	 * Configure HikariCP pooled DataSource.
	 */
	@Bean
	public DataSource dataSource() {
		DataSourceProperties dataSourceProperties = dataSourceProperties();
		HikariDataSource dataSource = (HikariDataSource) DataSourceBuilder.create(dataSourceProperties.getClassLoader())
				.driverClassName(dataSourceProperties.getDriverClassName()).url(dataSourceProperties.getUrl())
				.username(dataSourceProperties.getUsername()).password(dataSourceProperties.getPassword())
				.type(HikariDataSource.class).build();
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
		factoryBean.setJpaProperties(jpaProperties());
		return factoryBean;
	}
	
	/*
     * Specify any provider specific properties.
     */
    private Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("spring.jpa.properties.hibernate.dialect"));
        properties.put("hibernate.ddl.auto", environment.getRequiredProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("spring.jpa.show-sql"));
        if(StringUtils.isNotEmpty(environment.getRequiredProperty("datasource.myApp.defaultSchema"))){
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