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
@Table(name = "council_position")
public class CouncilPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "council_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Council councilId;
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Position positionId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User userId;
    @OneToMany(mappedBy = "councilPositionId")
    private Set<Score> scoreSet;
}
