package com.aktie.hexabase.adapter.inbound.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<UserDTO> createUser(@RequestBody CreateUserDTO user) {
        var createdUser = useCase.execute(user);

        return ResponseEntity.ok(createdUser);
    }

}

