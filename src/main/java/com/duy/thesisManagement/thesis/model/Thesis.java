package com.duy.thesisManagement.thesis.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private int id;
    @NotBlank
    private String name;
    @NotBlank
    private boolean active;
    @NotBlank
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(name = "created_date")
    private Date createdDate;
    @NotBlank
    @Column(name = "total_score")
    private float totalScore;
    @JoinColumn(name = "council_id", referencedColumnName = "id")
    @ManyToOne
    private Council councilId;
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    @ManyToOne
    private Faculty facultyId;
    @OneToMany(mappedBy = "thesisId")
    private Set<Score> scoreSet;
    @OneToMany(mappedBy = "thesisId")
    private Set<ThesisPosition> thesisPositionSet;
}
