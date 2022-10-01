package com.duy.thesisManagement.thesis.controller;


import com.duy.thesisManagement.thesis.dto.*;
import com.duy.thesisManagement.thesis.model.*;
import com.duy.thesisManagement.thesis.repository.PositionRepository;
import com.duy.thesisManagement.thesis.service.PositionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    private final PositionService positionService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            description = "get all position",
            security = @SecurityRequirement(name = "Bearer Authentication"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success fetching all position",
                            content = @Content(schema = @Schema(implementation = UsersResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Authentication error")
            }
    )
    public ResponseEntity<PositionResponse> getPosition() {
        List<PositionDTO> position = positionService.getAllPosition();
        PositionResponse response = new PositionResponse();
        response.setPositions(position);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<PositionDTO> createPosition(@Valid @RequestBody PositionCreationDTO positionCreationDTO) {
        PositionDTO createdPosition = this.positionService.createPosition(positionCreationDTO);
        return ResponseEntity.ok(createdPosition);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePosition(@PathVariable(value = "id") Integer id) {
        this.positionService.deletePosition(id);
        return ResponseEntity.ok("Successfully delete Position");
    }



}
