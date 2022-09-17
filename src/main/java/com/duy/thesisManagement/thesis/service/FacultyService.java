package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.model.Faculty;

import java.util.List;

public interface FacultyService {
    public List<Faculty> getFaculty();

    public boolean createdFaculty(Faculty faculty);

    public boolean deleteFaculty(int id);
}
