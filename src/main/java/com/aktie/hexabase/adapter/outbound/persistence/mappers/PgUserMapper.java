package com.aktie.hexabase.adapter.outbound.persistence.mappers;

import com.aktie.hexabase.adapter.outbound.persistence.model.PgUser;
import com.aktie.hexabase.domain.entities.UserBO;

public class PgUserMapper {

    public static PgUser toEntity(UserBO bo) {
        var entity = new PgUser();

        entity.setId(bo.getId());
        entity.setEmail(bo.getEmail());
        entity.setName(bo.getName());
        entity.setPassword(bo.getPassword());

        return entity;
    }

    public static UserBO toDomain(PgUser bo) {
        return new UserBO(
                bo.getId(),
                bo.getName(),
                bo.getEmail(),
                bo.getPassword());
    }

}
