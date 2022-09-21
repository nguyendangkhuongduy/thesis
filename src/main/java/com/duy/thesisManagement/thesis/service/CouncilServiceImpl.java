package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dto.CouncilCreationDTO;
import com.duy.thesisManagement.thesis.dto.CouncilDTO;
import com.duy.thesisManagement.thesis.dto.CouncilUpdatingDTO;
import com.duy.thesisManagement.thesis.dto.UserCreationDTO;
import com.duy.thesisManagement.thesis.exception.BadRequestException;
import com.duy.thesisManagement.thesis.exception.ResourceNotFoundException;
import com.duy.thesisManagement.thesis.model.Council;
import com.duy.thesisManagement.thesis.model.Faculty;
import com.duy.thesisManagement.thesis.model.Role;
import com.duy.thesisManagement.thesis.model.User;
import com.duy.thesisManagement.thesis.repository.CouncilRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CouncilServiceImpl implements CouncilService{

    private final CouncilRepository councilRepository;
    private final FacultyService facultyService;


    @Override
    public List<CouncilDTO> getAllCouncils() {
        List<Council> councils = councilRepository.findAll();
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
    public Council getCouncilById(Integer id) {
        Optional<Council> council = this.councilRepository.findById(id);
        if (council.isPresent()) {
            return council.get();
        }
        throw new ResourceNotFoundException("Cannot find any council with id: " + id);
    }

    @Override
    public CouncilDTO createCouncil(CouncilCreationDTO councilCreationDTO) {
        this.validateNewCouncil(councilCreationDTO);
        Council council = this.toCouncil(councilCreationDTO);
        Council savedCouncil = this.councilRepository.save(council);
        return toCouncilDTO(savedCouncil);
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

    private CouncilDTO toCouncilDTO(Council council) {
        CouncilDTO councilDTO = CouncilDTO.builder()
                .id(council.getId())
                .active(council.isActive())
                .createdDate(council.getCreatedDate())
                .facultyId(council.getFaculty().getId())
                .name(council.getName())
                .build();
        return councilDTO;
    }

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
                .active(councilCreationDTO.getActive())
                .name(councilCreationDTO.getName())
                .createdDate(councilCreationDTO.getCreatedDate())
                .faculty(faculty)
                .build();
        return council;
    }
}
