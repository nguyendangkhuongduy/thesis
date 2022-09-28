package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dto.*;
import com.duy.thesisManagement.thesis.model.Council;
import com.duy.thesisManagement.thesis.model.Thesis;
import java.util.List;

public interface ThesisService {
    List<ThesisRequestDTO> getTheses();

    ThesisRequestDTO createdThesis(ThesisCreationDTO thesisCreationDTO);

    ThesisRequestDTO getThesisById(int id);

    Thesis getThesisByID(int id);

    void deleteThesis(Integer id);

    ThesisRequestDTO updateThesis(Integer id, ThesisUpdatingDTO thesisUpdatingDTO);

    Long countThesisByCouncilId(Integer id);

    ThesisRequestDTO addCouncil(Integer id, ThesisAddCouncilDTO thesisAddCouncilDTO);

    ThesisRequestDTO addTotalScore(Integer id, ThesisAddTotalScoreDTO thesisAddTotalScoreDTO);



}
