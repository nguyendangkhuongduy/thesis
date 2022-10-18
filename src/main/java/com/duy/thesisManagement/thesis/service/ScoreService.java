package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dto.*;

import java.util.List;

public interface ScoreService {

    ScoreDTO createdScore(ScoreCreationDTO scoreCreationDTO);

    void deleteScore(Integer id);

    ScoreDTO updateScore(ScoreDTO scoreDTO);

    List<ScoreDTO> getScores();

    List<ScoreDTO> getScoreByThesisId(Integer id);
}
