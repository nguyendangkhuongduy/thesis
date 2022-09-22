package com.duy.thesisManagement.thesis.dto;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThesisCreationDTO {
    private String name;
    private Integer councilId;
    private Integer facultyId;
    private boolean active;
    private Date createdDate;
    private float totalScore;
}
