package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dto.ScoreDTO;
import com.duy.thesisManagement.thesis.dto.ThesisRequestDTO;
import com.duy.thesisManagement.thesis.dto.UserCreationDTO;
import com.duy.thesisManagement.thesis.model.*;
import com.duy.thesisManagement.thesis.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService{

    private final ScoreRepository scoreRepository;

    private final ThesisService thesisService;
    private final CouncilPositionService councilPositionService;

    @Override
    public ScoreDTO createdScore(ScoreDTO scoreDTO) {
        Score score = this.toScore(scoreDTO);
        Score savedScore = this.scoreRepository.save(score);
        return scoreDTO;
    }

    @Override
    public void deleteScore(ScoreDTO scoreDTO) {

    }

    @Override
    public ScoreDTO updateScore(ScoreDTO scoreDTO) {
        return null;
    }

    private Score toScore(ScoreDTO scoreDTO) {
        Thesis thesis = null;
        CouncilPosition councilPosition = null;
        if (Objects.nonNull(scoreDTO.getThesisId())) {
            thesis = this.thesisService.getThesisByID(scoreDTO.getThesisId());
        }

        if (Objects.nonNull(scoreDTO.getCouncilPositionId())) {
            councilPosition = this.councilPositionService.getCouncilPositionByCouncilId(scoreDTO.getThesisId());
        }
        Score score = Score.builder()
                .councilPositionId(councilPosition)
                .thesisId(thesis)
                .build();
        return score;
    }
}
