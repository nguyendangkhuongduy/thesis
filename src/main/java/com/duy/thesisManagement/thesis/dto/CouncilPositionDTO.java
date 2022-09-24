package com.duy.thesisManagement.thesis.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouncilPositionDTO {
    private Integer id;
    private Integer councilId;
    private Integer positionId;
    private Integer userId;
}
