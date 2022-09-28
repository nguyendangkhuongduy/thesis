package com.duy.thesisManagement.thesis.model;

import com.duy.thesisManagement.thesis.dto.ThesisPositionDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ThesisPositionResponse {
    List<ThesisPositionDTO> thesisPosition;
}
