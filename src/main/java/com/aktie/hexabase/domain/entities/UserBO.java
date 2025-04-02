package com.aktie.hexabase.domain.entities;

import java.util.UUID;

public class UserBO {
    
    private final UUID id;
    
    private final String name;

    private final String email;

    private final String password;

    public UserBO(UUID id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
