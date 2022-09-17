package com.duy.thesisManagement.thesis.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "position_for_thesis")
public class PositionForThesis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private AppThesisRole name;

    @OneToMany(mappedBy = "thesisPosition")
    private Set<ThesisPosition> thesisPositionSet;

}
