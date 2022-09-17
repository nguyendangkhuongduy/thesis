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

		private final PasswordEncoder encoder;

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
						.email(userDetails.getEmail()).roles(roles).build();
				return ResponseEntity.ok(jwtResponse);
		}

		@PostMapping("/signup")
		public ResponseEntity<?> registerUser(@Valid @RequestBody UserCreationDTO userCreationDTO) {
				if (userRepository.existsByUsername(userCreationDTO.getUsername())) {
						return ResponseEntity
								.badRequest()
								.body("Error: Username is already taken!");
				}
				if (userRepository.existsByEmail(userCreationDTO.getEmail())) {
						return ResponseEntity
								.badRequest()
								.body(new ErrorMessage("Error: Email is already in use!"));
				}
				// Create new user's account
				User user = User.builder().username(userCreationDTO.getUsername())
						.email(userCreationDTO.getEmail())
						.password(encoder.encode(userCreationDTO.getPassword())).fullName(userCreationDTO.getFullName())
						.phone(userCreationDTO.getPhone()).gender(userCreationDTO.getGender())
						.createdDate(userCreationDTO.getCreatedDate())
						.active(userCreationDTO.getActive()).build();



				Set<String> strRoles = userCreationDTO.getRoles();
				Set<Role> roles = new HashSet<>();
				if (strRoles == null) {
						return ResponseEntity.badRequest().body(new ErrorMessage("Missing roles in request body"));
				} else {
						// FIXME refactor it, encapsulate logic inside Enum
						strRoles.forEach(role -> {
								switch (role) {
										case "admin":
												Role adminRole = roleRepository.findByName(AppRole.ROLE_ADMIN)
														.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
												roles.add(adminRole);
												break;
										case "manager":
												Role modRole = roleRepository.findByName(AppRole.ROLE_MANAGER)
														.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
												roles.add(modRole);
												break;
										case "associate":
												Role associate = roleRepository.findByName(AppRole.ROLE_ASSOCIATE)
														.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
												roles.add(associate);
												break;
								}
						});
				}
				user.setRoles(roles);
				userRepository.save(user);
				return ResponseEntity.ok(new ErrorMessage("User registered successfully!"));
		}

}
