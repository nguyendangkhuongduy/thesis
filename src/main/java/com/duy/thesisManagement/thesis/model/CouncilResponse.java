package com.duy.thesisManagement.thesis.model;


import com.duy.thesisManagement.thesis.dto.CouncilDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CouncilResponse {
    List<CouncilDTO> councils;
}
