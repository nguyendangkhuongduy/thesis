package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dto.CouncilPositionCreationDTO;
import com.duy.thesisManagement.thesis.dto.CouncilPositionDTO;
import com.duy.thesisManagement.thesis.dto.CouncilPositionUpdatingDTO;
import com.duy.thesisManagement.thesis.model.CouncilPosition;

import java.util.List;
import java.util.Set;

public interface CouncilPositionService {
    List<CouncilPositionDTO> getAllCouncilPosition();
    List<CouncilPositionDTO> getCouncilPositionByCouncilId(Integer id);

    CouncilPositionDTO createdCouncilPosition(CouncilPositionCreationDTO councilPositionCreationDTO);

    CouncilPositionDTO updatedCouncilPosition(Integer id, CouncilPositionUpdatingDTO councilPositionUpdatingDTO);

    Set<CouncilPosition> constructSetOfCouncilPosition(Set<CouncilPositionCreationDTO> councilPositions);
}
