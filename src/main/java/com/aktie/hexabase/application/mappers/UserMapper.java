package com.aktie.hexabase.application.mappers;

import com.aktie.hexabase.adapter.inbound.dto.UserDTO;
import com.aktie.hexabase.domain.entities.UserBO;

public class UserMapper {
    
    public static UserDTO toDTO(UserBO bo) {
        return new UserDTO(bo.getId(), bo.getName(), bo.getEmail(), bo.getPassword());
    }
    
    public static UserBO toDomain(UserDTO dto) {
        return new UserBO(dto.uuid(), dto.name(), dto.email(), dto.password());
    }

}
