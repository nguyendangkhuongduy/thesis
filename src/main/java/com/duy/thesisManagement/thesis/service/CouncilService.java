package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dto.CouncilCreationDTO;
import com.duy.thesisManagement.thesis.dto.CouncilDTO;
import com.duy.thesisManagement.thesis.dto.CouncilUpdatingDTO;
import com.duy.thesisManagement.thesis.model.Council;
import com.duy.thesisManagement.thesis.model.CouncilPosition;
import com.duy.thesisManagement.thesis.repository.CouncilRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class CouncilService {

    private final CouncilCoreService councilCoreService;
    private final CouncilPositionService councilPositionService;

    private final CouncilRepository councilRepository;
    public CouncilDTO createCouncil(CouncilCreationDTO councilCreationDTO) {
        Set<CouncilPosition> councilPositions = this.councilPositionService
                .constructSetOfCouncilPosition(councilCreationDTO.getCouncilPositions());
        Council council = this.councilCoreService.createCouncil(councilCreationDTO);
        council.setCouncilPositionSet(councilPositions);
        for (CouncilPosition item : councilPositions) {
            item.setCouncilId(council);
        }
        return this.councilCoreService.toCouncilDTO(councilRepository.save(council));
    }

    public List<CouncilDTO> getAllCouncils() {
        return this.councilCoreService.getAllCouncils();
    }

    public CouncilDTO getCouncilById(Integer id) {
        return this.councilCoreService.getCouncilDTOById(id);
    }

    public CouncilDTO updateCouncil(Integer id, CouncilUpdatingDTO councilUpdatingDTO) {
        return this.councilCoreService.updateCouncil(id, councilUpdatingDTO);
    }

    public void deleteCouncil(Integer id) {
        this.councilCoreService.deleteCouncil(id);
    }
}
