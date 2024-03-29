package com.duy.thesisManagement.thesis.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "thesis")
public class Thesis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private boolean active;

    @NotBlank
    @CreationTimestamp
    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "total_score")
    private float totalScore;

    private String file;

    @JoinColumn(name = "council_id")
    @ManyToOne
    private Council councilId;

    @JoinColumn(name = "faculty_id")
    @ManyToOne
    private Faculty faculty;
    @OneToMany(mappedBy = "thesisId")
    private Set<Score> scoreSet;
    @OneToMany(mappedBy = "thesisId")
    private Set<ThesisPosition> thesisPositionSet;
}
