package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dto.ScoreDetailCreationDTO;
import com.duy.thesisManagement.thesis.dto.ScoreDetailDTO;
import com.duy.thesisManagement.thesis.dto.ScoreDetailUpdatingDTO;
import com.duy.thesisManagement.thesis.dto.ThesisPositionDTO;
import com.duy.thesisManagement.thesis.exception.ResourceNotFoundException;
import com.duy.thesisManagement.thesis.model.*;
import com.duy.thesisManagement.thesis.repository.CriteriaRepository;
import com.duy.thesisManagement.thesis.repository.ScoreDetailRepository;
import com.duy.thesisManagement.thesis.repository.ScoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ScoreDetailServiceImpl implements ScoreDetailService {
    private final ScoreDetailRepository scoreDetailRepository;
    private final ScoreRepository scoreRepository;
    private final CriteriaRepository criteriaRepository;


    @Override
    public ScoreDetailDTO createdScoreDetail(ScoreDetailCreationDTO scoreDetailCreationDTO) {
        ScoreDetail scoreDetail = this.toScoreDetail(scoreDetailCreationDTO);
        ScoreDetail saved = this.scoreDetailRepository.save(scoreDetail);
        return toScoreDetailDTO(saved);
    }

    @Override
    public void deleteScoreDetail(Integer id) {
        Optional<ScoreDetail> scoreOpt = this.scoreDetailRepository.findById(id);
        if (scoreOpt.isPresent()) {
            ScoreDetail deleted = scoreOpt.get();
            this.scoreDetailRepository.delete(deleted);
            return;
        }
        throw new ResourceNotFoundException("Cannot find any scoreDetails for deleting action with id: " + id);
    }

    @Override
    public ScoreDetailDTO updateScoreDetail(Integer id, ScoreDetailUpdatingDTO scoreDetailUpdatingDTO) {
        Optional<ScoreDetail> found = this.scoreDetailRepository.findById(id);
        if (found.isPresent()) {
            ScoreDetail updating = found.get();
            updating.setMark(scoreDetailUpdatingDTO.getMark());

            ScoreDetail saved = this.scoreDetailRepository.save(updating);
            return this.toScoreDetailDTO(saved);
        }
        throw new ResourceNotFoundException("Cannot find any scoreDetails for Update action with id: " + id);
    }

    @Override
    public List<ScoreDetailDTO> getScoreDetailByScoreId(Integer id) {
        Optional<Score> score = scoreRepository.findById(id);
        List<ScoreDetail> scoreDetails = scoreDetailRepository.findByScore(score.get());
        List<ScoreDetailDTO> result = scoreDetails.stream()
                .map(this::toScoreDetailDTO).collect(Collectors.toList());
        return result;
    }

    private ScoreDetail toScoreDetail(ScoreDetailCreationDTO scoreDetailCreationDTO) {
        Optional<Score> score = null;
        if (Objects.nonNull(scoreDetailCreationDTO.getScore_id())) {
            score = this.scoreRepository.findById(scoreDetailCreationDTO.getScore_id());
        }

        Optional<Criteria> criteria= null;
        if (Objects.nonNull(scoreDetailCreationDTO.getCriteria_id())) {
            criteria = this.criteriaRepository.findById(scoreDetailCreationDTO.getCriteria_id());
        }
        ScoreDetail scoreDetail = ScoreDetail.builder()
                .score(score.get())
                .criteria(criteria.get())
                .mark(scoreDetailCreationDTO.getMark())
                .build();
        return scoreDetail;
    }

    private ScoreDetailDTO toScoreDetailDTO(ScoreDetail scoreDetail) {
        ScoreDetailDTO scoreDetailDTO = ScoreDetailDTO.builder()
                .id(scoreDetail.getId())
                .score_id(scoreDetail.getScore().getId())
                .criteria_id(scoreDetail.getCriteria().getId())
                .mark(scoreDetail.getMark())
                .build();
        return scoreDetailDTO;
    }
}
