package com.demo.ecom;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.demo.ecom.models.Product;
import com.demo.ecom.repository.ProductRepository;

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

	@Bean
	public CommandLineRunner demo(ProductRepository repository) {
		return (args) -> {
			// save a few products
			repository.save(new Product("Product 1", 500.0, 4, "Product 1 description", 20));
			repository.save(new Product("Product 2", 1300.0, 2, "Product 2 description", 200));
			repository.save(new Product("Product 3", 700.0, 3, "Product 3 description", 70));
			repository.save(new Product("Product 4", 640.0, 3, "Product 4 description", 10));
			repository.save(new Product("Product 5", 1100.0, 5, "Product 5 description", 0));
		};
	}
}
