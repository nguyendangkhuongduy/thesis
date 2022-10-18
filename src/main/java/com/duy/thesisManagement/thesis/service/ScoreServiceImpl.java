package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dto.ScoreCreationDTO;
import com.duy.thesisManagement.thesis.dto.ScoreDTO;
import com.duy.thesisManagement.thesis.dto.ThesisRequestDTO;
import com.duy.thesisManagement.thesis.dto.UserCreationDTO;
import com.duy.thesisManagement.thesis.exception.ResourceNotFoundException;
import com.duy.thesisManagement.thesis.model.*;
import com.duy.thesisManagement.thesis.repository.CouncilPositionRepository;
import com.duy.thesisManagement.thesis.repository.ScoreRepository;
import com.duy.thesisManagement.thesis.repository.ThesisRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Literal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService{

    private final ScoreRepository scoreRepository;

    private final ThesisRepository thesisRepository;
    private final CouncilPositionRepository councilPositionRepository;

    private final ThesisService thesisService;
    private final CouncilPositionService councilPositionService;

    @Override
    public ScoreDTO createdScore(ScoreCreationDTO scoreCreationDTO) {
        Score score = this.toScore(scoreCreationDTO);
        score.setActive(true);
        Score saved = this.scoreRepository.save(score);
        return toScoreDTO(saved);
    }

    @Override
    public void deleteScore(Integer id) {
        Optional<Score> scoreOpt = this.scoreRepository.findById(id);
        if (scoreOpt.isPresent()) {
            Score deleted = scoreOpt.get();
            deleted.setActive(false);
            this.scoreRepository.save(deleted);
            return;
        }
        throw new ResourceNotFoundException("Cannot find any score for deleting action with id: " + id);
    }

    @Override
    public ScoreDTO updateScore(ScoreDTO scoreDTO) {
        return null;
    }

    @Override
    public List<ScoreDTO> getScores() {
        List<Score> scores = scoreRepository.findByActiveTrue();
        List<ScoreDTO> result = scores.stream().map(this::toScoreDTO).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<ScoreDTO> getScoreByThesisId(Integer id) {
        Optional<Thesis> thesis = thesisRepository.findById(id);
        List<Score> scores = scoreRepository.findByThesisId(thesis.get());
        List<ScoreDTO> result = scores.stream().map(this::toScoreDTO).collect(Collectors.toList());
        return result;
    }

    private Score toScore(ScoreCreationDTO scoreCreationDTO) {
        Thesis thesis = null;
        Optional<CouncilPosition> councilPosition = null;
        if (Objects.nonNull(scoreCreationDTO.getThesisId())) {
            thesis = this.thesisService.getThesisByID(scoreCreationDTO.getThesisId());
        }

        if (Objects.nonNull(scoreCreationDTO.getCouncilPositionId())) {
            councilPosition = this.councilPositionRepository.findById(scoreCreationDTO.getCouncilPositionId());
        }
        Score score = Score.builder()
                .councilPositionId(councilPosition.get())
                .thesisId(thesis)
                .build();
        return score;
    }

    private ScoreDTO toScoreDTO(Score score) {
        ScoreDTO scoreDTO = ScoreDTO.builder()
                .id(score.getId())
                .councilPositionId(score.getCouncilPositionId().getId())
                .thesisId(score.getThesisId().getId())
                .build();
        return scoreDTO;
    }
}
