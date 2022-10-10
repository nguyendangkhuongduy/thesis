package com.duy.thesisManagement.thesis.repository;

import com.duy.thesisManagement.thesis.model.Score;
import com.duy.thesisManagement.thesis.model.ScoreDetail;
import com.duy.thesisManagement.thesis.model.Thesis;
import com.duy.thesisManagement.thesis.model.ThesisPosition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreDetailRepository extends JpaRepository<ScoreDetail, Integer> {
    List<ScoreDetail> findByScore(Score id);
}
