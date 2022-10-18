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
@RequestMapping()
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


//    @GetMapping( path = "/thesisNullCouncil", produces = {MediaType.APPLICATION_JSON_VALUE})
//    @Operation(
//            description = "get theses",
//            security = @SecurityRequirement(name = "Bearer Authentication"),
//            responses = {
//                    @ApiResponse(responseCode = "200", description = "Success fetching theses",
//                            content = @Content(schema = @Schema(implementation = ThesisRequestDTO.class))),
//                    @ApiResponse(responseCode = "401", description = "Authentication error")
//            }
//    )
//    public ResponseEntity<ThesisResponse> getThesesNullCouncil() {
//        List<ThesisRequestDTO> thesis = thesisService.getThesesNullCouncil();
//        ThesisResponse response = new ThesisResponse();
//        response.setTheses(thesis);
//        return ResponseEntity.ok(response);
//    }

    @GetMapping(path = "/thesis/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            description = "get dedicated thesis by id",
            security = @SecurityRequirement(name = "Bearer Authentication"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success fetching thesis by id",
                            content = @Content(schema = @Schema(implementation = ThesisRequestDTO.class))),
                    @ApiResponse(responseCode = "401", description = "Authentication error"),
                    @ApiResponse(responseCode = "404", description = "Cannot find any thesis")
            }
    )
    public ResponseEntity<ThesisRequestDTO> getThesis(@PathVariable(value = "id") Integer id){
        ThesisRequestDTO thesis = thesisService.getThesisById(id);
        return ResponseEntity.ok(thesis);
    }

    @GetMapping(path = "/thesis/thesisByCouncilId/{councilId}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            description = "get dedicated thesis by id",
            security = @SecurityRequirement(name = "Bearer Authentication"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success fetching thesis by id",
                            content = @Content(schema = @Schema(implementation = ThesisRequestDTO.class))),
                    @ApiResponse(responseCode = "401", description = "Authentication error"),
                    @ApiResponse(responseCode = "404", description = "Cannot find any thesis")
            }
    )
    public ResponseEntity<ThesisResponse> getThesisByCouncilId(@PathVariable(value = "councilId") Integer councilId){
        List<ThesisRequestDTO> thesis = thesisService.getThesesByCouncilId(councilId);
        ThesisResponse response = new ThesisResponse();
        response.setTheses(thesis);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/thesis/thesisByUserId/{userId}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            description = "get dedicated thesis by id",
            security = @SecurityRequirement(name = "Bearer Authentication"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success fetching thesis by id",
                            content = @Content(schema = @Schema(implementation = ThesisRequestDTO.class))),
                    @ApiResponse(responseCode = "401", description = "Authentication error"),
                    @ApiResponse(responseCode = "404", description = "Cannot find any thesis")
            }
    )
    public ResponseEntity<ThesisResponse> getThesisByUserId(@PathVariable(value = "userId") Integer userId){
        List<ThesisRequestDTO> thesis = thesisService.getThesisByUserId(userId);
        ThesisResponse response = new ThesisResponse();
        response.setTheses(thesis);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/thesis/count/{id}")
    public ResponseEntity<?> countThesis(@PathVariable(value = "id") Integer id){
        Long count = this.thesisService.countThesisByCouncilId(id);
        return ResponseEntity.ok(count);
    }


    @PostMapping("/thesis")
    public ResponseEntity<ThesisRequestDTO> createThesis(@Valid @RequestBody ThesisCreationDTO thesisCreationDTO) {
        ThesisRequestDTO createdThesis = this.thesisService.createdThesis(thesisCreationDTO);
        return ResponseEntity.ok(createdThesis);
    }

    @PutMapping(path = "/thesis/{id}")
    public ResponseEntity<ThesisRequestDTO> UpdateThesis(@PathVariable(value = "id") Integer id,
                                              @Valid @RequestBody ThesisUpdatingDTO thesisUpdatingDTO) {
        ThesisRequestDTO thesis = this.thesisService.updateThesis(id, thesisUpdatingDTO);
        return ResponseEntity.ok(thesis);
    }

    @PutMapping(path = "/thesis/action/putFile/{id}")
    public ResponseEntity<String> PutFile(@PathVariable(value = "id") Integer id,
                                                         @Valid @RequestBody ThesisPutFileDTO thesisPutFileDTO) {
        this.thesisService.putFile(id, thesisPutFileDTO);
        return ResponseEntity.ok("Successfully put file");
    }

    @PutMapping(path = "/thesis/addCouncil/{id}")
    public ResponseEntity<ThesisRequestDTO> addCouncil(@PathVariable(value = "id") Integer id,
                                                       @Valid @RequestBody ThesisAddCouncilDTO thesisAddCouncilDTO) {
        ThesisRequestDTO thesis = this.thesisService.addCouncil(id, thesisAddCouncilDTO);
        return ResponseEntity.ok(thesis);
    }

    @PutMapping(path = "/thesis/addTotalScore/{id}")
    public ResponseEntity<ThesisRequestDTO> addTotalScore(@PathVariable(value = "id") Integer id,
                                                       @Valid @RequestBody ThesisAddTotalScoreDTO thesisAddCouncilDTO) {
        ThesisRequestDTO thesis = this.thesisService.addTotalScore(id, thesisAddCouncilDTO);
        return ResponseEntity.ok(thesis);
    }

    @PutMapping(path = "/thesis/{id}/removeCouncil")
    public ResponseEntity<String> addTotalScore(@PathVariable(value = "id") Integer id ) {
        this.thesisService.removeCouncil(id);
        return ResponseEntity.ok("Successfully remove council");
    }

    @DeleteMapping("/thesis/{id}")
    public ResponseEntity<String> deleteThesis(@PathVariable(value = "id") Integer id) {
        this.thesisService.deleteThesis(id);
        return ResponseEntity.ok("Successfully soft delete thesis");
    }
}
