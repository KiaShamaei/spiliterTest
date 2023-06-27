package snapp.pay.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import snapp.pay.config.security.JwtTokenUtil;
import snapp.pay.dto.JwtRequest;
import snapp.pay.dto.JwtResponse;
import snapp.pay.dto.UserRequestDto;
import snapp.pay.entities.User;
import snapp.pay.services.JwtUserDetailsService;
import snapp.pay.services.UserService;


@RestController
@RequestMapping(value = "/auth")
@CrossOrigin
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
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest request) throws Exception {

//        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
//
//        final UserDetails userDetails = userDetailsService
//                .loadUserByUsername(authenticationRequest.getEmail());
//
//        final String token = jwtTokenUtil.generateToken(userDetails);
//
//        return ResponseEntity.ok(new JwtResponse(token));
        try {
             authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword())
            );
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(request.getEmail());
            String accessToken = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok().body(accessToken);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping(value = "/register")
    @Operation(summary = "add new user " ,description = "ok status")
    public ResponseEntity<User> createNewUser(@RequestBody UserRequestDto json) {

        User usr = userService.addNewUser(json);
        return new ResponseEntity<>(usr, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
