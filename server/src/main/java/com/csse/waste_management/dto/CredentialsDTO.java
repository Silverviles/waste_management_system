package com.csse.waste_management.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CredentialsDTO {
    List<GrantedAuthority> authorities;
    private String username;

    public CredentialsDTO() {
    }

    public CredentialsDTO(String username) {
        this.username = username;
    }

    public void addAuthority(GrantedAuthority authority) {
        if (authorities == null) {
            authorities = new ArrayList<>();
        }
        authorities.add(authority);
    }
}
