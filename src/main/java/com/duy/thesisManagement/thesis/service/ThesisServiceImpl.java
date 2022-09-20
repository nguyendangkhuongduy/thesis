package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dto.*;
import com.duy.thesisManagement.thesis.exception.BadRequestException;
import com.duy.thesisManagement.thesis.exception.ResourceNotFoundException;
import com.duy.thesisManagement.thesis.model.*;
import com.duy.thesisManagement.thesis.repository.ThesisRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThesisServiceImpl implements ThesisService {

    private final FacultyService facultyService;

    private final CouncilService councilService;

    @Autowired
    private ThesisRepository thesisRepository;

    @Override
    public List<ThesisRequestDTO> getTheses()
    {
        List<Thesis> theses = thesisRepository.findAll();
        List<ThesisRequestDTO> result = theses.stream().map(this::toThesisDTO).collect(Collectors.toList());
        return result;
    }

    @Override
    public ThesisRequestDTO createdThesis(ThesisRequestDTO thesisRequestDTO) {
        this.validateNewThesis(thesisRequestDTO);
        Thesis thesis = this.toThesis(thesisRequestDTO);
        Thesis savedThesis = this.thesisRepository.save(thesis);
        return toThesisDTO(savedThesis);
    }

    @Override
    public ThesisRequestDTO getThesisById(int id) {
        Optional<Thesis> thesis = thesisRepository.findById(id);
        if (thesis.isPresent()) {
            return this.toThesisDTO(thesis.get());
        }
        throw new ResourceNotFoundException("Cannot find any thesis with id: " + id);
    }



    @Override
    public void deleteThesis(Integer id) {
        Optional<Thesis> deletedThesisOpt = thesisRepository.findById(id);
        if (deletedThesisOpt.isPresent()) {
            Thesis thesis = deletedThesisOpt.get();
            thesis.setActive(false);
            thesisRepository.save(thesis);
        }
        throw new ResourceNotFoundException("Cannot delete thesis because there is no thesis with given id: " + id);
    }

    @Override
    public ThesisRequestDTO updateThesis(Integer id, ThesisUpdatingDTO thesisUpdatingDTO) {
        Optional<Thesis> foundThesis = this.thesisRepository.findById(id);
        Faculty faculty = null;
        Council council = null;
        if (Objects.nonNull(thesisUpdatingDTO.getFacultyId())) {
            faculty = this.facultyService.getFacultyById(thesisUpdatingDTO.getFacultyId());
        }
        if (Objects.nonNull(thesisUpdatingDTO.getCouncilId())) {
            council = this.councilService.getCouncilById(thesisUpdatingDTO.getCouncilId());
        }
        if (foundThesis.isPresent()) {
            Thesis updatingThesis = foundThesis.get();
            updatingThesis.setName(thesisUpdatingDTO.getName());
            updatingThesis.setCouncilId(council);
            updatingThesis.setFaculty(faculty);
            updatingThesis.setTotalScore(thesisUpdatingDTO.getTotalScore());

            Thesis savedThesis = this.thesisRepository.save(updatingThesis);
            return this.toThesisDTO(savedThesis);
        }
        throw new ResourceNotFoundException("Cannot find any thesis for Update action with id: " + id);
    }




    private ThesisRequestDTO toThesisDTO(Thesis thesis) {
        ThesisRequestDTO thesisDTO = ThesisRequestDTO.builder()
                .id(thesis.getId())
                .name(thesis.getName())
                .createdDate(thesis.getCreatedDate())
                .totalScore(thesis.getTotalScore())
                .facultyId(thesis.getFaculty().getId())
                .councilId(thesis.getCouncilId().getId()).build();
        return thesisDTO;
    }

    private Thesis toThesis(ThesisRequestDTO thesisRequestDTO) {
        Faculty faculty = null;
        Council council = null;
        if (Objects.nonNull(thesisRequestDTO.getFacultyId())) {
            faculty = this.facultyService.getFacultyById(thesisRequestDTO.getFacultyId());
        }
        if (Objects.nonNull(thesisRequestDTO.getCouncilId())) {
            council = this.councilService.getCouncilById(thesisRequestDTO.getCouncilId());
        }
        Thesis thesis = Thesis.builder()
                .name(thesisRequestDTO.getName())
                .createdDate(thesisRequestDTO.getCreatedDate())
                .faculty(faculty)
                .councilId(council)
                .totalScore(thesisRequestDTO.getTotalScore())
                .active(thesisRequestDTO.getActive())
                .build();
        return thesis;
    }
    private void validateNewThesis(ThesisRequestDTO thesisRequestDTO) {
        boolean ThesisNameExisted = this.thesisRepository.existsByName(thesisRequestDTO.getName());
        StringBuilder errorMessageBuilder = new StringBuilder();
        if (ThesisNameExisted) {
            errorMessageBuilder.append("Cannot create thesis: Thesis name already existed");
        }
        String errorMessage = errorMessageBuilder.toString();
        if (StringUtils.isNotBlank(errorMessage)) {
            throw new BadRequestException(errorMessage);
        }
    }

}
