package com.duy.thesisManagement.thesis.model;

import com.duy.thesisManagement.thesis.dto.FacultyRequestDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FacultyResponse {
    List<FacultyRequestDTO> faculties;
}
