package com.duy.thesisManagement.thesis.dto;

import com.duy.thesisManagement.thesis.model.Role;
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
public class UserUpdatingDTO {
		private String email;
		private String fullName;
		private String username;
		private String phone;
		private String gender;
		private Set<String> roles;
}

