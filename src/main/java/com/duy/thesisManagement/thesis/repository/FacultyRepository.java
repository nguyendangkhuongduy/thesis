package com.duy.thesisManagement.thesis.repository;

import com.duy.thesisManagement.thesis.model.Faculty;
import com.duy.thesisManagement.thesis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacultyRepository  extends JpaRepository<Faculty, Integer> {
    Boolean existsByName(String name);

    @Query("SELECT c FROM Faculty c WHERE c.active = true")
    List<Faculty> findByActiveTrue();
}
