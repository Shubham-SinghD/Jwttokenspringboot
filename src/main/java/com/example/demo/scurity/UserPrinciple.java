package com.example.demo.scurity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.LoginData;

import lombok.Data;

@Data
public class UserPrinciple implements UserDetails {

	private static final long serialVersionUID = 1L;
	@Autowired
	LoginData data;
	
	public UserPrinciple(LoginData loginData) {
		this.data=loginData;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority(data.getRole()));
	}

	@Override
	public String getPassword() {
		return data.getPassword();
	}

	@Override
	public String getUsername() {
		return data.getEmail();
	}

}
