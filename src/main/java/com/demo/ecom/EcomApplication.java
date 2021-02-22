package com.demo.ecom;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Entry point to the application.
 * 
 * @author Varusai
 *
 */
@SpringBootApplication
public class EcomApplication {

	/**
	 * Allowed origin - fetched from properties file.
	 */
	@Value("${cors.allowed.origin}")
	private String allowedOrigin;

	public static void main(String[] args) {
		SpringApplication.run(EcomApplication.class, args);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public FilterRegistrationBean corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", buildCorsConfiguration());

		final FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);

		return bean;
	}

	/**
	 * Builds the {@link CorsConfiguration} object.
	 * 
	 * @return Returns the constructed cors configuration object.
	 */
	private CorsConfiguration buildCorsConfiguration() {
		final CorsConfiguration configuration = new CorsConfiguration();

		configuration.setAllowedOrigins(Collections.singletonList(allowedOrigin));
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");

		return configuration;
	}
}
