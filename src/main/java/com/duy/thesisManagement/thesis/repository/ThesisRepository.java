package com.duy.thesisManagement.thesis.repository;

import com.duy.thesisManagement.thesis.dto.ThesisPositionDTO;
import com.duy.thesisManagement.thesis.dto.ThesisRequestDTO;
import com.duy.thesisManagement.thesis.model.*;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ThesisRepository extends JpaRepository<Thesis, Integer> {

    Optional<ThesisRequestDTO> findByName(String name);
    Boolean existsByName(String name);

    @Query("SELECT COUNT(c) FROM Thesis c WHERE c.active = true AND c.councilId = :council" )
    public long countByCouncilId(Council council);

    @Query("SELECT c FROM Thesis c WHERE c.active = true")
    List<Thesis> findByActiveTrue();

//    @Query("SELECT c FROM Thesis c WHERE c.councilId = null")
//    List<Thesis> getThesisNullCouncil();

    @Query("SELECT c FROM Thesis c WHERE c.councilId=:council")
    public List<Thesis> getByCouncilId(Council council);


}
