package com.demoservices.Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.sql.DataSource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demoservices.Model.CustomUser;
import com.demoservices.Model.Role;
import com.demoservices.Repository.RoleRepository;
import com.demoservices.Services.CustomUserService;

@CrossOrigin(value="*",allowedHeaders="*")
@RestController
public class CustomUserController {
	

	@Autowired
    PasswordEncoder passwordEncoder;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	CustomUserService customUserService;	
	
	@RequestMapping(value="/getOAuth2ClientSecretPasswordGrant",method=RequestMethod.GET)
	public HashMap getClientAndSecret(OAuth2Authentication auth){
		HashMap<String,String> list= new HashMap();
		list.put("password", "password");
		list.put("client", "client");
		return list;
	}
	
	
	@RequestMapping(value="/users",method=RequestMethod.GET)
	public String users(@RequestHeader("Authorization") String encoding){
		return "test";
	}
	
	@RequestMapping(value="/signup",method=RequestMethod.POST)
	public ResponseEntity<?> Signup(@Valid @RequestBody CustomUser user){
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("content-type", "application/json");

		CustomUser temp=new CustomUser();
		
		String passwordencode = passwordEncoder.encode(user.getPassword());
		
		temp.setPassword(passwordencode);
		temp.setUsername(user.getUsername());
		temp.setFirstname(user.getFirstname());
		temp.setLastname(user.getLastname());
		temp.setEnabled(false);
		
		List<Role> l=new ArrayList();
		l.add(new Role("USER"));
		
		temp.setRoles(l);
		
		UserDetails userDetails=customUserService.loadUserByUsername(user.getUsername());
		
		if(userDetails==null){
			CustomUser cu=customUserService.adduser(temp);    
			return ResponseEntity.ok().body(cu);
		}else{
			 throw new IllegalArgumentException("User already exist");
		}
	}
		
}
