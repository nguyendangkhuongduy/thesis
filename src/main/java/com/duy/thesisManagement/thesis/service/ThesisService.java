package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dto.ThesisRequestDTO;
import com.duy.thesisManagement.thesis.dto.ThesisUpdatingDTO;
import com.duy.thesisManagement.thesis.model.Thesis;
import java.util.List;

public interface ThesisService {
    List<ThesisRequestDTO> getTheses();

    ThesisRequestDTO createdThesis(ThesisRequestDTO thesisRequestDTO);

    ThesisRequestDTO getThesisById(int id);

    Thesis getThesisByID(int id);

    void deleteThesis(Integer id);

    ThesisRequestDTO updateThesis(Integer id, ThesisUpdatingDTO thesisUpdatingDTO);



}
