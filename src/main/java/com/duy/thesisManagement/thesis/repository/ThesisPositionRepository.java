package com.duy.thesisManagement.thesis.repository;

import com.duy.thesisManagement.thesis.dto.ThesisPositionDTO;
import com.duy.thesisManagement.thesis.model.Thesis;
import com.duy.thesisManagement.thesis.model.ThesisPosition;
import com.duy.thesisManagement.thesis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThesisPositionRepository extends JpaRepository<ThesisPosition, Integer> {
    ThesisPosition findByUserId(Integer id);

    ThesisPositionDTO existsByUserId(Integer id);

    List<ThesisPosition> findByThesisId(Thesis id);

    List<ThesisPosition> findByUserId(User id);
}
