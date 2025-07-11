package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.LoginHandler;
import com.example.demo.entity.LoginData;
import com.example.demo.jwtUtil.JwtUtil;
import com.example.demo.repo.LoginRepo;

@Service
public class EmployeeService {
//	@Autowired
//	private EmployeeRepo employeeRepo;
	@Autowired
	private LoginRepo loginRepo;
	
	public String validateLogin(LoginHandler 
			LoginHand) {
	    Optional<LoginData> user = loginRepo.findByEmailIgnoreCase(LoginHand.getEmail());
	    if (user.isPresent()) {
	    	LoginData loginUser = user.get();
	        if (!loginUser.getPassword().equals(LoginHand.getPassword())) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Incorrect Password");
	        }
	        return JwtUtil.generateToken(LoginHand.getEmail());
	    } else {
	        return validateMobile(LoginHand);
	    }
	}
	

	// Mobile Validation 
	public String  validateMobile(LoginHandler Loginhand) {
		LoginData byMobile = loginRepo.findByMobile(Loginhand.getMobile());
		if(byMobile==null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Incorrect Mobile Number ");
		}
		if(!byMobile.getPassword().equals(Loginhand.getPassword())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Incorrect Password ");
		}
		return JwtUtil.generateToken(String.valueOf(Loginhand.getMobile()));
	
	}

}
