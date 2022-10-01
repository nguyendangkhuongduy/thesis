package com.duy.thesisManagement.thesis.repository;

import com.duy.thesisManagement.thesis.dto.UserDTO;
import com.duy.thesisManagement.thesis.model.AppRole;
import com.duy.thesisManagement.thesis.model.User;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

		Optional<User> findByUsername(String username);

		Boolean existsByUsername(String username);

		Boolean existsByEmail(String email);

		@Query("SELECT c FROM User c WHERE c.active = true")
		List<User> findByActiveTrue();

//		@Query("SELECT c FROM User c WHERE c.roles = ROLE_ASSOCIATE")
//		List<User> findAllByRoles();

		List<User> findByRoles_Name(AppRole roleName);
}
