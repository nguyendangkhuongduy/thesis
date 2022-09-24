package com.duy.thesisManagement.thesis.controller;

import com.duy.thesisManagement.thesis.dto.*;
import com.duy.thesisManagement.thesis.model.CouncilPositionResponse;
import com.duy.thesisManagement.thesis.service.CouncilPositionService;
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

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/academic")
public class CouncilPositionController {

    private final CouncilPositionService councilPositionService;

    @GetMapping(path = "/councilPosition", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            description = "get all councilPosition",
            security = @SecurityRequirement(name = "Bearer Authentication"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success fetching all users",
                            content = @Content(schema = @Schema(implementation = CouncilPositionResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Authentication error")
            }
    )
    public ResponseEntity<CouncilPositionResponse> getCouncilPosition() {
        List<CouncilPositionDTO> councilPosition = councilPositionService.getAllCouncilPosition();
        CouncilPositionResponse response = new CouncilPositionResponse();
        response.setCouncilPosition(councilPosition);
        return ResponseEntity.ok(response);
    }
    @GetMapping(path = "/councilPosition/{councilId}" ,produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            description = "get dedicated council position by council id",
            security = @SecurityRequirement(name = "Bearer Authentication"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success fetching council position by council id",
                            content = @Content(schema = @Schema(implementation = CouncilPositionDTO.class))),
                    @ApiResponse(responseCode = "401", description = "Authentication error"),
                    @ApiResponse(responseCode = "404", description = "Cannot find any user")
            }
    )
    public ResponseEntity<List<CouncilPositionDTO>> getCouncilPosition(@PathVariable(value = "councilId") Integer councilId) {
        List<CouncilPositionDTO> councilPosition = councilPositionService.getCouncilPositionByCouncilId(councilId);
        return ResponseEntity.ok(councilPosition);
    }

    @PostMapping(path = "/councilPosition")
    public ResponseEntity<CouncilPositionDTO> createCouncilPosition(@Valid @RequestBody CouncilPositionCreationDTO councilPositionCreationDTO) {
        CouncilPositionDTO createdCouncilPosition = this.councilPositionService.createdCouncilPosition(councilPositionCreationDTO);
        return ResponseEntity.ok(createdCouncilPosition);
    }

    @PutMapping("/councilPosition/{id}")
    public ResponseEntity<CouncilPositionDTO> UpdateCouncilPosition(@PathVariable(value = "id") Integer id,
                                              @Valid @RequestBody CouncilPositionUpdatingDTO councilPositionUpdatingDTO) {
        CouncilPositionDTO councilPosition = this.councilPositionService.updatedCouncilPosition(id, councilPositionUpdatingDTO);
        return ResponseEntity.ok(councilPosition);
    }
}
