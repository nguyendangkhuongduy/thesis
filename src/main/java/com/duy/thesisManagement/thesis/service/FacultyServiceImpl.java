package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dao.FacultyDAO;
import com.duy.thesisManagement.thesis.model.Faculty;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyDAO {

    @Autowired
    private FacultyDAO facultyDAO;

    @Override
    public List<Faculty> getFaculty() {
        return facultyDAO.getFaculty();
    }

    @Override
    public boolean createdFaculty(Faculty faculty) {
        return facultyDAO.createdFaculty(faculty);
    }

    @Override
    public boolean deleteFaculty(int id) {
        return facultyDAO.deleteFaculty(id);
    }
}
