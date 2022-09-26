package com.duy.thesisManagement.thesis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "thesis_position")
public class ThesisPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "thesis_position", referencedColumnName = "id")
    @ManyToOne
    private PositionForThesis thesisPosition;
    @JoinColumn(name = "thesis_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Thesis thesisId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;
}
