package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dto.FacultyRequestDTO;
import com.duy.thesisManagement.thesis.dto.FacultyUpdatingDTO;
import com.duy.thesisManagement.thesis.model.Faculty;

import java.util.List;

public interface FacultyService {
    List<FacultyRequestDTO> getAllFaculties();

    FacultyRequestDTO createdFaculty(FacultyUpdatingDTO facultyUpdatingDTO);

    void deleteFaculty(Integer id);

    Faculty getFacultyById(Integer id);


}
