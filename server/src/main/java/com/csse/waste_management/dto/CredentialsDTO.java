package com.csse.waste_management.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredentialsDTO {
    private String username;

    public CredentialsDTO() {
    }

    public CredentialsDTO(String username) {
        this.username = username;
    }
}
