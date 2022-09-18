package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.exception.BadRequestException;
import com.duy.thesisManagement.thesis.model.AppRole;
import com.duy.thesisManagement.thesis.model.Role;
import com.duy.thesisManagement.thesis.repository.RoleRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

		private final RoleRepository roleRepository;

		@Override
		public List<Role> getRoleByNames(Set<String> names) {
				Set<AppRole> roles = new HashSet<>();
				for (String roleName : names) {
						Optional<AppRole> foundRole = AppRole.fromValue(roleName);
						if (!foundRole.isPresent()) {
								throw new BadRequestException("The role with name: " + roleName + " has not supported in the project");
						}
						roles.add(foundRole.get());
				}
				return this.roleRepository.findByNameIn(roles);
		}
}
