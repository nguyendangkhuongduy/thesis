package com.duy.thesisManagement.thesis.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "position")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private boolean active;


    @OneToMany(mappedBy = "positionId")
    private Set<CouncilPosition> councilPositionSet;
}
