package com.csse.waste_management.security;

import com.csse.waste_management.common.UserPrivileges;
import com.csse.waste_management.dto.CredentialsDTO;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService {
    // FIXME: Temporary driver method to return a CredentialsDTO object
    public CredentialsDTO loadUserByUsername(String username) {
        CredentialsDTO dummyAdminUser = new CredentialsDTO();
        dummyAdminUser.addAuthority(new SimpleGrantedAuthority(UserPrivileges.ADMIN));
        return dummyAdminUser;
    }
}
