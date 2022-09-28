package com.duy.thesisManagement.thesis.repository;

import com.duy.thesisManagement.thesis.dto.ThesisPositionDTO;
import com.duy.thesisManagement.thesis.dto.ThesisRequestDTO;
import com.duy.thesisManagement.thesis.model.Council;
import com.duy.thesisManagement.thesis.model.Thesis;
import com.duy.thesisManagement.thesis.model.ThesisPosition;
import com.duy.thesisManagement.thesis.model.User;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ThesisRepository extends JpaRepository<Thesis, Integer> {

    Optional<ThesisRequestDTO> findByName(String name);
    Boolean existsByName(String name);

    public long countByCouncilId(Council id);

    @Query("SELECT c FROM Thesis c WHERE c.councilId = null")
    List<Thesis> getThesisNullCouncil();

}
