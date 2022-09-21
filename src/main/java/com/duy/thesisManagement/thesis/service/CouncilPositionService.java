package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.model.CouncilPosition;

public interface CouncilPositionService {
    CouncilPosition getCouncilPositionByCouncilId(Integer id);

    CouncilPosition createdCouncilPosition(CouncilPosition councilPosition);

//    CouncilPosition updateCouncilPosition(CouncilPosition councilPosition);

    void deleteCouncilPosition(Integer id);
}
