package com.duy.thesisManagement.thesis.repository;

import com.duy.thesisManagement.thesis.model.Score;
import com.duy.thesisManagement.thesis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Integer> {
    @Query("SELECT c FROM Score c WHERE c.active = true")
    List<Score> findByActiveTrue();

}
