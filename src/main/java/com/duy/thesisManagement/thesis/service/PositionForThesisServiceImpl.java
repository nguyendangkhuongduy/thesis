package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.exception.ResourceNotFoundException;
import com.duy.thesisManagement.thesis.model.PositionForThesis;
import com.duy.thesisManagement.thesis.model.User;
import com.duy.thesisManagement.thesis.repository.PositionForThesisRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PositionForThesisServiceImpl implements PositionForThesisService{

    private final PositionForThesisRepository positionForThesisRepository;

    @Override
    public PositionForThesis findById(Integer id) {
        Optional<PositionForThesis> PFT = this.positionForThesisRepository.findById(id);
        if (PFT.isPresent()) {
            return PFT.get();
        }
        throw new ResourceNotFoundException("Cannot find any user with id: " + id);
    }
}
