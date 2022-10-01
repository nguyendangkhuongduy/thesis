package com.duy.thesisManagement.thesis.model;


import com.duy.thesisManagement.thesis.dto.CriteriaDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CriteriaResponse {
    List<CriteriaDTO> criteria;
}
