package com.study.blog.model;

import javax.validation.constraints.NotEmpty;

import com.study.blog.validator.PasswordMatches;
import com.study.blog.validator.ValidEmail;
import com.sun.istack.NotNull;

/**
 * The user DTO
 * @author Beom
 *
 */
@PasswordMatches
public class UserDto {
    @NotNull
    @NotEmpty
    private String firstName;
    
    @NotNull
    @NotEmpty
    private String lastName;
    
    @NotNull
    @NotEmpty
    private String password;
    
    private String matchingPassword;
    
    @ValidEmail
    @NotNull
    @NotEmpty
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