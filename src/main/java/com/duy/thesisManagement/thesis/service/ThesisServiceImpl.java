package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dto.*;
import com.duy.thesisManagement.thesis.exception.BadRequestException;
import com.duy.thesisManagement.thesis.exception.ResourceNotFoundException;
import com.duy.thesisManagement.thesis.model.*;
import com.duy.thesisManagement.thesis.repository.CouncilRepository;
import com.duy.thesisManagement.thesis.repository.ThesisPositionRepository;
import com.duy.thesisManagement.thesis.repository.ThesisRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.duy.thesisManagement.thesis.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThesisServiceImpl implements ThesisService {

    private final FacultyService facultyService;

    private final UserRepository userRepository;

    private final ThesisPositionRepository thesisPositionRepository;

    private final ThesisService thesisService;

    private final CouncilRepository councilRepository;

    private final CouncilCoreService councilCoreService;

    @Autowired
    private ThesisRepository thesisRepository;

    @Override
    public List<ThesisRequestDTO> getTheses()
    {
        List<Thesis> theses = thesisRepository.findByActiveTrue();
        List<ThesisRequestDTO> result = theses.stream().map(this::toThesisDTO).collect(Collectors.toList());
        return result;
    }

//    @Override
//    public List<Thesis> getThesis() {
//
//    }

    @Override
    public List<ThesisRequestDTO> getThesesByCouncilId(Integer id) {
        Optional<Council> council = councilRepository.findById(id);
        List<Thesis> cp = thesisRepository.getByCouncilId(council.get());
        List<ThesisRequestDTO> result = cp.stream()
                .map(this::toThesisDTO).collect(Collectors.toList());
        return result;
    }

//    @Override
//    public List<ThesisRequestDTO> getThesesNullCouncil() {
//        List<Thesis> theses = thesisRepository.getThesisNullCouncil();
//        List<ThesisRequestDTO> result = theses.stream().map(this::toThesisDTO).collect(Collectors.toList());
//        return result;
//    }

//    @Override
//    public List<Thesis> getTheses() {
//        List<Thesis> theses = thesisRepository.findAll();
//        return theses;
//    }

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
    public void removeCouncil(Integer id) {
        Optional<Thesis> thesis = thesisRepository.findById(id);
        if (thesis.isPresent()) {
            Thesis a = thesis.get();
            a.setCouncilId(null);
            thesisRepository.save(a);
            return;
        }
        throw new ResourceNotFoundException("Cannot remove council");
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

//    @Override
//    public List<ThesisRequestDTO> getThesisByUserId(Integer id) {
//        Optional<User> user = userRepository.findById(id);
//        List<ThesisPosition> thesisPositions = thesisPositionRepository.findByUserId(user.get());
//        List<Thesis> getAll = thesisRepository.findByActiveTrue();
//        List<Thesis> thesis = new ArrayList<Thesis>();
//        for (int i = 0; i < thesisPositions.size(); i++) {
//            ThesisPosition t = thesisPositions.get(i);
//            if (t.getThesisId().getId() == getAll.get(i).getId()) {
//                thesis.add(getAll.get(i));
//            }
//        }
//        List<ThesisRequestDTO> result = thesis.stream().map(this::toThesisDTO).collect(Collectors.toList());
//        return result;
//}


    private ThesisRequestDTO toThesisDTO(Thesis thesis) {
        ThesisRequestDTO thesisDTO = ThesisRequestDTO.builder()
                .id(thesis.getId())
                .name(thesis.getName())
                .active(thesis.isActive())
                .createdDate(thesis.getCreatedDate())
                .totalScore(thesis.getTotalScore())
                .faculty(thesis.getFaculty().getName())
                .build();
        if (Objects.nonNull(thesis.getCouncilId())) {
            thesisDTO.setCouncil(thesis.getCouncilId().getName());
        }
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
