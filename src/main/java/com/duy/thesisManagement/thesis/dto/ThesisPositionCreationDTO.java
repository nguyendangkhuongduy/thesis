package com.duy.thesisManagement.thesis.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ThesisPositionCreationDTO {
    private Integer userId;
    private String name;
    private Integer thesisId;
}

