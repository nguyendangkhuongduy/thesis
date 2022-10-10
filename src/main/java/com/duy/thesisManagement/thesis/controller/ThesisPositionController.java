package com.duy.thesisManagement.thesis.controller;


import com.duy.thesisManagement.thesis.dto.*;
import com.duy.thesisManagement.thesis.model.CouncilPositionResponse;
import com.duy.thesisManagement.thesis.model.ThesisPositionResponse;
import com.duy.thesisManagement.thesis.model.UsersResponse;
import com.duy.thesisManagement.thesis.service.ThesisPositionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/thesisPosition")
@RequiredArgsConstructor
public class ThesisPositionController {

    private final ThesisPositionService thesisPositionService;


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            description = "get all thesis position",
            security = @SecurityRequirement(name = "Bearer Authentication"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success fetching all thesis position",
                            content = @Content(schema = @Schema(implementation = ThesisPositionResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Authentication error")
            }
    )
    public ResponseEntity<ThesisPositionResponse> getThesisPosition() {
        List<ThesisPositionDTO> thesisPositionDTO = thesisPositionService.getAllThesisPosition();
        ThesisPositionResponse response = new ThesisPositionResponse();
        response.setThesisPosition(thesisPositionDTO);
        return ResponseEntity.ok(response);
    }


    @GetMapping(path = "/Action/getByUser/{id}" ,produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            description = "get dedicated thesis position by thesis id",
            security = @SecurityRequirement(name = "Bearer Authentication"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success fetching thesis position by thesis id",
                            content = @Content(schema = @Schema(implementation = ThesisPositionDTO.class))),
                    @ApiResponse(responseCode = "401", description = "Authentication error"),
                    @ApiResponse(responseCode = "404", description = "Cannot find any Id")
            }
    )
    public ResponseEntity<ThesisPositionResponse> getThesisPositionByThesisId(@PathVariable(value = "id") Integer id) {
        List<ThesisPositionDTO> thesisPositionDTO = thesisPositionService.getThesisPositionByThesisId(id);
        ThesisPositionResponse response = new ThesisPositionResponse();
        response.setThesisPosition(thesisPositionDTO);
        return ResponseEntity.ok(response);
    }

//    @GetMapping(path = "/{id}" ,produces = {MediaType.APPLICATION_JSON_VALUE})
//    @Operation(
//            description = "get dedicated thesis position by thesis id",
//            security = @SecurityRequirement(name = "Bearer Authentication"),
//            responses = {
//                    @ApiResponse(responseCode = "200", description = "Success fetching thesis position by thesis id",
//                            content = @Content(schema = @Schema(implementation = ThesisPositionDTO.class))),
//                    @ApiResponse(responseCode = "401", description = "Authentication error"),
//                    @ApiResponse(responseCode = "404", description = "Cannot find any Id")
//            }
//    )
//    public ResponseEntity<ThesisPositionResponse> getThesisPositionByUserId(@PathVariable(value = "id") Integer id) {
//        List<ThesisPositionDTO> thesisPositionDTO = thesisPositionService.getThesisPositionByUserId(id);
//        ThesisPositionResponse response = new ThesisPositionResponse();
//        response.setThesisPosition(thesisPositionDTO);
//        return ResponseEntity.ok(response);
//    }

    @PostMapping
    public ResponseEntity<ThesisPositionDTO> createThesisPosition(@Valid @RequestBody ThesisPositionCreationDTO thesisPositionCreationDTO) {
        ThesisPositionDTO create = this.thesisPositionService.createThesisPosition(thesisPositionCreationDTO);
        return ResponseEntity.ok(create);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePosition(@PathVariable(value = "id") Integer id) {
        this.thesisPositionService.deleteThesisPosition(id);
        return ResponseEntity.ok("Successfully delete Thesis Position");
    }
}
