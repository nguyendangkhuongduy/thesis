package com.duy.thesisManagement.thesis.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

import javax.persistence.criteria.CriteriaBuilder;

@Getter
@Setter
public class ThesisPositionDTO {
    private Integer userId;
    private Integer thesisPosition;
    private Integer thesisId;
}
