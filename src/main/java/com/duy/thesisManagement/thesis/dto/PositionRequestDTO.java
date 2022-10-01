package com.duy.thesisManagement.thesis.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PositionRequestDTO {
    private Integer id;
    private String name;
}
