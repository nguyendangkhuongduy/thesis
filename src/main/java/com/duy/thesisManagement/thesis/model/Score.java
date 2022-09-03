package com.duy.thesisManagement.thesis.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "score")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "council_position_id", referencedColumnName = "id")
    @ManyToOne
    private CouncilPosition councilPositionId;
    @JoinColumn(name = "thesis_id", referencedColumnName = "id")
    @ManyToOne
    private Thesis thesisId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "score")
    private ScoreDetail scoreDetail;

}
