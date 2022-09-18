package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.model.Faculty;

import java.util.List;

public interface FacultyService {
    public List<Faculty> getAllFaculties();

    public Faculty createdFaculty(Faculty faculty);

    public void deleteFaculty(Integer id);

    Faculty getFacultyById(Integer facultyId);
}
