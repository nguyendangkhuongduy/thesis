package com.duy.thesisManagement.thesis.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

import javax.persistence.criteria.CriteriaBuilder;

@Getter
@Setter
@Builder
public class ThesisPositionDTO {
    private Integer id;
    private Integer userId;
    private Integer thesisPosition;
    private Integer thesisId;
}
