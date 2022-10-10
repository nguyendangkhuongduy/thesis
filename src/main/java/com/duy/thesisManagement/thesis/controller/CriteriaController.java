package com.duy.thesisManagement.thesis.controller;

import com.duy.thesisManagement.thesis.dto.*;
import com.duy.thesisManagement.thesis.model.CriteriaResponse;
import com.duy.thesisManagement.thesis.service.CriteriaService;
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
@RequestMapping(path = "/criteria")
@RequiredArgsConstructor
public class CriteriaController {
    private final CriteriaService criteriaService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            description = "get all criteria",
            security = @SecurityRequirement(name = "Bearer Authentication"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success fetching all users",
                            content = @Content(schema = @Schema(implementation = CriteriaResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Authentication error")
            }
    )
    public ResponseEntity<CriteriaResponse> getAllCriteria() {
        List<CriteriaDTO> criteria = criteriaService.getAllCriteria();
        CriteriaResponse response = new CriteriaResponse();
        response.setCriteria(criteria);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CriteriaDTO> createCriteria(@Valid @RequestBody CriteriaCreationDTO criteriaCreationDTO) {
        CriteriaDTO create = this.criteriaService.createCriteria(criteriaCreationDTO);
        return ResponseEntity.ok(create);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CriteriaDTO> UpdateCriteria(@PathVariable(value = "id") Integer id,
                                                      @Valid @RequestBody CriteriaUpdatingDTO criteriaUpdatingDTO) {
        CriteriaDTO criteria = this.criteriaService.updateCriteria(id, criteriaUpdatingDTO);
        return ResponseEntity.ok(criteria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCriteria(@PathVariable(value = "id") Integer id) {
        this.criteriaService.deleteCriteria(id);
        return ResponseEntity.ok("Successfully soft delete user");
    }
}
