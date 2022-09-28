package com.duy.thesisManagement.thesis.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity()
@Table(name = "council_position")

public class CouncilPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ToString.Exclude
    @JoinColumn(name = "council_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
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
