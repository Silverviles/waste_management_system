package com.csse.waste_management.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CredentialsDTO {
    List<GrantedAuthority> authorities;
    private String username;
    private String password;

    public CredentialsDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void addAuthority(GrantedAuthority authority) {
        if (authorities == null) {
            authorities = new ArrayList<>();
        }
        authorities.add(authority);
    }
}
