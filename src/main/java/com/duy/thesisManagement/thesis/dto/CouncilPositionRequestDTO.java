package com.duy.thesisManagement.thesis.dto;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;

@Getter
@Setter
public class CouncilPositionRequestDTO {
    private Integer councilId;
    private Integer positionId;
    private Integer userId;
}
