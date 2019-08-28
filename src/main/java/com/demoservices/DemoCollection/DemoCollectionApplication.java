package com.demoservices.DemoCollection;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.demoservices.ResourceServer.SimpleCorsFilter;

@SpringBootApplication
@ComponentScan("com.demoservices")
@EntityScan("com.demoservices")
@EnableJpaRepositories("com.demoservices")
@Configuration
@EnableConfigurationProperties({
    com.demoservices.FileStorage.FileStorageProperties.class
})
public class DemoCollectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoCollectionApplication.class, args);
	}

	  
}
	
	
