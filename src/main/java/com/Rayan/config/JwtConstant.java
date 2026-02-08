package com.Rayan.config;


public class JwtConstant {

	public static final String SECRET_KEY=System.getenv("JWT_SECRET");
	
	public static final String JWT_HEADER="Authorization";
}
