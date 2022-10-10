package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dto.*;

import java.util.List;

public interface ScoreDetailService {
    ScoreDetailDTO createdScoreDetail(ScoreDetailCreationDTO scoreDetailCreationDTO);

    void deleteScoreDetail(Integer id);

    ScoreDetailDTO updateScoreDetail(Integer id,ScoreDetailUpdatingDTO scoreDetailUpdatingDTO);


    List<ScoreDetailDTO> getScoreDetailByScoreId(Integer id);

}
