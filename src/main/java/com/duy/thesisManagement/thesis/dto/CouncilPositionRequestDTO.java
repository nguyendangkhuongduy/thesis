package com.duy.thesisManagement.thesis.dto;


import lombok.*;

import javax.persistence.criteria.CriteriaBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouncilPositionRequestDTO {
    private Integer id;
    private Integer councilId;
    private Integer positionId;
    private Integer userId;
}
