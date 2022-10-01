package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dto.*;
import com.duy.thesisManagement.thesis.model.Position;
import com.duy.thesisManagement.thesis.model.User;

import java.util.List;

public interface PositionService {
    List<PositionDTO> getAllPosition();
    PositionDTO createPosition(PositionCreationDTO positionCreationDTO);
    void deletePosition(Integer id);
    PositionDTO getPositionById(Integer id);

    Position getPositionByID(Integer id);
}
