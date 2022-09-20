package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dto.ThesisRequestDTO;
import com.duy.thesisManagement.thesis.dto.ThesisUpdatingDTO;
import com.duy.thesisManagement.thesis.model.Thesis;
import java.util.List;

public interface ThesisService {
    public List<ThesisRequestDTO> getTheses();

    public ThesisRequestDTO createdThesis(ThesisRequestDTO thesisRequestDTO);

    public ThesisRequestDTO getThesisById(int id);

    public void deleteThesis(Integer id);

    ThesisRequestDTO updateThesis(Integer id, ThesisUpdatingDTO thesisUpdatingDTO);



}
