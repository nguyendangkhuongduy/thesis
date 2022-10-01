package com.duy.thesisManagement.thesis.repository;


import com.duy.thesisManagement.thesis.dto.CouncilDTO;
import com.duy.thesisManagement.thesis.dto.CouncilPositionDTO;
import com.duy.thesisManagement.thesis.model.Council;
import com.duy.thesisManagement.thesis.model.CouncilPosition;
import com.duy.thesisManagement.thesis.model.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CouncilRepository extends JpaRepository<Council, Integer> {

    boolean existsByName(String name);
    boolean findCouncilById(Integer id);

    boolean findByName(String name);

    CouncilDTO getCouncilByName(String name);

    @Query("SELECT c FROM Council c WHERE c.active = true")
    List<Council> findByActiveTrue();
}
