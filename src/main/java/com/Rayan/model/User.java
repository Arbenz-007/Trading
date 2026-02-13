package com.Rayan.model;

import com.Rayan.domain.USER_ROLE;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long  id;
	
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	private String fullName;
	
	
	private String email;
	
	
	@Embedded
	private TwoFactorAuth twoFactorAuth = new TwoFactorAuth();
	
	private USER_ROLE role= USER_ROLE.ROLE_CUSTOMER;

	public User(Long id, String password, String fullName, String email, TwoFactorAuth twoFactorAuth, USER_ROLE role) {
		super();
		this.id = id;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.twoFactorAuth = twoFactorAuth;
		this.role = role;
	}

	public User() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TwoFactorAuth getTwoFactorAuth() {
		return twoFactorAuth;
	}

	public void setTwoFactorAuth(TwoFactorAuth twoFactorAuth) {
		this.twoFactorAuth = twoFactorAuth;
	}

	public USER_ROLE getRole() {
		return role;
	}

	public void setRole(USER_ROLE role) {
		this.role = role;
	}
	
	
}
