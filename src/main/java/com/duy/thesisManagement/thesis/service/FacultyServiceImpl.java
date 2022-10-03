package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dto.FacultyRequestDTO;
import com.duy.thesisManagement.thesis.dto.FacultyUpdatingDTO;
import com.duy.thesisManagement.thesis.dto.UserCreationDTO;
import com.duy.thesisManagement.thesis.dto.UserDTO;
import com.duy.thesisManagement.thesis.exception.BadRequestException;
import com.duy.thesisManagement.thesis.exception.ResourceNotFoundException;
import com.duy.thesisManagement.thesis.model.AppRole;
import com.duy.thesisManagement.thesis.model.Faculty;
import com.duy.thesisManagement.thesis.model.Role;
import com.duy.thesisManagement.thesis.model.User;
import com.duy.thesisManagement.thesis.repository.FacultyRepository;

import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public List<FacultyRequestDTO> getAllFaculties() {
        List<Faculty> faculties = facultyRepository.findByActiveTrue();
        List<FacultyRequestDTO> result = faculties.stream().map(this::toFacultyRequestDTO).collect(Collectors.toList());
        return result;
    }

    @Override
    public FacultyRequestDTO createdFaculty(FacultyUpdatingDTO facultyUpdatingDTO) {
        this.validateNewFaculty(facultyUpdatingDTO);
        Faculty faculty = this.toFaculty(facultyUpdatingDTO);
        faculty.setActive(true);
        Faculty savedFaculty = this.facultyRepository.save(faculty);
        return toFacultyRequestDTO(savedFaculty);
    }

    @Override
    public void deleteFaculty(Integer id) {
        Optional<Faculty> facultyOpt = this.facultyRepository.findById(id);
        if (facultyOpt.isPresent()) {
            Faculty deleted = facultyOpt.get();
            deleted.setActive(false);
            this.facultyRepository.save(deleted);
            return;
        }
        throw new ResourceNotFoundException("Cannot find any faculty for deleting action with id: " + id);
    }

    @Override
    public Faculty getFacultyById(Integer facultyId) {
        Optional<Faculty> facultyOpt = this.facultyRepository.findById(facultyId);
        if (facultyOpt.isPresent()) {
            return facultyOpt.get();
        }
        throw new ResourceNotFoundException("Cannot find any faculty with id: "+ facultyId);
    }

    private FacultyRequestDTO toFacultyRequestDTO(Faculty faculty) {
        FacultyRequestDTO facultyRequestDTO = FacultyRequestDTO.builder()
                .id(faculty.getId())
                .name(faculty.getName())
                .build();
        return facultyRequestDTO;
    }

    private Faculty toFaculty(FacultyUpdatingDTO facultyUpdatingDTO) {
        Faculty faculty = Faculty.builder()
                .name(facultyUpdatingDTO.getName())
                .build();
        return faculty;
    }

    private void validateNewFaculty(FacultyUpdatingDTO facultyUpdatingDTO) {
        boolean nameExisted = this.facultyRepository.existsByName(facultyUpdatingDTO.getName());
        StringBuilder errorMessageBuilder = new StringBuilder();
        if (nameExisted) {
            errorMessageBuilder.append("Cannot create user: username alredy existed");
        }
        String errorMessage = errorMessageBuilder.toString();
        if (StringUtils.isNotBlank(errorMessage)) {
            throw new BadRequestException(errorMessage);
        }
    }
}
