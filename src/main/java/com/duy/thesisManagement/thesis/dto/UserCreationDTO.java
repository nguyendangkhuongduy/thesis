package com.duy.thesisManagement.thesis.dto;

import java.util.Set;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserCreationDTO {
	private String username;
	private String password;
	private String email;
	private String fullName;
	private String phone;
	private String gender;
	@NotNull
	private Integer facultyId;
	private Set<String> roles;

}
