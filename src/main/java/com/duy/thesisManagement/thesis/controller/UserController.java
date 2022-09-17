package com.duy.thesisManagement.thesis.controller;

import com.duy.thesisManagement.thesis.dto.UserRequestDTO;
import com.duy.thesisManagement.thesis.model.AppRole;
import com.duy.thesisManagement.thesis.model.Role;
import com.duy.thesisManagement.thesis.model.User;
import com.duy.thesisManagement.thesis.model.UsersResponse;
import com.duy.thesisManagement.thesis.repository.RoleRepository;
import com.duy.thesisManagement.thesis.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

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

    @PostMapping
    public ResponseEntity<Void> createUser()
    {
        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }

    // PUT & DELETE
    @PutMapping("/{id}")
    public ResponseEntity<User> UpdateUser(@PathVariable(value = "id") Long id, @Valid @RequestBody UserRequestDTO userRequestDTO){
        User user = userRepository.getReferenceById(id);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        user.setFullName(userRequestDTO.getFullname().toString());
        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(userRequestDTO.getEmail());
        user.setGender(userRequestDTO.getGender());
        user.setPassword(userRequestDTO.getPassword());
        user.setActive(userRequestDTO.getActive());
        user.setPhone(userRequestDTO.getPhone());

        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> DeleteUser(@PathVariable(value = "id") Long id) {
        User user = userRepository.getOne(id);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }

    }
