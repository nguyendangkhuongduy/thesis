package com.duy.thesisManagement.thesis.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@Table(name = "faculty")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String name;


    @OneToMany(mappedBy = "facultyId")
    private Set<User> userSet;
    @OneToMany(mappedBy = "facultyId")
    private Set<Council> councilSet;
    @OneToMany(mappedBy = "facultyId")
    private Set<Thesis> thesisSet;
}
