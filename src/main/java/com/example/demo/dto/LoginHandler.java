package com.example.demo.dto;


import lombok.Data;

@Data
public class LoginHandler {
	private String email;
	private long mobile;
	private String password;
}
