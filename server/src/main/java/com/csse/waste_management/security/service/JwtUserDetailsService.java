package com.csse.waste_management.security.service;

import com.csse.waste_management.dto.CredentialsDTO;

public interface JwtUserDetailsService {
    CredentialsDTO loadUserByUsername(String username);
}
