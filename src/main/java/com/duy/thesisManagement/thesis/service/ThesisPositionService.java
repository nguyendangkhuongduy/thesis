package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dto.*;

import java.util.List;

public interface ThesisPositionService {

    List<ThesisPositionDTO> getThesisPositionByThesisId(Integer id);

    List<ThesisPositionDTO> getThesisPositionByUserId(Integer id);

    List<ThesisPositionDTO> getAllThesisPosition();

    ThesisPositionDTO createThesisPosition(ThesisPositionCreationDTO thesisPositionCreationDTO);

    void deleteThesisPosition(Integer id);
}




