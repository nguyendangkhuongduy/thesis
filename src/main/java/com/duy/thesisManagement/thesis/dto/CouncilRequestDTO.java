package com.duy.thesisManagement.thesis.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CouncilRequestDTO {
    private String name;
    private Boolean active;
    private Data created_date;
    private Integer faculty_id;
}
