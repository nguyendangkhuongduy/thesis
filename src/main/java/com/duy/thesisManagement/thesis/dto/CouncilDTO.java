package com.duy.thesisManagement.thesis.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouncilDTO {
    private Integer id;
    private Boolean active;
    private Date createdDate;
    private String name;
    private String faculty;
}
