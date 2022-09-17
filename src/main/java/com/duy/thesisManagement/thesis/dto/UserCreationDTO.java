package com.duy.thesisManagement.thesis.dto;

import java.util.Date;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreationDTO {
	private String username;
	private String password;
	private String email;
	private Boolean active;
	private String fullName;
	private String phone;
	private String gender;
	private Date createdDate;
	private Integer facultyId;
	private Set<String> roles;

}
