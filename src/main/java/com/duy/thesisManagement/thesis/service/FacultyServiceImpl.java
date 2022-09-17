package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.exception.ResourceNotFoundException;
import com.duy.thesisManagement.thesis.model.Faculty;
import com.duy.thesisManagement.thesis.repository.FacultyRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    @Override
    public Faculty createdFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(Integer id) {
        Optional<Faculty> foundFaculty = facultyRepository.findById(id);
        if (foundFaculty.isPresent()) {
            facultyRepository.delete(foundFaculty.get());
        }
        throw new ResourceNotFoundException("Cannot find any Faculty with id: " + id);
    }
}
