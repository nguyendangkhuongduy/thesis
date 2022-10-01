package com.duy.thesisManagement.thesis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "score")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private boolean active;


    @JoinColumn(name = "council_position_id", referencedColumnName = "id")
    @ManyToOne
    private CouncilPosition councilPositionId;
    @JoinColumn(name = "thesis_id", referencedColumnName = "id")
    @ManyToOne
    private Thesis thesisId;

    @OneToMany(mappedBy = "score")
    private Set<ScoreDetail> scoreDetailSet;
}
