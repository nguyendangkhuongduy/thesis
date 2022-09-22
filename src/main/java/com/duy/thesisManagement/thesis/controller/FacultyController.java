package com.duy.thesisManagement.thesis.controller;

import com.duy.thesisManagement.thesis.dto.FacultyRequestDTO;
import com.duy.thesisManagement.thesis.dto.FacultyUpdatingDTO;
import com.duy.thesisManagement.thesis.model.Faculty;
import com.duy.thesisManagement.thesis.model.FacultyResponse;
import com.duy.thesisManagement.thesis.model.UsersResponse;
import com.duy.thesisManagement.thesis.service.FacultyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/faculty")
@RequiredArgsConstructor
public class FacultyController {

    @Autowired
    private  FacultyService facultyService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            description = "get all Faculties",
            security = @SecurityRequirement(name = "Bearer Authentication"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success fetching all Faculties",
                            content = @Content(schema = @Schema(implementation = UsersResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Authentication error")
            }
    )
    public ResponseEntity<FacultyResponse>  getAllFaculty(){
        List<FacultyRequestDTO> listFaculty= facultyService.getAllFaculties();
        FacultyResponse response = new FacultyResponse();
        response.setFaculties(listFaculty);
        return ResponseEntity.ok(response);
    }


    @PostMapping()
    public ResponseEntity<?> createdFaculty(@Valid @RequestBody FacultyUpdatingDTO facultyUpdatingDTO){
        FacultyRequestDTO createdFaculty = this.facultyService.createdFaculty(facultyUpdatingDTO);
        return ResponseEntity.ok(createdFaculty);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable(value = "id") Integer id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }
}
