package com.csse.waste_management.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtRegisterRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -8091879091924046844L;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
