package com.duy.thesisManagement.thesis.model;

import com.duy.thesisManagement.thesis.dto.PositionDTO;
import com.duy.thesisManagement.thesis.dto.PositionRequestDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PositionResponse {
    List<PositionDTO> positions;
}
