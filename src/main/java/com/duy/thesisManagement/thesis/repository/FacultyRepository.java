package com.duy.thesisManagement.thesis.repository;

import com.duy.thesisManagement.thesis.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FacultyRepository  extends JpaRepository<Faculty, Integer> {
    Boolean existsByName(String name);
}
