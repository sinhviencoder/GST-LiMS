package com.lims.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@SuppressWarnings("deprecation")
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Bean
    public SpringSecurityDialect springSecurityDialect(){
        return new SpringSecurityDialect();
    }
	
	// Custom config messages validate form
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource bean = new ReloadableResourceBundleMessageSource();
		bean.addBasenames("classpath:messages");
		return bean;
	}

	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		/*
		 * Resources controlled by Spring Security, which adds
		 * "Cache-Control: must-revalidate".
		 */
		// registry.addResourceHandler("/**").addResourceLocations("/public/").setCachePeriod(3600
		// * 24);

		registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
		registry.addResourceHandler("/**").addResourceLocations("/WEB-INF/view/oss/", "/WEB-INF/view/oss/demos/construction/", "/WEB-INF/view/customs/");
		registry.addResourceHandler("/admin/oss/**").addResourceLocations("/WEB-INF/admin/oss/");
		registry.addResourceHandler("/admin/customs/**").addResourceLocations("/WEB-INF/admin/customs/");

	}

}
