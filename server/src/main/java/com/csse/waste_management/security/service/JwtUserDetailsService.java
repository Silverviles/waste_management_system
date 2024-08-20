package com.csse.waste_management.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface JwtUserDetailsService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);
}
