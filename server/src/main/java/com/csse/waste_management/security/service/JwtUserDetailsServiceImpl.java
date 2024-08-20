package com.csse.waste_management.security.service;

import com.csse.waste_management.common.UserPrivileges;
import com.csse.waste_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService {
    UserService userService;

    @Autowired
    JwtUserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        com.csse.waste_management.dao.User user = userService.getUserByUsername(username);
        if (user == null) {
            return null;
        }
        // FIXME: Allow admin privileges until privileges are fully configured
        return new User(user.getUsername(), user.getPassword(), List.of(new SimpleGrantedAuthority(UserPrivileges.ADMIN)));
    }

    @Override
    public UserDetails registerNewUser(com.csse.waste_management.dao.User user) {
        user = userService.saveUser(user);
        return new User(user.getUsername(), user.getPassword(), List.of(new SimpleGrantedAuthority(UserPrivileges.ADMIN)));
    }
}
