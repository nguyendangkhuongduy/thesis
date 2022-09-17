package com.duy.thesisManagement.thesis.repository;

import com.duy.thesisManagement.thesis.model.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ThesisRepository extends JpaRepository<Thesis, Integer> {

    Optional<Thesis> findThesisByname(String name);
    Boolean existsByThesisname(String name);
}
