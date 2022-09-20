package com.duy.thesisManagement.thesis.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThesisUpdatingDTO {
    private String name;
    private Float totalScore;
    private Integer councilId;
    private Integer facultyId;
}
