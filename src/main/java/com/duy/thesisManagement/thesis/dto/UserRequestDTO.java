package com.duy.thesisManagement.thesis.dto;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {
	private String username;
	private String password;
	private String email;
	private Set<String> roles;
}
