package com.example.demo.controller;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.LoginHandler;
import com.example.demo.entity.LoginData;
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
	public Map<String, Object> getToken(@RequestBody LoginHandler handler) throws AuthenticationException {
		 LoginData validateLogin = employeeService.validateLogin(handler);
		 System.err.println(validateLogin);
		 String token = JwtUtil.generateToken(handler.getEmail());
		 Map<String , Object>mp=new HashMap<String, Object>();
		 mp.put("Data", validateLogin);
		 mp.put("Token", token);
		 return mp;
	}
	@PostMapping("/register")
	public LoginData getRegister(@RequestBody LoginData  loginData) {
		return employeeService.register(loginData);
	}

	
	@GetMapping("/block")
	public Set<String> getBlack() {
		return TokenStore.getBlackListToken();
	}
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
}
