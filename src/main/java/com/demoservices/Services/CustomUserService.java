package com.demoservices.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demoservices.Model.CustomUser;
import com.demoservices.Repository.UserRepository;

@Service
public class CustomUserService implements UserDetailsService {
	
	@Autowired
	UserRepository userrepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return userrepository.findByUsername(username);
	}
	
	public CustomUser adduser(CustomUser user){
		return userrepository.save(user);
	}


}
