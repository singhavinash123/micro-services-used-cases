package com.eureka.zuul.constants;

public class SecurityConstants {
	private SecurityConstants() {
		throw new IllegalStateException("Utility class");
	}
	
	public static String getTokenSecret() {
		return RESET_PASSWORD;
	}
	
	public static final long PASSWORD_RESTE_EXPIRATION_TIME = 120000;
	public static final String SECRET = "SecretKeyToGenJWTs";
	public static final String RESET_PASSWORD = "SecretKeyToGenJWTs";
	public static final long EXPIRATION_TIME = 864_000_000; // 10 days
	
	public static final String HEADER_STRING = "Authorization";
	public static final String BEARER_PREFIX = "Bearer ";
}
