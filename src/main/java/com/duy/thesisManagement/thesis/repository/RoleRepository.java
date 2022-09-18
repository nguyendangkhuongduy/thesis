package com.duy.thesisManagement.thesis.repository;

import com.duy.thesisManagement.thesis.model.AppRole;
import com.duy.thesisManagement.thesis.model.Role;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
		Optional<Role> findByName(AppRole name);

		List<Role> findByNameIn(Set<AppRole> names);
}
