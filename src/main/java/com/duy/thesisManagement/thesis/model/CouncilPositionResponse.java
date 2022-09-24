package com.duy.thesisManagement.thesis.model;

import com.duy.thesisManagement.thesis.dto.CouncilPositionDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CouncilPositionResponse {
    List<CouncilPositionDTO> councilPosition;
}
