package com.duy.thesisManagement.thesis.controller;


import com.duy.thesisManagement.thesis.dto.*;
import com.duy.thesisManagement.thesis.model.ScoreDetail;
import com.duy.thesisManagement.thesis.model.ScoreDetailHasUserNameResponse;
import com.duy.thesisManagement.thesis.model.ScoreDetailResponse;
import com.duy.thesisManagement.thesis.model.ThesisPositionResponse;
import com.duy.thesisManagement.thesis.repository.ScoreDetailRepository;
import com.duy.thesisManagement.thesis.service.ScoreDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/scoreDetail")
@AllArgsConstructor
public class ScoreDetailController {
    private final ScoreDetailService scoreDetailService;
    private final ScoreDetailRepository scoreDetailRepository;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            description = "get all scoreDetails",
            security = @SecurityRequirement(name = "Bearer Authentication"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success fetching all scoreDetails",
                            content = @Content(schema = @Schema(implementation = ScoreDetailResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Authentication error")
            }
    )
    public ResponseEntity<ScoreDetailResponse> getScoreDetails() {
        List<ScoreDetailDTO> scoreDetailDTO = scoreDetailService.getAll();
        ScoreDetailResponse response = new ScoreDetailResponse();
        response.setScoreDetails(scoreDetailDTO);
        return ResponseEntity.ok(response);
    }



    @GetMapping(path = "/{id}" ,produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            description = "get dedicated scoreDetail by score id",
            security = @SecurityRequirement(name = "Bearer Authentication"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success fetching scoreDetail by score id",
                            content = @Content(schema = @Schema(implementation = ThesisPositionDTO.class))),
                    @ApiResponse(responseCode = "401", description = "Authentication error"),
                    @ApiResponse(responseCode = "404", description = "Cannot find any Id")
            }
    )
    public ResponseEntity<ScoreDetailResponse> getScoreDetailByScoreId(@PathVariable(value = "id") Integer id) {
        List<ScoreDetailDTO> scoreDetailDTO = scoreDetailService.getScoreDetailByScoreId(id);
        ScoreDetailResponse response = new ScoreDetailResponse();
        response.setScoreDetails(scoreDetailDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/action/getByThesis/{thesisId}" ,produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            description = "get dedicated scoreDetail by score id",
            security = @SecurityRequirement(name = "Bearer Authentication"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success fetching scoreDetail by score id",
                            content = @Content(schema = @Schema(implementation = ThesisPositionDTO.class))),
                    @ApiResponse(responseCode = "401", description = "Authentication error"),
                    @ApiResponse(responseCode = "404", description = "Cannot find any Id")
            }
    )
    public ResponseEntity<ScoreDetailResponse> getScoreDetailByThesisId(@PathVariable(value = "thesisId") Integer thesisId) {
        List<ScoreDetailDTO> scoreDetailDTO = scoreDetailService.getScoreDetailByScore(thesisId);
        ScoreDetailResponse response = new ScoreDetailResponse();
        response.setScoreDetails(scoreDetailDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ScoreDetailDTO> createScoreDetail(@Valid @RequestBody ScoreDetailCreationDTO scoreDetailCreationDTO) {
        ScoreDetailDTO create = this.scoreDetailService.createdScoreDetail(scoreDetailCreationDTO);
        return ResponseEntity.ok(create);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScoreDetailDTO> UpdateScoreDetail(@PathVariable(value = "id") Integer id,
                                              @Valid @RequestBody ScoreDetailUpdatingDTO scoreDetailUpdatingDTO) {
        ScoreDetailDTO scoreDetail = this.scoreDetailService.updateScoreDetail(id, scoreDetailUpdatingDTO);
        return ResponseEntity.ok(scoreDetail);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteScoreDetail(@PathVariable(value = "id") Integer id) {
        this.scoreDetailService.deleteScoreDetail(id);
        return ResponseEntity.ok("Successfully delete scoreDetail");
    }

}
