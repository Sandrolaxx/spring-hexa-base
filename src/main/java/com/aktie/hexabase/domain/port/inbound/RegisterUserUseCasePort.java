package com.aktie.hexabase.domain.port.inbound;

import com.aktie.hexabase.adapter.inbound.dto.CreateUserDTO;
import com.aktie.hexabase.adapter.inbound.dto.UserDTO;

public interface RegisterUserUseCasePort {
    
    UserDTO execute(CreateUserDTO dto);

}
