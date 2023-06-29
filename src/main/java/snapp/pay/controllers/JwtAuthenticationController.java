package snapp.pay.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import snapp.pay.config.security.JwtTokenUtil;
import snapp.pay.dto.JwtRequest;
import snapp.pay.dto.JwtResponse;
import snapp.pay.dto.UserRequestDto;
import snapp.pay.dto.UserResponseDto;
import snapp.pay.services.JwtUserDetailsService;
import snapp.pay.services.UserService;

import javax.validation.Valid;

/**
 * JwtAuthenticationController handle register and login
 *
 * @Author Kiarash Shamaei 2023-06-25
 */
@RestController
@RequestMapping(value = "/auth")
@Tag(name = "Auth Api")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest request) throws BadCredentialsException {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword())
        );
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(request.getUsername());
        String accessToken = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok().body(JwtResponse.builder()
                .email(request.getUsername())
                .jwttoken(accessToken)
                .build());

    }

    @PostMapping(value = "/register")
    @Operation(summary = "add new user ", description = "ok status")
    public ResponseEntity<UserResponseDto> createNewUser(@RequestBody @Valid UserRequestDto json) {

        UserResponseDto usr = userService.addNewUser(json);
        return new ResponseEntity<UserResponseDto>(usr, HttpStatus.OK);
    }

}
