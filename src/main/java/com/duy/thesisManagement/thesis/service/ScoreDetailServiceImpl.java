package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dto.*;
import com.duy.thesisManagement.thesis.exception.ResourceNotFoundException;
import com.duy.thesisManagement.thesis.model.*;
import com.duy.thesisManagement.thesis.repository.CriteriaRepository;
import com.duy.thesisManagement.thesis.repository.ScoreDetailRepository;
import com.duy.thesisManagement.thesis.repository.ScoreRepository;
import com.duy.thesisManagement.thesis.repository.ThesisRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ScoreDetailServiceImpl implements ScoreDetailService {
    private final ScoreDetailRepository scoreDetailRepository;
    private final ScoreRepository scoreRepository;

    private final ThesisRepository thesisRepository;
    private final CriteriaRepository criteriaRepository;

    private ScoreService scoreService;


    @Override
    public List<ScoreDetailDTO> getAll() {
        List<ScoreDetail> scoreDetails = scoreDetailRepository.findAll();
        List<ScoreDetailDTO> result = scoreDetails.stream().map(this::toScoreDetailDTO).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<ScoreDetailDTO> getScoreDetailByScore(Integer id) {
        Optional<Thesis> thesis = thesisRepository.findById(id);
        List<Score> score = scoreRepository.findByThesisId(thesis.get());
        List<ScoreDetail> getAll = scoreDetailRepository.findAll();
        List<ScoreDetail> detailList = new ArrayList<>();
        for (int i = 0; i < getAll.size(); i++) {
            for(int j = 0; j < score.size(); j++)
            {
                Score t = score.get(j);
                if (t.getId() == getAll.get(i).getScore().getId()) {
                    detailList.add(getAll.get(i));
                }
            }
        }
        List<ScoreDetailDTO> result = detailList.stream().map(this::toScoreDetailDTO).collect(Collectors.toList());
        return result;
    }

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

    private ScoreDetailHasUserNameDTO toScoreDetailHasNameDTO(ScoreDetail scoreDetail, String name) {
        ScoreDetailHasUserNameDTO scoreDetailDTO = ScoreDetailHasUserNameDTO.builder()
                .id(scoreDetail.getId())
                .score_id(scoreDetail.getScore().getId())
                .criteria_id(scoreDetail.getCriteria().getId())
                .mark(scoreDetail.getMark())
                .name(name)
                .build();
        return scoreDetailDTO;
    }
}
