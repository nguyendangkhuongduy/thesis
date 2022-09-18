package com.duy.thesisManagement.thesis.repository;

import com.duy.thesisManagement.thesis.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

		Optional<User> findByUsername(String username);

		Boolean existsByUsername(String username);

		Boolean existsByEmail(String email);
}
