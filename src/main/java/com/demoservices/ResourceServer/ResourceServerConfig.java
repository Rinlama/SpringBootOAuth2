package com.demoservices.ResourceServer;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableResourceServer
@Configuration
@RestController
public class ResourceServerConfig extends ResourceServerConfigurerAdapter  {

	
	private static final String RESOURCE_ID = "resource_id";
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID).stateless(false);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/user/getEmployeesList")
//        .hasAnyRole("ADMIN").anyRequest().authenticated().and().formLogin()
//        .permitAll().and().logout().permitAll();


//        http.csrf().disable().cors().and().authorizeRequests().
//        antMatchers(HttpMethod.OPTIONS, "/oauth/token")
//        .permitAll()
//                .antMatchers("/addprojects","/deleteprojects/**","/users")
//                .authenticated().antMatchers("/").permitAll().and()
//                .exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
		
		  http.csrf().disable().cors().and().authorizeRequests().
	        antMatchers("/swagger-ui.html/**","/search/**","/uploads/**","/signup","/getOAuth2ClientSecretPasswordGrant","/projects/**","/projectsbypageable/**")
	        .permitAll()
	                .antMatchers("/updateprojects/**","/addprojects","/deleteprojects/**","/users").hasAnyAuthority("USER")
	                .anyRequest().authenticated().antMatchers("/").permitAll().and()
	                .exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
		
		//http.authorizeRequests().anyRequest().permitAll();
	}
	
	@GetMapping("/validateuser")
	public Principal user(Principal user){
		return user;
	}
	
	
}
