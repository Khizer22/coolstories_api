package com.coolstories.projectstories;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;

@SpringBootApplication
public class ProjectStoriesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectStoriesApiApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();

		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);

		registrationBean.setFilter(new CorsFilter(source));
		registrationBean.setOrder(0);

		return registrationBean;
	}

}
