package com.daloji.tracker;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class TrackerApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(TrackerApplication.class, args);
	}

	  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(TrackerApplication.class);
	    }

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames("messages", "messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
}
