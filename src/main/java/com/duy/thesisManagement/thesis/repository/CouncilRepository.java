package com.duy.thesisManagement.thesis.repository;


import com.duy.thesisManagement.thesis.model.Council;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouncilRepository extends JpaRepository<Council, Integer> {

//    boolean existsByCouncilname(String name);
}
