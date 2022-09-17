package com.duy.thesisManagement.thesis.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class PositionRequestDTO {
    private Set<String> name;
}
