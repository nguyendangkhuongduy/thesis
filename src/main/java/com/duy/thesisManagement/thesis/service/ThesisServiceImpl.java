package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dto.*;
import com.duy.thesisManagement.thesis.exception.BadRequestException;
import com.duy.thesisManagement.thesis.exception.ResourceNotFoundException;
import com.duy.thesisManagement.thesis.model.*;
import com.duy.thesisManagement.thesis.repository.CouncilRepository;
import com.duy.thesisManagement.thesis.repository.ThesisRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThesisServiceImpl implements ThesisService {

    private final FacultyService facultyService;

    private final CouncilRepository councilRepository;

    private final CouncilCoreService councilCoreService;

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
    public ThesisRequestDTO createdThesis(ThesisCreationDTO thesisCreationDTO) {
        this.validateNewThesis(thesisCreationDTO);
        Thesis thesis = this.toThesis(thesisCreationDTO);
        thesis.setActive(true);
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
    public Thesis getThesisByID(int id) {
        Optional<Thesis> thesis = thesisRepository.findById(id);
        if (thesis.isPresent()) {
            return thesis.get();
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
            return;
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
            council = this.councilCoreService.getCouncilByID(thesisUpdatingDTO.getCouncilId());
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

    @Override
    public Long countThesisByCouncilId(Integer id) {
        Optional<Council> council = councilRepository.findById(id);
        Long result = this.thesisRepository.countByCouncilId(council.get());
        return result;
    }

    @Override
    public ThesisRequestDTO addCouncil(Integer id, ThesisAddCouncilDTO thesisAddCouncilDTO) {
        Optional<Thesis> foundThesis = this.thesisRepository.findById(id);
        Council council = null;
        if (Objects.nonNull(thesisAddCouncilDTO.getCouncilId())) {
            council = this.councilCoreService.getCouncilByID(thesisAddCouncilDTO.getCouncilId());
        }
        if (foundThesis.isPresent()) {
            Thesis updatingThesis = foundThesis.get();
            updatingThesis.setCouncilId(council);
            Thesis savedThesis = this.thesisRepository.save(updatingThesis);
            return this.toThesisDTO(savedThesis);
        }
        throw new ResourceNotFoundException("Cannot find any thesis for Add action with id: " + id);
    }

    @Override
    public ThesisRequestDTO addTotalScore(Integer id, ThesisAddTotalScoreDTO thesisAddTotalScoreDTO) {
        Optional<Thesis> foundThesis = this.thesisRepository.findById(id);
        if (foundThesis.isPresent()) {
            Thesis updatingThesis = foundThesis.get();
            updatingThesis.setTotalScore(thesisAddTotalScoreDTO.getTotalScore());
            Thesis savedThesis = this.thesisRepository.save(updatingThesis);
            return this.toThesisDTO(savedThesis);
        }
        throw new ResourceNotFoundException("Cannot find any thesis for Add score action with id: " + id);
    }


    private ThesisRequestDTO toThesisDTO(Thesis thesis) {
        ThesisRequestDTO thesisDTO = ThesisRequestDTO.builder()
                .id(thesis.getId())
                .name(thesis.getName())
                .active(thesis.isActive())
                .createdDate(thesis.getCreatedDate())
                .totalScore(thesis.getTotalScore())
                .facultyId(thesis.getFaculty().getId())
                .councilId(thesis.getCouncilId().getId())
                .build();
        return thesisDTO;
    }

    private Thesis toThesis(ThesisCreationDTO thesisCreationDTO) {
        Faculty faculty = null;
        if (Objects.nonNull(thesisCreationDTO.getFacultyId())) {
            faculty = this.facultyService.getFacultyById(thesisCreationDTO.getFacultyId());
        }
        Thesis thesis = Thesis.builder()
                .name(thesisCreationDTO.getName())
                .faculty(faculty)
                .build();
        return thesis;
    }
    private void validateNewThesis(ThesisCreationDTO thesisCreationDTO) {
        boolean ThesisNameExisted = this.thesisRepository.existsByName(thesisCreationDTO.getName());
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
