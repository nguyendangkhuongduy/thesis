package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dto.*;
import com.duy.thesisManagement.thesis.model.Council;
import com.duy.thesisManagement.thesis.model.CouncilPosition;

import java.util.List;
import java.util.Set;

public interface CouncilPositionService {
    List<CouncilPositionDTO> getAllCouncilPosition();

    List<CouncilPositionDTO> getCouncilPositionByUserId(Integer id);
    List<CouncilPositionDTO> getCouncilPositionByCouncilId(Integer id);


    CouncilPositionDTO createdCouncilPosition(CouncilPositionCreationDTO councilPositionCreationDTO);

    CouncilPositionDTO updatedCouncilPosition(Integer id, CouncilPositionUpdatingDTO councilPositionUpdatingDTO);

    Set<CouncilPosition> constructSetOfCouncilPosition(Set<CouncilPositionCreationDTO> councilPositions);

    Long countCouncilPositionByCouncilId(Integer id);

    Long countPresidentByCouncilId(Integer id);

    Long countSecretaryByCouncilId(Integer id);

    Long countCriticalByCouncilId(Integer id);

    Long countMemberByCouncilId(Integer id);

    void deleteCouncilPosition(Integer id);



}
