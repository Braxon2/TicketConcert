package com.dusanbran.ticketConcert.auth;

import com.dusanbran.ticketConcert.config.JwtService;
import com.dusanbran.ticketConcert.domain.Role;
import com.dusanbran.ticketConcert.domain.User;
import com.dusanbran.ticketConcert.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterRequest request) {

        var user = new User(request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                Role.USER);

        userRepository.save(user);
        String jwt = jwtService.generateToken(user);

        return new AuthenticationResponse(jwt);

    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
        );
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        var jwt = jwtService.generateToken(user);

        return new AuthenticationResponse(jwt);

    }
}
