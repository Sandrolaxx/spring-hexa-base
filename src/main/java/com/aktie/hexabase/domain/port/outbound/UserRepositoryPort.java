package com.aktie.hexabase.domain.port.outbound;

import com.aktie.hexabase.domain.entities.UserBO;

public interface UserRepositoryPort {

    void save(UserBO user);

}
