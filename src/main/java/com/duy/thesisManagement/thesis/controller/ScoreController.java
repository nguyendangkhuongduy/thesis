package com.duy.thesisManagement.thesis.controller;

import com.duy.thesisManagement.thesis.dto.ScoreCreationDTO;
import com.duy.thesisManagement.thesis.dto.ScoreDTO;
import com.duy.thesisManagement.thesis.dto.ThesisCreationDTO;
import com.duy.thesisManagement.thesis.dto.ThesisRequestDTO;
import com.duy.thesisManagement.thesis.model.ScoreResponse;
import com.duy.thesisManagement.thesis.model.ThesisResponse;
import com.duy.thesisManagement.thesis.service.ScoreService;
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
@RequestMapping("/score")
@AllArgsConstructor
public class ScoreController {

    private final ScoreService scoreService;
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            description = "get all score",
            security = @SecurityRequirement(name = "Bearer Authentication"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success fetching theses",
                            content = @Content(schema = @Schema(implementation = ScoreDTO.class))),
                    @ApiResponse(responseCode = "401", description = "Authentication error")
            }
    )
    public ResponseEntity<ScoreResponse> getScores() {
        List<ScoreDTO> scores = scoreService.getScores();
        ScoreResponse response = new ScoreResponse();
        response.setScore(scores);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/action/getByThesisId/{thesisId}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            description = "get all score",
            security = @SecurityRequirement(name = "Bearer Authentication"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success fetching theses",
                            content = @Content(schema = @Schema(implementation = ScoreDTO.class))),
                    @ApiResponse(responseCode = "401", description = "Authentication error")
            }
    )
    public ResponseEntity<ScoreResponse> getScoresByThesisId(@PathVariable(value = "thesisId") Integer thesisId) {
        List<ScoreDTO> scores = scoreService.getScoreByThesisId(thesisId);
        ScoreResponse response = new ScoreResponse();
        response.setScore(scores);
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<ScoreDTO> createScore(@Valid @RequestBody ScoreCreationDTO scoreCreationDTO) {
        ScoreDTO createdScore = this.scoreService.createdScore(scoreCreationDTO);
        return ResponseEntity.ok(createdScore);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteScore(@PathVariable(value = "id") Integer id) {
        this.scoreService.deleteScore(id);
        return ResponseEntity.ok("Successfully soft delete score");
    }
}
