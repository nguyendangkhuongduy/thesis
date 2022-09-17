package com.duy.thesisManagement.thesis.dto;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;

@Getter
@Setter
public class ScoreRequestDTO {
    private Integer councilPositionId;
    private Integer thesisId;
}
