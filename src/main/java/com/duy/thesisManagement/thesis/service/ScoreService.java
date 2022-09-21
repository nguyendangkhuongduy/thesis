package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dto.ScoreDTO;
import com.duy.thesisManagement.thesis.dto.ThesisRequestDTO;
import com.duy.thesisManagement.thesis.dto.ThesisUpdatingDTO;

import java.util.List;

public interface ScoreService {

    ScoreDTO createdScore(ScoreDTO scoreDTO);

    void deleteScore(ScoreDTO scoreDTO);

    ScoreDTO updateScore(ScoreDTO scoreDTO);
}
