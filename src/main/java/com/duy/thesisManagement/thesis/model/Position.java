package com.duy.thesisManagement.thesis.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@Table(name = "position")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private AppCouncilRole name;

    @OneToMany(mappedBy = "positionId")
    private Set<CouncilPosition> councilPositionSet;
}
