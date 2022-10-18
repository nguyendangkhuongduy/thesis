package com.duy.thesisManagement.thesis.repository;

import com.duy.thesisManagement.thesis.model.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Integer> {
}
