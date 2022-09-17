package com.duy.thesisManagement.thesis.repository;


import com.duy.thesisManagement.thesis.model.CouncilPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouncilPositionRepository extends JpaRepository<CouncilPosition, Integer> {
}
