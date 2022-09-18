package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.model.Role;
import java.util.List;
import java.util.Set;

public interface RoleService {

		List<Role> getRoleByNames(Set<String> name);
}
