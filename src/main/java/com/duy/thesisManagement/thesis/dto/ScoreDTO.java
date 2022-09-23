package com.duy.thesisManagement.thesis.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreDTO {
    private Integer id;
    private Integer thesisId;
    private Integer councilPositionId;
}
