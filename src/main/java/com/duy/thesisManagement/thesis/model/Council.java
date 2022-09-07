package com.duy.thesisManagement.thesis.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@Table(name = "council")
public class Council {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String name;
    @NotBlank
    private boolean active;
    @NotBlank
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date createdDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Faculty facultyId;
    @OneToMany(mappedBy = "councilId")
    private Set<CouncilPosition> councilPositionSet;
    @OneToMany(mappedBy = "councilId")
    private Set<Thesis> thesisSet;
}
