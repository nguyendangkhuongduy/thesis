package com.duy.thesisManagement.thesis.controller;

import com.duy.thesisManagement.thesis.model.UsersResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
        description = "get all users",
        security = @SecurityRequirement(name = "basicAuth"),
        responses = {
            @ApiResponse(responseCode = "200", description = "Success fetching all users",
                content = @Content(schema = @Schema(implementation = UsersResponse.class))),
            @ApiResponse(responseCode = "401", description = "Authentication error")
        }
    )
    public ResponseEntity<UsersResponse> getUsers()
    {
        UsersResponse result = new UsersResponse();
        result.setUsers(List.of("duy", "Di"));
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Void> createUser()
    {
        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }

    // PUT & DELETE
}
