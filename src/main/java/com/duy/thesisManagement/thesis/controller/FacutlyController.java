package com.duy.thesisManagement.thesis.controller;

import com.duy.thesisManagement.thesis.model.Faculty;
import com.duy.thesisManagement.thesis.service.FacultyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/faculty")
public class FacutlyController {

    @Autowired
    private  FacultyService facultyService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Faculty>> listAllFaculty(){
        List<Faculty> listFaculty= facultyService.getFaculty();
        if(listFaculty.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Faculty>>(listFaculty, HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<?> createdFaculty(@Valid @RequestBody Faculty faculty){
        facultyService.createdFaculty(faculty);
        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable(value = "id") Integer id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }
}
