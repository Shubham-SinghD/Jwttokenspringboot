package com.example.demo.scurity;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LoginData;
import com.example.demo.repo.LoginRepo;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private LoginRepo loginRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<LoginData> byEmailIgnoreCase = loginRepo.findByEmailIgnoreCase(email);
		LoginData loginData = byEmailIgnoreCase.get();
		return new UserPrinciple(loginData);
	}
	


}
