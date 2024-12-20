package com.example.meus_gastos.controller;

import com.example.meus_gastos.DTOs.User.CreateUserDTO;
import com.example.meus_gastos.DTOs.User.UserUpdateDTO;
import com.example.meus_gastos.domain.User.UserEntity;
import com.example.meus_gastos.mapper.User.UserMap;
import com.example.meus_gastos.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "Controller for operations related with user .")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMap userMap;


    @PostMapping
    @Operation(summary = "Register a new user", description = "This operation is for registered a new user in our database")
    @ApiResponse(responseCode = "201", description = "The user was registered with successful in system")
    @ApiResponse(responseCode = "400", description = "Validation error")
    public ResponseEntity<CreateUserDTO> createUser(@RequestBody CreateUserDTO userDTO) throws Exception {
        UserEntity userEntity = userMap.toUserEntity(userDTO);
        UserEntity userReturn = userService.createUser(userEntity);
        CreateUserDTO dtoUser = userMap.toCreateUserDTO(userReturn);
        return new ResponseEntity<>(dtoUser, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all user", description = "This operation return all users registered in system")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "204", description = "Doesn't exists users registered in system")
    public ResponseEntity<List<UserEntity>> getAllUsers(){
        List<UserEntity> users = userService.findAll();
        if(users.isEmpty()){
           return ResponseEntity.status(204).build();
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id", description = "This operation return user by id in system")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id){
        UserEntity user = userService.findById(id);
        if(user == null){
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a user", description = "This operation update a exists user")
    @ApiResponse(responseCode = "200", description = "The user data was updated with successfull")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<UserUpdateDTO> updateUser(@PathVariable Long id, @RequestBody UserUpdateDTO user) throws Exception {
        UserUpdateDTO response = userService.updateUser(id, user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user", description = "This operaion delete a exists user")
    @ApiResponse(responseCode = "204", description = "User deleted with successfull")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.status(204).build();
    }

}
