package com.duy.thesisManagement.thesis.dto;

import java.util.Date;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
		private Integer id;
		private String username;
		private String email;
		private boolean active;
		private String fullName;
		private String phone;
		private String gender;
		private Date createdDate;
		private Set<String> roles;
		private String faculty;
}
