package com.aktie.hexabase.adapter.outbound.persistence;

import org.springframework.stereotype.Repository;

import com.aktie.hexabase.adapter.outbound.persistence.mappers.PgUserMapper;
import com.aktie.hexabase.adapter.outbound.persistence.repository.JpaUserRepository;
import com.aktie.hexabase.domain.entities.UserBO;
import com.aktie.hexabase.domain.port.outbound.UserRepositoryPort;

@Repository
public class UserRepositoryAdapter implements UserRepositoryPort {

    private JpaUserRepository repository;

    public UserRepositoryAdapter(JpaUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(UserBO user) {
        var entity = PgUserMapper.toEntity(user);
        
        repository.save(entity);
    }
    
}
