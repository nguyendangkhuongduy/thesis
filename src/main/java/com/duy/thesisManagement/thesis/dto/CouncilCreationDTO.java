package com.duy.thesisManagement.thesis.dto;


import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouncilCreationDTO {
    private Boolean active;
    private Date createdDate;
    private String name;
    private Integer facultyId;
    private Set<CouncilPositionCreationDTO> councilPositions;
}
