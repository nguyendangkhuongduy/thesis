package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.exception.ResourceNotFoundException;
import com.duy.thesisManagement.thesis.model.Thesis;
import com.duy.thesisManagement.thesis.repository.ThesisRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThesisServiceImpl implements ThesisService {

    @Autowired
    private ThesisRepository thesisRepository;

    @Override
    public List<Thesis> getTheses() {
        return thesisRepository.findAll();
    }

    @Override
    public Thesis createdThesis(Thesis thesis) {
        return thesisRepository.save(thesis);
    }

    @Override
    public Thesis getThesisById(int id) {
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
        }
        throw new ResourceNotFoundException("Cannot delete thesis because there is no thesis with given id: " + id);
    }
}
