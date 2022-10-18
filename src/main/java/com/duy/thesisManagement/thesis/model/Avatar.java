package com.duy.thesisManagement.thesis.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "avatar")
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;

    private String type;

    @Lob
    private byte[] data;

//    private Integer userId;

//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    @ManyToOne(fetch = FetchType.EAGER)
//    private User userId;

//    public void avatar(String name, String type, byte[] data, Integer userId) {
//        this.name = name;
//        this.type = type;
//        this.data = data;
//        this.userId = userId;
//    }
//
//    public void avatar() {
//
//    }

}
