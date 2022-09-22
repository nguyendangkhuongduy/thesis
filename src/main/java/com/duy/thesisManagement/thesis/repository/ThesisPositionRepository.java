package com.duy.thesisManagement.thesis.repository;

import com.duy.thesisManagement.thesis.dto.ThesisPositionDTO;
import com.duy.thesisManagement.thesis.model.ThesisPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThesisPositionRepository extends JpaRepository<ThesisPosition, Integer> {
    ThesisPosition findByUserId(Integer id);

    ThesisPositionDTO existsByUserId(Integer id);
}
