package com.csse.waste_management.security.service;

import com.csse.waste_management.util.ModuleException;
import com.csse.waste_management.util.ModuleExceptionCodes;
import com.csse.waste_management.util.UserPrivileges;
import com.csse.waste_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
            throw new UsernameNotFoundException(ModuleExceptionCodes.USER_NOT_FOUND);
        }
        // FIXME: Allow admin privileges until privileges are fully configured
        return new User(user.getUsername(), user.getPassword(), List.of(new SimpleGrantedAuthority(UserPrivileges.ADMIN)));
    }

    @Override
    public UserDetails registerNewUser(com.csse.waste_management.dao.User user) throws ModuleException {
        if (userService.isUserExists(user.getUsername())) {
            throw new ModuleException(ModuleExceptionCodes.USER_ALREADY_EXISTS);
        }
        user = userService.saveUser(user);
        return new User(user.getUsername(), user.getPassword(), List.of(new SimpleGrantedAuthority(UserPrivileges.ADMIN)));
    }
}
