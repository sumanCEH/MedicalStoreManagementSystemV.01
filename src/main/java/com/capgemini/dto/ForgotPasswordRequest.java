package com.capgemini.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ForgotPasswordRequest {
	
	@Email
	@NotBlank(message="username should not be blank")
	private String userName;
	
	@NotBlank(message="password should not be blank")
	private String password;
	
	@NotBlank(message="confirm password should not be blank")
	private String confirmPassword;


}
