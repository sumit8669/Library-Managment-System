package com.lsm.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class User {

	private int id;

	@NotBlank(message = "name is required")
	@Size(min = 3, max = 30, message = "min 3 and max 30 characters are allowed")
	private String name;

	@Email(message="Please provide a valid email address and must contain @ .")
	@Pattern(regexp=".+@.+\\..+", message="email must contain @ .")
	private String email;

	
	@Size(min = 4, max = 20, message = "password length must be 4 character long")
	@NotNull(message = "password is required")
	private String password;

	private String role;

	private String about;

	public User(int id, String name, String email, String password, String role, String about) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.about = about;
	}

	public User() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
				+ ", about=" + about + "]";
	}

}
