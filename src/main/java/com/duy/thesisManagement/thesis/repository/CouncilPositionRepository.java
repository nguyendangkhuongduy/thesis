package com.duy.thesisManagement.thesis.repository;


import com.duy.thesisManagement.thesis.dto.CouncilPositionRequestDTO;
import com.duy.thesisManagement.thesis.model.CouncilPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface CouncilPositionRepository extends JpaRepository<CouncilPosition, Integer> {

    CouncilPosition findCouncilPositionByCouncilId(Integer id);


}
