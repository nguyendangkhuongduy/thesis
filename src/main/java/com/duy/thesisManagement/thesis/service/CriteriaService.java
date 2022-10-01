package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dto.*;


import java.util.List;

public interface CriteriaService {
    List<CriteriaDTO> getAllCriteria();

    CriteriaDTO createCriteria(CriteriaCreationDTO criteriaCreationDTO);

    void deleteCriteria(Integer id);
}
