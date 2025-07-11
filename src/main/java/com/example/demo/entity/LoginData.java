package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@Table(name="shubham_Login_Data")
@AllArgsConstructor
@NoArgsConstructor
public class LoginData {

	
	@Id
	private String email;
	private String name;
	@Column(nullable = false,unique = true)
	private Long mobile;
	private String password;
	private String role;
	
	

}
