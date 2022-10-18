package com.duy.thesisManagement.thesis.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreDetailHasUserNameDTO {
    private Integer id;
    private Integer criteria_id;
    private Integer score_id;
    private Float mark;
    private String name;
}
