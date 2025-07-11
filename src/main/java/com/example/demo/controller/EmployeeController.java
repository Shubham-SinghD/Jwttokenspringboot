package com.example.demo.controller;


import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginHandler;
import com.example.demo.jwtUtil.JwtUtil;
import com.example.demo.jwtUtil.TokenStore;
import com.example.demo.service.EmployeeService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService; 
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> getToken(@RequestBody LoginHandler handler) {
		 String validateLogin = employeeService.validateLogin(handler);
		 System.err.println(validateLogin);
		 TokenStore.addBlackList(validateLogin);
		 return new  ResponseEntity<Map<String, String>>(Map.of("Status",validateLogin),HttpStatus.OK);
	}

	
	@GetMapping("/secure-data")
	public ResponseEntity<Object> secureData(@RequestHeader("Authorization") String authHeader) {
		String token=authHeader.replace("Bearer","");
		if(JwtUtil.validateToken(token)) {
			return new ResponseEntity<Object>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping("/black")
	public Set<String> getBlack() {
		return TokenStore.getBlackListToken();
	}
	
}
