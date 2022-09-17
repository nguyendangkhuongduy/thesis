package com.duy.thesisManagement.thesis.controller;


import com.duy.thesisManagement.thesis.dto.FacultyRequestDTO;
import com.duy.thesisManagement.thesis.dto.ThesisRequestDTO;
import com.duy.thesisManagement.thesis.dto.UserRequestDTO;
import com.duy.thesisManagement.thesis.model.Council;
import com.duy.thesisManagement.thesis.model.Faculty;
import com.duy.thesisManagement.thesis.model.Thesis;
import com.duy.thesisManagement.thesis.model.User;
import com.duy.thesisManagement.thesis.repository.CouncilRepository;
import com.duy.thesisManagement.thesis.repository.FacultyRepository;
import com.duy.thesisManagement.thesis.repository.ThesisRepository;
import com.duy.thesisManagement.thesis.service.ThesisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/academic")
public class ThesisController {

    @Autowired
    private ThesisService thesisService;

    private ThesisRepository thesisRepository;
    private CouncilRepository councilRepository;
    private FacultyRepository facultyRepository;

    private ThesisRequestDTO thesisRequestDTO;

    @GetMapping("/thesis")
    public List<Thesis> getThesis() {
        return thesisService.getThesis();
    }

    @GetMapping(path = "/thesis/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Thesis> ThesisById(@PathVariable(value = "id") Integer id){
        Thesis thesis= thesisService.getThesisById(id);
        if(thesis == null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Thesis>(thesis, HttpStatus.OK);
    }


    @PostMapping("/thesis")
    public ResponseEntity<?> createdThesis(@Valid @RequestBody Thesis thesis){
        if(thesisRepository.existsByThesisname(thesisRequestDTO.getName())){
            return ResponseEntity
                    .badRequest()
                    .body("Error: Thesis have been created!!");
        }

        thesisService.createdThesis(thesis);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/thesis/{id}")
    public ResponseEntity<Thesis> UpdateThesis(@PathVariable(value = "id") Integer id, @Valid @RequestBody Thesis thesis){
        if(thesis == null){
            return ResponseEntity.notFound().build();
        }
        thesis.setName(thesisRequestDTO.getName());
        thesis.setActive(thesisRequestDTO.getActive());

        Council c = councilRepository.getReferenceById(thesisRequestDTO.getCouncilId());
        thesis.setCouncilId(c);

        Faculty f = facultyRepository.getReferenceById(thesisRequestDTO.getFacultyId());
        thesis.setFacultyId(f);

        thesisRepository.save(thesis);
        return ResponseEntity.ok().build();

    }
}
