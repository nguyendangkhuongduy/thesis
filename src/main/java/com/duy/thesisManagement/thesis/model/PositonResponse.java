package com.duy.thesisManagement.thesis.model;

import com.duy.thesisManagement.thesis.dto.PositionDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PositonResponse {
    List<PositionDTO> position;
}
