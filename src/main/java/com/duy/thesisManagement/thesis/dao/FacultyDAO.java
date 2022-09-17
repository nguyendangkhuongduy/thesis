package com.duy.thesisManagement.thesis.dao;


import com.duy.thesisManagement.thesis.model.Faculty;

import java.util.List;

public interface FacultyDAO {
    public List<Faculty> getFaculty();

    public boolean createdFaculty(Faculty faculty);

    public boolean deleteFaculty(int id);


}
