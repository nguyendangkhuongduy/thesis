package com.duy.thesisManagement.thesis.controller;


import com.duy.thesisManagement.thesis.dto.*;
import com.duy.thesisManagement.thesis.model.*;
import com.duy.thesisManagement.thesis.repository.CouncilRepository;
import com.duy.thesisManagement.thesis.repository.FacultyRepository;
import com.duy.thesisManagement.thesis.repository.ThesisRepository;
import com.duy.thesisManagement.thesis.service.ThesisService;
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

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/academic")
public class ThesisController {

    @Autowired
    private ThesisService thesisService;

    private ThesisRequestDTO thesisRequestDTO;

    @GetMapping( path = "/thesis", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            description = "get all theses",
            security = @SecurityRequirement(name = "Bearer Authentication"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success fetching theses",
                            content = @Content(schema = @Schema(implementation = ThesisRequestDTO.class))),
                    @ApiResponse(responseCode = "401", description = "Authentication error")
            }
    )
    public ResponseEntity<ThesisResponse> getTheses() {
        List<ThesisRequestDTO> thesis = thesisService.getTheses();
        ThesisResponse response = new ThesisResponse();
        response.setTheses(thesis);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/thesis/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            description = "get dedicated thesis by id",
            security = @SecurityRequirement(name = "Bearer Authentication"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success fetching thesis by id",
                            content = @Content(schema = @Schema(implementation = UserDTO.class))),
                    @ApiResponse(responseCode = "401", description = "Authentication error"),
                    @ApiResponse(responseCode = "404", description = "Cannot find any thesis")
            }
    )
    public ResponseEntity<ThesisRequestDTO> getThesis(@PathVariable(value = "id") Integer id){
        ThesisRequestDTO thesis = thesisService.getThesisById(id);
        return ResponseEntity.ok(thesis);
    }


    @PostMapping("/thesis")
    public ResponseEntity<ThesisRequestDTO> createThesis(@Valid @RequestBody ThesisRequestDTO thesisRequestDTO) {
        ThesisRequestDTO createdThesis = this.thesisService.createdThesis(thesisRequestDTO);
        return ResponseEntity.ok(createdThesis);
    }

    @PutMapping(path = "/thesis/{id}")
    public ResponseEntity<ThesisRequestDTO> UpdateThesis(@PathVariable(value = "id") Integer id,
                                              @Valid @RequestBody ThesisUpdatingDTO thesisUpdatingDTO) {
        ThesisRequestDTO thesis = this.thesisService.updateThesis(id, thesisUpdatingDTO);
        return ResponseEntity.ok(thesis);
    }

    @DeleteMapping("/thesis/{id}")
    public ResponseEntity<String> deleteThesis(@PathVariable(value = "id") Integer id) {
        this.thesisService.deleteThesis(id);
        return ResponseEntity.ok("Successfully soft delete user");
    }
}
