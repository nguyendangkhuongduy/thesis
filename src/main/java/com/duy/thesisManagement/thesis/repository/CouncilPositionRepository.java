package com.duy.thesisManagement.thesis.repository;

import com.duy.thesisManagement.thesis.dto.CouncilPositionDTO;
import com.duy.thesisManagement.thesis.model.Council;
import com.duy.thesisManagement.thesis.model.CouncilPosition;
import com.duy.thesisManagement.thesis.model.Position;
import com.duy.thesisManagement.thesis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.hibernate.hql.internal.antlr.HqlTokenTypes.FROM;
import static org.hibernate.loader.Loader.SELECT;

@Repository
public interface CouncilPositionRepository extends JpaRepository<CouncilPosition, Integer> {

    public  List<CouncilPosition> findByCouncilId(Council id);

    public long countByCouncilId(Council id);

    @Query("SELECT count(positionId) FROM CouncilPosition WHERE councilId=:council AND positionId=1")
    public long countPresidentByCouncilId(Council council);

    @Query("SELECT count(positionId) FROM CouncilPosition WHERE councilId=:council AND positionId=2")
    public long countSecretaryByCouncilId(Council council);

    @Query("SELECT count(positionId) FROM CouncilPosition WHERE councilId=:council AND positionId=3")
    public long countCriticalByCouncilId(Council council);


    @Query("SELECT count(positionId) FROM CouncilPosition WHERE councilId=:council AND positionId=4")
    public long countMemberByCouncilId(Council council);

    @Query("SELECT c FROM CouncilPosition c WHERE c.userId=:user")
    public List<CouncilPosition> getByUserId(User user);



}
