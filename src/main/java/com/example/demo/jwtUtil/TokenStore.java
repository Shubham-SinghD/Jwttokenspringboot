package com.example.demo.jwtUtil;

import java.util.HashSet;
import java.util.Set;

public class TokenStore {
	
	private static final Set<String>BlackListToken=new HashSet<String>();
	
	public static void addBlackList(String token) {
		BlackListToken.add(token);
	}
	
	public static boolean isToken(String token) {
		return BlackListToken.contains(token);
	}
	public static Set<String> getBlackListToken() {
		return BlackListToken;
	}
	

}
