package com.study.blog.model;

import javax.validation.constraints.NotEmpty;

import com.study.blog.validator.PasswordMatches;
import com.study.blog.validator.ValidEmail;

/**
 * The user DTO
 * @author Beom
 *
 */
@PasswordMatches
public class UserDto {
    @NotEmpty(message = "First name may not be empty")
    private String firstName;
    
    @NotEmpty(message = "Last name may not be empty")
    private String lastName;
    
    @NotEmpty(message = "Password may not be empty")
    private String password;
    
    private String matchingPassword;
    
    @ValidEmail
    @NotEmpty(message = "Email may not be empty")
    private String email;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}