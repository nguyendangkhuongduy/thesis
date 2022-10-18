package com.duy.thesisManagement.thesis.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JwtResponse {
		private Integer id;
		private String token;
		private String username;
		private String email;
		private String fullName;
		private String gender;
		private String phone;
		private Integer facultyId;
		private List<String> roles;
		private String avatar;
		private Boolean active;
}
