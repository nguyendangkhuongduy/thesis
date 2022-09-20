package com.duy.thesisManagement.thesis.model;


import com.duy.thesisManagement.thesis.dto.ThesisRequestDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ThesisResponse {
    List<ThesisRequestDTO> theses;
}
