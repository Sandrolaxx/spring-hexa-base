package com.aktie.hexabase.adapter.outbound.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aktie.hexabase.adapter.outbound.persistence.model.PgUser;

@Repository
public interface JpaUserRepository extends JpaRepository<PgUser, UUID> {
}
