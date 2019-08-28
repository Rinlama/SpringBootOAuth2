package com.demoservices.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.demoservices.Model.CustomUser;

public interface UserRepository extends JpaRepository<CustomUser, String> {

	 UserDetails findByUsername(String Username);
}
