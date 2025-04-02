package com.aktie.hexabase.adapter.outbound.persistence;

import com.aktie.hexabase.domain.entities.UserBO;
import com.aktie.hexabase.domain.port.outbound.UserRepositoryPort;

public class UserRepositoryAdapter implements UserRepositoryPort {

    @Override
    public void save(UserBO user) {
        // Criar model e persistir com JPA
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
    
}
