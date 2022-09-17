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
    private int id;
    @NotBlank
    private float score1;
    @NotBlank
    private float score2;
    @NotBlank
    private float score3;
    @NotBlank
    private float score4;

    @JoinColumn(name = "score_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.EAGER)
    private Score score;
}
