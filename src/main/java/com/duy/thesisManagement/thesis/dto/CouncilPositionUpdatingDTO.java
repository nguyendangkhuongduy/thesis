package com.duy.thesisManagement.thesis.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CouncilPositionUpdatingDTO {
    private Integer userId;
    private Integer positionId;
}
