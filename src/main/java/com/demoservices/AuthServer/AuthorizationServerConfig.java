package com.demoservices.AuthServer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
		@Autowired(required=true)
		@Qualifier(value="authenticationManagerBean")
		private AuthenticationManager authenticationManager;
	   
		@Value("${varun.oauth.tokenTimeout:3600}")
		private int expiration;
	   
	   @Autowired
	   private TokenStore tokenStore;
	   
	   @Autowired
	   PasswordEncoder passwordEncoder;
	   
	   @Autowired
	   DataSource datasource;
	   
	   
		@Override
		public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
			security.tokenKeyAccess("permitAll()")
			.checkTokenAccess("isAuthenticated()");
		}
	 @Override
	 public void configure (ClientDetailsServiceConfigurer clients) throws Exception {
	 clients.jdbc(datasource);
//	     clients.jdbc(datasource).withClient("client")
//	             .authorizedGrantTypes ("password", "authorization_code", "refresh_token", "implicit")
//	             .authorities ("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT", "USER")
//	             .scopes ("read", "write")
//	             .autoApprove (true)        
//	             .secret (passwordEncoder. encode ("password")) .accessTokenValiditySeconds(3600)
//	             .and().build();          
	 }
	 
	  @Override
	  public void configure (AuthorizationServerEndpointsConfigurer endpoints) throws Exception {	  
		  
		  endpoints
	              .authenticationManager (authenticationManager)        
	              .tokenStore (tokenStore);
	  }
	 
	

}



