package com.duy.thesisManagement.thesis.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ThesisPositionCreationDTO {
    private Integer userId;
    private Integer thesisPosition;
    private Integer thesisId;
}

