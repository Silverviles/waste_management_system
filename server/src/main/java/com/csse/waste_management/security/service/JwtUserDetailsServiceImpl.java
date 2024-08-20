package com.csse.waste_management.security.service;

import com.csse.waste_management.common.UserPrivileges;
import com.csse.waste_management.dto.CredentialsDTO;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService {
    public CredentialsDTO loadUserByUsername(String username) {
        // FIXME: Temporary driver method to return a CredentialsDTO object
        CredentialsDTO dummyAdminUser = new CredentialsDTO("dummyUser", "dummyPassword");
        dummyAdminUser.addAuthority(new SimpleGrantedAuthority(UserPrivileges.ADMIN));
        return dummyAdminUser;
    }
}
