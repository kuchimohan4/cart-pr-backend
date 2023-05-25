package com.drivein.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.drivein.entity.userCredentials;
import com.drivein.repository.userCreantialsRepositry;

@Service
public class AuthService {
	
	@Autowired
	private userCreantialsRepositry userCreantialsRepositry;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private jwtService jwtService;
	public String saveUser(userCredentials userCredentials) {
		userCredentials.setPassword(passwordEncoder.encode(userCredentials.getPassword()));
		userCreantialsRepositry.save(userCredentials);
		return "user added sucessfully";
		
	}
	
	
	public String genarateToken(String userName) {
		
		return jwtService.genarateToken(userName);
		
		
	}
	
	public void validateToken(String token) {
		
		jwtService.validateToken(token);
	}
	
	

}
