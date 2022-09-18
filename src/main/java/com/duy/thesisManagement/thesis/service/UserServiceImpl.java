package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dto.UserCreationDTO;
import com.duy.thesisManagement.thesis.dto.UserDTO;
import com.duy.thesisManagement.thesis.dto.UserUpdatingDTO;
import com.duy.thesisManagement.thesis.exception.BadRequestException;
import com.duy.thesisManagement.thesis.exception.ResourceNotFoundException;
import com.duy.thesisManagement.thesis.model.AppRole;
import com.duy.thesisManagement.thesis.model.Faculty;
import com.duy.thesisManagement.thesis.model.Role;
import com.duy.thesisManagement.thesis.model.User;
import com.duy.thesisManagement.thesis.repository.UserRepository;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

		private final UserRepository userRepository;
		private final RoleService roleService;
		private final FacultyService facultyService;
		private final PasswordEncoder encoder;

		@Override
		public List<UserDTO> getAllUsers() {
				List<User> users = userRepository.findAll();
				List<UserDTO> result = users.stream().map(this::toUserDTO).collect(Collectors.toList());
				return result;
		}

		@Override
		public UserDTO getUserById(Integer id) {
				Optional<User> user = this.userRepository.findById(id);
				if (user.isPresent()) {
						return this.toUserDTO(user.get());
				}
				throw new ResourceNotFoundException("Cannot find any user with id: " + id);
		}

		@Override
		public UserDTO createUser(UserCreationDTO userCreationDTO) {
				this.validateNewUser(userCreationDTO);
				User user = this.toUser(userCreationDTO);
				User savedUser = this.userRepository.save(user);
				return toUserDTO(savedUser);
		}

		private void validateNewUser(UserCreationDTO userCreationDTO) {
				boolean usernameExisted = this.userRepository.existsByUsername(userCreationDTO.getUsername());
				boolean emailExisted = this.userRepository.existsByEmail(userCreationDTO.getEmail());
				StringBuilder errorMessageBuilder = new StringBuilder();
				if (usernameExisted) {
						errorMessageBuilder.append("Cannot create user: username alredy existed");
				}
				if (emailExisted) {
						errorMessageBuilder.append(" and ");
						errorMessageBuilder.append("Cannot create user: email already existed");
				}
				String errorMessage = errorMessageBuilder.toString();
				if (StringUtils.isNotBlank(errorMessage)) {
						throw new BadRequestException(errorMessage);
				}
		}

		@Override
		public UserDTO updateUser(Integer id, UserUpdatingDTO userUpdatingDTO) {
				Optional<User> foundUser = this.userRepository.findById(id);
				if (foundUser.isPresent()) {
							User updatingUser = foundUser.get();
							updatingUser.setEmail(userUpdatingDTO.getEmail());
							updatingUser.setFullName(userUpdatingDTO.getFullName());
							updatingUser.setGender(userUpdatingDTO.getGender());
							updatingUser.setPhone(userUpdatingDTO.getPhone());

							Set<Role> roles = this.getExistingRoles(userUpdatingDTO.getRoles());
							updatingUser.setRoles(roles);

							User savedUser = this.userRepository.save(updatingUser);
							return this.toUserDTO(savedUser);
				}
				throw new ResourceNotFoundException("Cannot find any user for Update action with id: " + id);
		}

		@Override
		public void deleteUser(Integer id) {
				Optional<User> userOpt = this.userRepository.findById(id);
				if (userOpt.isPresent()) {
						User deletedUser = userOpt.get();
						deletedUser.setActive(false);
						this.userRepository.save(deletedUser);
						return;
				}
				throw new ResourceNotFoundException("Cannot find any user for deleting action with id: " + id);
		}

		@Override
		public void changePassword(Integer id, String newPassword) {
				if (StringUtils.isBlank(newPassword)) {
						throw new BadRequestException("New password cannot be blank");
				}

				Optional<User> userOpt = this.userRepository.findById(id);
				if (userOpt.isPresent()) {
						User existingUser = userOpt.get();
						String newEncodedPassword = this.encoder.encode(newPassword);
						if (newEncodedPassword.equals(existingUser.getPassword())) {
								throw new BadRequestException("You cannot change password with the same existing password. Please input the different value");
						}
						existingUser.setPassword(newEncodedPassword);
						this.userRepository.save(existingUser);
						return;
				}
				throw new ResourceNotFoundException("Cannot find any user for changing password action with id: " + id);
		}

		private UserDTO toUserDTO(User user) {
				UserDTO userDTO = UserDTO.builder()
						.id(user.getId())
						.username(user.getUsername())
						.fullName(user.getFullName())
						.active(user.isActive())
						.createdDate(user.getCreatedDate())
						.gender(user.getGender())
						.phone(user.getPhone())
						.roles(user.getRoles().stream().map(Role::getName).map(AppRole::getName).collect(Collectors.toSet()))
						.email(user.getEmail()).build();
				return userDTO;
		}

		private User toUser(UserCreationDTO userCreationDTO) {
				Set<Role> existingRoles = this.getExistingRoles(userCreationDTO.getRoles());
				Faculty faculty = null;
				if (Objects.nonNull(userCreationDTO.getFacultyId())) {
						faculty = this.facultyService.getFacultyById(userCreationDTO.getFacultyId());
				}
				String encodedPass = this.encoder.encode(userCreationDTO.getPassword());
				User user = User.builder()
						.active(userCreationDTO.getActive())
						.email(userCreationDTO.getEmail())
						.fullName(userCreationDTO.getFullName())
						.gender(userCreationDTO.getGender())
						.password(encodedPass)
						.phone(userCreationDTO.getPhone())
						.roles(existingRoles)
						.username(userCreationDTO.getUsername())
						.faculty(faculty)
						.build();
				return user;
		}

		private Set<Role> getExistingRoles(Set<String> roles) {
				if (CollectionUtils.isEmpty(roles)) {
					throw new BadRequestException("Cannot create user without any roles");
				}
				List<Role> foundRoles = this.roleService.getRoleByNames(roles);

				if (roles.size() != foundRoles.size()) {
						String errorMessage = MessageFormat.format(
								"Cannot create user because not all given roles found. Given {0} roles, but found only {1} roles",
								roles.size(), foundRoles.size());
						throw new ResourceNotFoundException(errorMessage);
				}

				return new HashSet(foundRoles);
		}
}
