package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dto.CouncilPositionUpdatingDTO;
import com.duy.thesisManagement.thesis.exception.ResourceNotFoundException;
import com.duy.thesisManagement.thesis.model.Council;
import com.duy.thesisManagement.thesis.model.CouncilPosition;
import com.duy.thesisManagement.thesis.repository.CouncilPositionRepository;
import com.duy.thesisManagement.thesis.repository.CouncilRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CouncilPositionServiceImpl implements CouncilPositionService{

    private final CouncilPositionRepository councilPositionRepository;
    private final CouncilRepository councilRepository;


    @Override
    public CouncilPosition getCouncilPositionByCouncilId(Integer id) {
        if(councilRepository.findCouncilById(id)){
            return this.councilPositionRepository.findCouncilPositionByCouncilId(id);
        }
        throw new ResourceNotFoundException("Cannot find any council with id: " + id);

    }

    @Override
    public CouncilPosition createdCouncilPosition(CouncilPosition councilPosition) {
         CouncilPosition save = this.councilPositionRepository.save(councilPosition);
         return save;
    }

//    @Override
//    public CouncilPosition updateCouncilPosition(CouncilPositionUpdatingDTO councilPositionUpdatingDTO) {
//        return CouncilPosition;
//    }

    @Override
    public void deleteCouncilPosition(Integer id) {

    }
}
