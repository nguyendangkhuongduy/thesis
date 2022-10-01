package com.duy.thesisManagement.thesis.repository;


import com.duy.thesisManagement.thesis.model.Criteria;
import com.duy.thesisManagement.thesis.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {

    @Query("SELECT c FROM Position c WHERE c.active = true")
    List<Position> findByActiveTrue();

}
