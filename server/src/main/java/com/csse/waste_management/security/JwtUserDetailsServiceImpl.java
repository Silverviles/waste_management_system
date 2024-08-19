package com.csse.waste_management.security;

import com.csse.waste_management.dto.CredentialsDTO;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService {
    // FIXME: Temporary driver method to return a CredentialsDTO object
    public CredentialsDTO loadUserByUsername(String username) {
        return new CredentialsDTO(username);
    }
}
