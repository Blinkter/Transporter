package transporter.conf;

import java.util.Locale;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.FormatterRegistry;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import transporter.converter.LocalDateConverter;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "transporter" })
@EnableWebMvc
@EnableJpaRepositories(basePackages = "transporter")
public class AppConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public LocalEntityManagerFactoryBean entityManagerFactory() {
		final LocalEntityManagerFactoryBean emfb = new LocalEntityManagerFactoryBean();
		emfb.setPersistenceUnitName("transporterPersistenceUnit");
		return emfb;
	}

	@Bean
	public JpaTransactionManager transactionManager(final EntityManagerFactory emf) {
		final JpaTransactionManager tm = new JpaTransactionManager(emf);
		return tm;
	}

	@Bean
	public ViewResolver viewResolver() {
		final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean(name = "localeResolver")
	public LocaleContextResolver getLocaleContextResolver() {

		final SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(new Locale("pl", "PL"));

		return localeResolver;
	}
	
	@Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new LocalDateConverter("yyyy-MM-dd"));
    }
	/*
	@Bean
	public Validator validator() {
		return new LocalValidatorFactoryBean();
	}

	@Override
	public void addFormatters(final FormatterRegistry registry) {
		registry.addConverter(authorConverter());
	}

	@Bean
	public AuthorConverter authorConverter() {
		return new AuthorConverter();
	}*/

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("/resources/**")
			.addResourceLocations("/WEB-INF/resources/");
	}
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
}