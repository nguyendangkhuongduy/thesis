package com.duy.thesisManagement.thesis.controller;


import com.duy.thesisManagement.thesis.dto.CouncilRequestDTO;
import com.duy.thesisManagement.thesis.dto.UserRequestDTO;
import com.duy.thesisManagement.thesis.model.Council;
import com.duy.thesisManagement.thesis.model.Faculty;
import com.duy.thesisManagement.thesis.model.Position;
import com.duy.thesisManagement.thesis.model.User;
import com.duy.thesisManagement.thesis.repository.CouncilRepository;
import com.duy.thesisManagement.thesis.repository.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.PseudoColumnUsage;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/council")
public class CouncilController {
    private final CouncilRepository councilRepository;

    private final FacultyRepository facultyRepository;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Council>> listAllCouncil(){
        List<Council> listCouncil= councilRepository.findAll();
        if(listCouncil.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Council>>(listCouncil, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createdCouncil(@Valid @RequestBody CouncilRequestDTO councilRequestDTO){
//        if(councilRepository.existsByCouncilname(councilRequestDTO.getName())){
//            return ResponseEntity.badRequest().body("Error: Council have been created!");
//        }
        Council council = Council.builder().name(councilRequestDTO.getName()).build();

//        Faculty f = facultyRepository.findById(councilRequestDTO.getFaculty_id()).get();
//        Integer fid =f.getId();

//        council.setFacultyId(Integer.parseInt(fid));

        councilRepository.save(council);
        return ResponseEntity.ok(new ErrorMessage("User registered successfully!"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Council> UpdateCouncil(@PathVariable(value = "id") Integer id, @Valid @RequestBody CouncilRequestDTO councilRequestDTO){
        Council council = councilRepository.getOne(id);
        if(council == null) {
            return ResponseEntity.notFound().build();
        }

        council.setName(councilRequestDTO.getName());
//        council.setFacultyId(councilRequestDTO.getFaculty_id());
        council.setActive(councilRequestDTO.getActive());
        councilRepository.save(council);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Council> DeleteCouncil(@PathVariable(value = "id") Integer id) {
        Council council = councilRepository.getOne(id);
        if(council == null) {
            return ResponseEntity.notFound().build();
        }
        councilRepository.delete(council);
        return ResponseEntity.ok().build();
    }

    }
