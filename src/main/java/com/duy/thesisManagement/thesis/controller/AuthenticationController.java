package com.duy.thesisManagement.thesis.controller;

import com.duy.thesisManagement.thesis.dto.AuthenticationRequestDTO;
import com.duy.thesisManagement.thesis.dto.JwtResponse;
import com.duy.thesisManagement.thesis.dto.UserDetailsImpl;
import com.duy.thesisManagement.thesis.dto.UserCreationDTO;
import com.duy.thesisManagement.thesis.jwt.JwtUtils;
import com.duy.thesisManagement.thesis.model.AppRole;
import com.duy.thesisManagement.thesis.model.Role;
import com.duy.thesisManagement.thesis.model.User;
import com.duy.thesisManagement.thesis.repository.RoleRepository;
import com.duy.thesisManagement.thesis.repository.UserRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

		private final AuthenticationManager authenticationManager;

		private final UserRepository userRepository;

		private final RoleRepository roleRepository;

		private final JwtUtils jwtUtils;

		@PostMapping("/signin")
		public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthenticationRequestDTO request) {
				Authentication authentication = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
				SecurityContextHolder.getContext().setAuthentication(authentication);
				String jwt = jwtUtils.generateJwtToken(authentication);

				UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
				List<String> roles = userDetails.getAuthorities().stream()
						.map(item -> item.getAuthority())
						.collect(Collectors.toList());
				JwtResponse jwtResponse = JwtResponse.builder().token(jwt).username(userDetails.getUsername())
						.email(userDetails.getEmail()).roles(roles).id(userDetails.getId()).fullName(userDetails.getFullName())
						.gender(userDetails.getGender()).phone(userDetails.getPhone())
						.facultyId(userDetails.getFacultyId()).avatar(userDetails.getAvatar()).active(userDetails.isActive())
						.build();
				return ResponseEntity.ok(jwtResponse);
		}

}
