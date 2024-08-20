package com.csse.waste_management.security.service;

import com.csse.waste_management.common.UserPrivileges;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService {
    public UserDetails loadUserByUsername(String username) {
        // FIXME: Temporary driver method to return a UserDetails object
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(UserPrivileges.ADMIN));
        return new User("dummyUser", encoder.encode("dummyPassword"), authorities);
    }
}
