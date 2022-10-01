package com.duy.thesisManagement.thesis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "score_detail")
public class ScoreDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "score_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Score score;

    @JoinColumn(name = "criteria_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Criteria criteria;

    @NotBlank
    private float mark;


}
