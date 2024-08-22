package com.csse.waste_management.security.controller;

import com.csse.waste_management.util.ModuleException;
import com.csse.waste_management.util.ModuleExceptionCodes;
import com.csse.waste_management.security.dto.JwtLoginRequest;
import com.csse.waste_management.security.dto.JwtRegisterRequest;
import com.csse.waste_management.security.dto.JwtResponse;
import com.csse.waste_management.security.service.JwtUserDetailsService;
import com.csse.waste_management.security.util.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtController {
    final JwtUserDetailsService jwtUserDetailsService;
    final AuthenticationManager authenticationManager;
    final TokenManager tokenManager;
    final PasswordEncoder passwordEncoder;

    @Autowired
    JwtController(JwtUserDetailsService jwtUserDetailsService, AuthenticationManager authenticationManager, TokenManager tokenManager, PasswordEncoder passwordEncoder) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> createToken(@RequestBody JwtLoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (DisabledException e) {
            return ResponseEntity.badRequest().body(new JwtResponse(null, ModuleExceptionCodes.USER_DISABLED));
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body(new JwtResponse(null, ModuleExceptionCodes.INVALID_CREDENTIALS));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new JwtResponse(null, ModuleExceptionCodes.UNKNOWN_ERROR));
        }
        final UserDetails credentials = jwtUserDetailsService.loadUserByUsername(request.getUsername());
        final String token = tokenManager.generateJwtToken(credentials);
        return ResponseEntity.ok(new JwtResponse(token, null));
    }

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> registerUser(@RequestBody JwtRegisterRequest request) {
        try {
            request.setPassword(passwordEncoder.encode(request.getPassword()));
            final UserDetails credentials = jwtUserDetailsService.registerNewUser(request.getUserFromRequest());
            final String token = tokenManager.generateJwtToken(credentials);
            return ResponseEntity.ok(new JwtResponse(token, null));
        } catch (ModuleException e) {
            return ResponseEntity.badRequest().body(new JwtResponse(null, e.getMessage()));
        }
    }
}
