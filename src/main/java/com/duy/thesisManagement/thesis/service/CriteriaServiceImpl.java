package com.duy.thesisManagement.thesis.service;


import com.duy.thesisManagement.thesis.dto.CriteriaCreationDTO;
import com.duy.thesisManagement.thesis.dto.CriteriaDTO;
import com.duy.thesisManagement.thesis.exception.ResourceNotFoundException;
import com.duy.thesisManagement.thesis.model.*;
import com.duy.thesisManagement.thesis.repository.CriteriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CriteriaServiceImpl  implements CriteriaService{

    private final CriteriaRepository criteriaRepository;


    @Override
    public List<CriteriaDTO> getAllCriteria() {
        List<Criteria> criteria = criteriaRepository.findByActiveTrue();
        List<CriteriaDTO> result = criteria.stream().map(this::toCriteriaDTO).collect(Collectors.toList());
        return result;
    }

    @Override
    public CriteriaDTO createCriteria(CriteriaCreationDTO criteriaCreationDTO) {
        Criteria criteria = this.toCriteria(criteriaCreationDTO);
        criteria.setActive(true);
        Criteria saved = this.criteriaRepository.save(criteria);
        return toCriteriaDTO(saved);
    }

    @Override
    public void deleteCriteria(Integer id) {
        Optional<Criteria> criteriaOpt = this.criteriaRepository.findById(id);
        if (criteriaOpt.isPresent()) {
            Criteria deleted = criteriaOpt.get();
            deleted.setActive(false);
            this.criteriaRepository.save(deleted);
            return;
        }
        throw new ResourceNotFoundException("Cannot find any Criteria for deleting action with id: " + id);
    }

    private CriteriaDTO toCriteriaDTO(Criteria criteria) {
        CriteriaDTO criteriaDTO = CriteriaDTO.builder()
                .id(criteria.getId())
                .name(criteria.getName())
                .build();
        return criteriaDTO;
    }

    private Criteria toCriteria(CriteriaCreationDTO criteriaCreationDTO) {
        Criteria criteria= Criteria.builder()
                .name(criteriaCreationDTO.getName())
                .build();
        return criteria;
    }
}
