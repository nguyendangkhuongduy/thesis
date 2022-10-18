package com.duy.thesisManagement.thesis.controller;

import com.duy.thesisManagement.thesis.dto.*;
import com.duy.thesisManagement.thesis.model.AppRole;
import com.duy.thesisManagement.thesis.model.UsersResponse;
import com.duy.thesisManagement.thesis.repository.RoleRepository;
import com.duy.thesisManagement.thesis.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


//    @GetMapping(path = "/associate",produces = {MediaType.APPLICATION_JSON_VALUE})
//    @Operation(
//            description = "get all Associate",
//            security = @SecurityRequirement(name = "Bearer Authentication"),
//            responses = {
//                    @ApiResponse(responseCode = "200", description = "Success fetching all users",
//                            content = @Content(schema = @Schema(implementation = UsersResponse.class))),
//                    @ApiResponse(responseCode = "401", description = "Authentication error")
//            }
//    )
//    public ResponseEntity<UsersResponse> getAllAssociate() {
//        List<UserDTO> users = userService.getAllAssociate();
//        UsersResponse response = new UsersResponse();
//        response.setUsers(users);
//        return ResponseEntity.ok(response);
//    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
        description = "get all users",
        security = @SecurityRequirement(name = "Bearer Authentication"),
        responses = {
            @ApiResponse(responseCode = "200", description = "Success fetching all users",
                content = @Content(schema = @Schema(implementation = UsersResponse.class))),
            @ApiResponse(responseCode = "401", description = "Authentication error")
        }
    )
    public ResponseEntity<UsersResponse> getUsers() {
        List<UserDTO> users = userService.getAllUsers();
        UsersResponse response = new UsersResponse();
        response.setUsers(users);
        return ResponseEntity.ok(response);
    }

    @GetMapping("actions/getStudents")
    public ResponseEntity<UsersResponse> getStudent() {
        List<UserDTO> users = this.userService.getUsersByRoleName(AppRole.ROLE_STUDENT);
        UsersResponse usersResponse = new UsersResponse();
        usersResponse.setUsers(users);
        return ResponseEntity.ok(usersResponse);
    }

    @GetMapping("actions/getAssociate")
    public ResponseEntity<UsersResponse> getAssociate() {
        List<UserDTO> users = this.userService.getUsersByRoleName(AppRole.ROLE_ASSOCIATE);
        UsersResponse usersResponse = new UsersResponse();
        usersResponse.setUsers(users);
        return ResponseEntity.ok(usersResponse);
    }

    @GetMapping(path = "/{id}" ,produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
        description = "get dedicated user by id",
        security = @SecurityRequirement(name = "Bearer Authentication"),
        responses = {
            @ApiResponse(responseCode = "200", description = "Success fetching user by id",
                content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "401", description = "Authentication error"),
            @ApiResponse(responseCode = "404", description = "Cannot find any user")
        }
    )
    public ResponseEntity<UserDTO> getUser(@PathVariable(value = "id") Integer id) {
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserCreationDTO userCreationDTO) {
        UserDTO createdUser = this.userService.createUser(userCreationDTO);
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> UpdateUser(@PathVariable(value = "id") Integer id,
        @Valid @RequestBody UserUpdatingDTO userUpdatingDTO) {
        UserDTO user = this.userService.updateUser(id, userUpdatingDTO);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/action/putAvt/{id}")
    public ResponseEntity<String> putAvt(@PathVariable(value = "id") Integer id,
                                          @Valid @RequestBody PutAvatarDTO putAvatarDTO) {
        this.userService.putAvatar(id, putAvatarDTO);
        return ResponseEntity.ok("Successfully put avt for user");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "id") Integer id) {
        this.userService.deleteUser(id);
        return ResponseEntity.ok("Successfully soft delete user");
    }

    @PutMapping("/{id}/actions/change-password")
    public ResponseEntity<String> changePassword(@PathVariable(value = "id") Integer id,
        @RequestBody PasswordChangingDTO newPassword) {
        this.userService.changePassword(id, newPassword.getNewPassword());
        return ResponseEntity.ok("Successfully change user's password");
    }
}
