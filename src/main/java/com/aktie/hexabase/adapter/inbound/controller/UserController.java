package com.aktie.hexabase.adapter.inbound.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aktie.hexabase.adapter.inbound.dto.CreateUserDTO;
import com.aktie.hexabase.adapter.inbound.dto.UserDTO;
import com.aktie.hexabase.application.usecases.RegisterUserUseCase;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private RegisterUserUseCase useCase;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
        var createdUser = useCase.execute(user);

        return ResponseEntity.ok(createdUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable UUID uuid, @RequestBody UserDTO user) {
        User updatedUser = userService.updateUser(uuid, user);
        return ResponseEntity.ok(updatedUser);
    }
}

