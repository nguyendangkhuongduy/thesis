package com.duy.thesisManagement.thesis.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class CouncilPositionCreationDTO {
    private Integer councilId;
    private Integer positionId;
    private Integer userId;
}
