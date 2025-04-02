package com.aktie.hexabase.adapter.inbound.dto;

import java.util.UUID;

public record UserDTO(UUID uuid, String name, String email, String password) {
}
