package com.duy.thesisManagement.thesis.repository;

import com.duy.thesisManagement.thesis.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface ScoreRepository extends JpaRepository<Score, Integer> {
}
