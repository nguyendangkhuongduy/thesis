package com.duy.thesisManagement.thesis.controller;


import com.duy.thesisManagement.thesis.dto.*;
import com.duy.thesisManagement.thesis.model.CouncilResponse;
import com.duy.thesisManagement.thesis.model.UsersResponse;
import com.duy.thesisManagement.thesis.service.CouncilPositionService;
import com.duy.thesisManagement.thesis.service.CouncilService;
import com.duy.thesisManagement.thesis.service.CouncilCoreService;
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
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/academic")
public class CouncilController {
    private final CouncilService councilService;

    private final CouncilPositionService councilPositionService;

    @GetMapping(path = "/council", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            description = "get all councils",
            security = @SecurityRequirement(name = "Bearer Authentication"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success fetching all councils",
                            content = @Content(schema = @Schema(implementation = UsersResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Authentication error")
            }
    )
    public ResponseEntity<CouncilResponse> getCouncils() {
        List<CouncilDTO> councils = councilService.getAllCouncils();
        CouncilResponse response = new CouncilResponse();
        response.setCouncils(councils);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/council/{id}" ,produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            description = "get dedicated council by id",
            security = @SecurityRequirement(name = "Bearer Authentication"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success fetching council by id",
                            content = @Content(schema = @Schema(implementation = UserDTO.class))),
                    @ApiResponse(responseCode = "401", description = "Authentication error"),
                    @ApiResponse(responseCode = "404", description = "Cannot find any council")
            }
    )
//    public ResponseEntity<List<CouncilPositionDTO>> getCouncilPositionByCouncilId(@PathVariable(value = "councilId") Integer councilId) {
//        return new ResponseEntity<List<CouncilPositionDTO>>(councilService.getByCouncilId(councilId), HttpStatus.OK);
//    }
    public ResponseEntity<CouncilDTO> getCouncil(@PathVariable(value = "id") Integer id) {
        CouncilDTO councilDTO = councilService.getCouncilById(id);
        return ResponseEntity.ok(councilDTO);
    }

    @PostMapping(path = "/council")
    public ResponseEntity<CouncilDTO> createCouncil(@Valid @RequestBody CouncilCreationDTO councilCreationDTO) {
        CouncilDTO createdCouncil = this.councilService.createCouncil(councilCreationDTO);
        return ResponseEntity.ok(createdCouncil);
    }

    @PutMapping("/council/{id}")
    public ResponseEntity<CouncilDTO> UpdateCouncil(@PathVariable(value = "id") Integer id,
                                              @Valid @RequestBody CouncilUpdatingDTO councilUpdatingDTO) {
        CouncilDTO council = this.councilService.updateCouncil(id, councilUpdatingDTO);
        return ResponseEntity.ok(council);
    }


    @DeleteMapping("/council/{id}")
    public ResponseEntity<String> deleteCouncil(@PathVariable(value = "id") Integer id) {
        this.councilService.deleteCouncil(id);
        return ResponseEntity.ok("Successfully soft delete user");
    }
}
