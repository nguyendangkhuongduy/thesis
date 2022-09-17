package com.duy.thesisManagement.thesis.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;

@Getter
@Setter
public class ThesisRequestDTO {
    private String name;
    private Integer councilId;
    private Integer facultyId;
    private Boolean active;
}
