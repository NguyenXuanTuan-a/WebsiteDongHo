package com.nguyenxuantuan.shopdongho.project.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordGenerator {
private static final int strength = 12;
	
	public static String getHashString(String input) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(strength);
		String hashedPassword = passwordEncoder.encode(input);

		return hashedPassword;
	}
	
	public static boolean checkHashStrings(String rawPassword, String encodedPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(strength);
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}
}
