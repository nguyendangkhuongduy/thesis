package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dto.*;
import com.duy.thesisManagement.thesis.model.Council;

import java.util.List;

public interface CouncilService {

    List<CouncilDTO> getAllCouncils();

    CouncilDTO getCouncilByName(String name);

    CouncilDTO getCouncilById(Integer id);

    Council getCouncilByID(Integer id);

    CouncilDTO createCouncil(CouncilCreationDTO councilCreationDTO);

    CouncilDTO updateCouncil(Integer id, CouncilUpdatingDTO councilUpdatingDTO);

    void deleteCouncil(Integer id);

}
