package com.movement.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import com.movement.GetMovingMainApplication;


@Configuration
public class ThymeleafConfiguration extends WebMvcConfigurerAdapter {
	protected static final Logger logger = Logger.getLogger(ThymeleafConfiguration.class); 

	
	/**
	 * THYMELEAF: View Resolver - implementation of Spring's ViewResolver interface.
	 */
	@Bean
	public ViewResolver viewResolver() {
	    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	    viewResolver.setTemplateEngine(templateEngine());
	    return viewResolver;
	}

	/**
	 * THYMELEAF: Template Engine
	 */
	@Bean
	public SpringTemplateEngine templateEngine() {
	    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	    templateEngine.addTemplateResolver(emailTemplateResolver());
	    return templateEngine;
	}

	/**
	 * THYMELEAF: Template Resolver for email templates.
	 */
	private ITemplateResolver emailTemplateResolver() {
	    TemplateResolver templateResolver = new ClassLoaderTemplateResolver();
	    templateResolver.setPrefix("mail/");
	    templateResolver.setSuffix(".html");
	    templateResolver.setTemplateMode("HTML5");
	    templateResolver.setOrder(1);
	    return templateResolver;
	}

}
