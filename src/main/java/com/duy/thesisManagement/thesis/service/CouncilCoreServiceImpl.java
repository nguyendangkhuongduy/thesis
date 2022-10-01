package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dto.*;
import com.duy.thesisManagement.thesis.exception.BadRequestException;
import com.duy.thesisManagement.thesis.exception.ResourceNotFoundException;
import com.duy.thesisManagement.thesis.model.*;
import com.duy.thesisManagement.thesis.repository.CouncilRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CouncilCoreServiceImpl implements CouncilCoreService {

    private final CouncilRepository councilRepository;
    private final FacultyService facultyService;

    @Override
    public List<CouncilDTO> getAllCouncils() {
        List<Council> councils = councilRepository.findByActiveTrue();
        List<CouncilDTO> result = councils.stream().map(this::toCouncilDTO).collect(Collectors.toList());
        return result;
    }

    @Override
    public CouncilDTO getCouncilByName(String name) {
        if (this.councilRepository.findByName(name)) {
            return this.councilRepository.getCouncilByName(name);
        }
        throw new ResourceNotFoundException("Cannot find any council with name: " + name);
    }

    @Override
    public CouncilDTO getCouncilDTOById(Integer id) {
        Optional<Council> council = this.councilRepository.findById(id);
        if (council.isPresent()) {
            return this.toCouncilDTO(council.get());
        }
        throw new ResourceNotFoundException("Cannot find any council with id: " + id);
    }

    @Override
    public Council getCouncilByID(Integer id) {
        Optional<Council> council = this.councilRepository.findById(id);
        if (council.isPresent()) {
            return council.get();
        }
        throw new ResourceNotFoundException("Cannot find any council with id: " + id);
    }

    @Override
    public Council createCouncil(CouncilCreationDTO councilCreationDTO) {
        this.validateNewCouncil(councilCreationDTO);
        Council council = this.toCouncil(councilCreationDTO);
        council.setActive(true);
        Council saved = this.councilRepository.save(council);
        return saved;
    }

    @Override
    public CouncilDTO updateCouncil(Integer id, CouncilUpdatingDTO councilUpdatingDTO) {
        Optional<Council> foundCouncil = this.councilRepository.findById(id);
        Faculty faculty = null;
        if (Objects.nonNull(councilUpdatingDTO.getFacultyId())) {
            faculty = this.facultyService.getFacultyById(councilUpdatingDTO.getFacultyId());
        }
        if (foundCouncil.isPresent()) {
            Council updatingCouncil = foundCouncil.get();
            updatingCouncil.setName(councilUpdatingDTO.getName());
            updatingCouncil.setFaculty(faculty);

            Council savedCouncil = this.councilRepository.save(updatingCouncil);
            return this.toCouncilDTO(savedCouncil);
        }
        throw new ResourceNotFoundException("Cannot find any council for Update action with id: " + id);
    }

    @Override
    public void deleteCouncil(Integer id) {
        Optional<Council> councilOpt = this.councilRepository.findById(id);
        if (councilOpt.isPresent()) {
            Council deletedCouncil = councilOpt.get();
            deletedCouncil.setActive(false);
            this.councilRepository.save(deletedCouncil);
            return;
        }
        throw new ResourceNotFoundException("Cannot find any council for deleting action with id: " + id);
    }

    public CouncilDTO toCouncilDTO(Council council) {
        CouncilDTO councilDTO = CouncilDTO.builder()
                .id(council.getId())
                .active(council.isActive())
                .createdDate(council.getCreatedDate())
                .facultyId(council.getFaculty().getId())
                .name(council.getName())
                .build();
        return councilDTO;
    }

//    @Override
//    public List<CouncilPositionDTO> getByCouncilId(Integer id) {
//        List<CouncilPosition> cp =  councilRepository.getByCouncilId(id);
//        List<CouncilPositionDTO> result = cp.stream()
//                .map(this::toCouncilPositionDTO).collect(Collectors.toList());
//        return result;
//    }

    private void validateNewCouncil(CouncilCreationDTO councilCreationDTO) {
        boolean nameExisted = this.councilRepository.existsByName(councilCreationDTO.getName());
        StringBuilder errorMessageBuilder = new StringBuilder();
        if (nameExisted) {
            errorMessageBuilder.append("Cannot create council: name already existed");
        }
        String errorMessage = errorMessageBuilder.toString();
        if (StringUtils.isNotBlank(errorMessage)) {
            throw new BadRequestException(errorMessage);
        }
    }

    private Council toCouncil(CouncilCreationDTO councilCreationDTO) {
        Faculty faculty = null;
        if (Objects.nonNull(councilCreationDTO.getFacultyId())) {
            faculty = this.facultyService.getFacultyById(councilCreationDTO.getFacultyId());
        }

        Council council= Council.builder()
//                .active(councilCreationDTO.getActive())
                .name(councilCreationDTO.getName())
//                .createdDate(councilCreationDTO.getCreatedDate())
                .faculty(faculty)
                .build();
        return council;
    }

    private CouncilPositionDTO toCouncilPositionDTO(CouncilPosition councilPosition) {
        CouncilPositionDTO positionRequestDTO = CouncilPositionDTO.builder()
                .id(councilPosition.getId())
                .userId(councilPosition.getUserId().getId())
                .positionId(councilPosition.getPositionId().getId())
                .councilId(councilPosition.getCouncilId().getId())
                .build();
        return positionRequestDTO;
    }

}
