package com.duy.thesisManagement.thesis.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CouncilPositionCreationDTO {
    private Integer councilId;
    private Integer positionId;
    private Integer userId;
}
