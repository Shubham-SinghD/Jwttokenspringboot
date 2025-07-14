package com.example.demo.service;
import java.util.Optional;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.dto.LoginHandler;
import com.example.demo.entity.LoginData;
import com.example.demo.repo.LoginRepo;

@Service
public class EmployeeService {
	
	
	@Autowired
	private LoginRepo loginRepo;
	
	@Autowired
	AuthenticationManager authenticationManager;
	

	public LoginData validateLogin(LoginHandler loginHandler) throws AuthenticationException {
	    Authentication authentication = authenticationManager.authenticate(
		    new UsernamePasswordAuthenticationToken(
		        loginHandler.getEmail(), 
		        loginHandler.getPassword()
		    )
		);

		if (authentication.isAuthenticated()) {
		    Optional<LoginData> optionalLoginData = loginRepo.findByEmailIgnoreCase(loginHandler.getEmail());
		    return optionalLoginData.orElse(null); 
		}

	    return null; 
	}

	
	
	
	//Registration
	private BCryptPasswordEncoder en=new BCryptPasswordEncoder();
	public LoginData register(LoginData data) {
		data.setPassword(en.encode(data.getPassword()));
		return loginRepo.save(data);
		
	}

}
