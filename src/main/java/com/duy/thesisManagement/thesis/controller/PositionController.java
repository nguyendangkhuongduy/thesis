package com.duy.thesisManagement.thesis.controller;


import com.duy.thesisManagement.thesis.dto.FacultyRequestDTO;
import com.duy.thesisManagement.thesis.dto.PositionRequestDTO;
import com.duy.thesisManagement.thesis.model.Faculty;
import com.duy.thesisManagement.thesis.model.Position;
import com.duy.thesisManagement.thesis.model.Role;
import com.duy.thesisManagement.thesis.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/position")
public class PositionController {

    private final PositionRepository positionRepository;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Position>> listAllPosition(){
        List<Position> listPosition= positionRepository.findAll();
        if(listPosition.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Position>>(listPosition, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createdFaculty(@Valid @RequestBody PositionRequestDTO positionRequestDTO){

//        Position position = Position.builder().name(positionRequestDTO.getName()).build();

        Set<String> councilRoles = positionRequestDTO.getName();
//        Set<> roles = new HashSet<>();
//
//
//
//        facultyRepository.save(faculty);
        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }




}
