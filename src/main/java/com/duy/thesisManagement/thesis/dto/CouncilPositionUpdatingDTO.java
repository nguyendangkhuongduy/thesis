package com.duy.thesisManagement.thesis.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouncilPositionUpdatingDTO {
    private Integer userId;
    private Integer facultyId;
    private Integer councilId;
}
