package com.duy.thesisManagement.thesis.repository;

import com.duy.thesisManagement.thesis.model.Criteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CriteriaRepository extends JpaRepository<Criteria, Integer> {
    @Query("SELECT c FROM Criteria c WHERE c.active = true")
    List<Criteria> findByActiveTrue();
}
