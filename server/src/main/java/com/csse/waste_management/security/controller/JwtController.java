package com.csse.waste_management.security.controller;

import com.csse.waste_management.common.ModuleExceptionCodes;
import com.csse.waste_management.dto.CredentialsDTO;
import com.csse.waste_management.security.dto.JwtModelRequest;
import com.csse.waste_management.security.dto.JwtModelResponse;
import com.csse.waste_management.security.service.JwtUserDetailsService;
import com.csse.waste_management.security.util.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtController {
    JwtUserDetailsService jwtUserDetailsService;
    AuthenticationManager authenticationManager;
    TokenManager tokenManager;

    @Autowired
    JwtController(JwtUserDetailsService jwtUserDetailsService, AuthenticationManager authenticationManager, TokenManager tokenManager) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtModelResponse> createToken(@RequestBody JwtModelRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (DisabledException e) {
            return ResponseEntity.badRequest().body(new JwtModelResponse(null, ModuleExceptionCodes.USER_DISABLED));
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body(new JwtModelResponse(null, ModuleExceptionCodes.INVALID_CREDENTIALS));
        }
        final CredentialsDTO credentials = jwtUserDetailsService.loadUserByUsername(request.getUsername());
        final String token = tokenManager.generateJwtToken(credentials);
        return ResponseEntity.ok(new JwtModelResponse(token, null));
    }
}
