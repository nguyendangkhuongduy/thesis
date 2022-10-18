package com.duy.thesisManagement.thesis.model;


import lombok.Data;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

@Data
public class AvatarDbResponse {
    private String name;
    private String url;
    private String type;

//    private Integer userId;
    private long size;

    public AvatarDbResponse(String name, String url, String type, long size) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
//        this.userId = userId;
    }
}
