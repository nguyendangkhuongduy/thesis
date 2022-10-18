package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dto.*;
import com.duy.thesisManagement.thesis.model.Council;
import com.duy.thesisManagement.thesis.model.CouncilPosition;

import java.util.List;
import java.util.Set;

public interface CouncilCoreService {

    List<CouncilDTO> getAllCouncils();

    CouncilDTO getCouncilByName(String name);

    CouncilDTO getCouncilDTOById(Integer id);

    Council getCouncilByID(Integer id);


    Council createCouncil(CouncilCreationDTO councilCreationDTO);

    CouncilDTO updateCouncil(Integer id, CouncilUpdatingDTO councilUpdatingDTO);

    void deleteCouncil(Integer id);

    CouncilDTO toCouncilDTO(Council council);

//    List<CouncilPositionDTO> getByCouncilId(Integer id);

    List<CouncilDTO> getCouncilByUserId(Integer id);


}
