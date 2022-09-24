package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dto.CouncilPositionCreationDTO;
import com.duy.thesisManagement.thesis.dto.CouncilPositionRequestDTO;
import com.duy.thesisManagement.thesis.dto.CouncilPositionUpdatingDTO;
import com.duy.thesisManagement.thesis.dto.UserDTO;
import com.duy.thesisManagement.thesis.model.CouncilPosition;

import java.util.List;
import java.util.Set;

public interface CouncilPositionService {
    List<CouncilPositionRequestDTO> getAllCouncilPosition();
    List<CouncilPositionRequestDTO> getCouncilPositionByCouncilId(Integer id);

    CouncilPositionRequestDTO createdCouncilPosition(CouncilPositionCreationDTO councilPositionCreationDTO);

    CouncilPositionRequestDTO updatedCouncilPosition(CouncilPositionUpdatingDTO councilPositionUpdatingDTO);

    Set<CouncilPosition> constructSetOfCouncilPosition(Set<CouncilPositionCreationDTO> councilPositions);
}
