package com.duy.thesisManagement.thesis.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ScoreCreationDTO {
    private Integer thesisId;
    private Integer councilPositionId;
}
