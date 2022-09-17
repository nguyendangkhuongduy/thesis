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
    private Faculty faculty;
    @OneToMany(mappedBy = "councilId")
    private Set<CouncilPosition> councilPositionSet;
    @OneToMany(mappedBy = "councilId")
    private Set<Thesis> thesisSet;
}
