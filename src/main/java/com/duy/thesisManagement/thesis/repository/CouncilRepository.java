package com.duy.thesisManagement.thesis.repository;


import com.duy.thesisManagement.thesis.dto.CouncilDTO;
import com.duy.thesisManagement.thesis.model.Council;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouncilRepository extends JpaRepository<Council, Integer> {

    boolean existsByName(String name);

    boolean findByName(String name);

    CouncilDTO getCouncilByName(String name);
}
