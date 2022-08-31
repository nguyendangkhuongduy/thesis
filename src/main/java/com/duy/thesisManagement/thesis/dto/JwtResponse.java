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
		private String token;
		private String username;
		private String email;
		private List<String> roles;
}
