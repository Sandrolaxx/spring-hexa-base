package com.aktie.hexabase.application.usecases;

import java.util.UUID;

import com.aktie.hexabase.adapter.inbound.dto.CreateUserDTO;
import com.aktie.hexabase.adapter.inbound.dto.UserDTO;
import com.aktie.hexabase.domain.entities.UserBO;
import com.aktie.hexabase.domain.port.inbound.RegisterUserUseCasePort;
import com.aktie.hexabase.domain.port.outbound.UserRepositoryPort;

public class RegisterUserUseCase implements RegisterUserUseCasePort {

    private final UserRepositoryPort userRepository;

    public RegisterUserUseCase(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO execute(CreateUserDTO dto) {
        
        var user = new UserBO(UUID.randomUUID(), dto.name(), dto.email(), dto.password());

        userRepository.save(user);

        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword());

    }

}
