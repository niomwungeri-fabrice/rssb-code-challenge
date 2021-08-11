package com.rssb.fileManager.controller;

import com.rssb.fileManager.enums.UserRole;
import com.rssb.fileManager.exception.HttpResponseHandler;
import com.rssb.fileManager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.rssb.fileManager.pojo.AuthenticationRequest;
import com.rssb.fileManager.pojo.AuthenticationResponse;
import com.rssb.fileManager.security.MyUserDetails;
import com.rssb.fileManager.service.JPAUserDetailsService;
import com.rssb.fileManager.utils.JwtUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/v1")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private JPAUserDetailsService userDetailsService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {

            HttpHeaders responseHeaders = new HttpHeaders();

            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
            //if authentication was succesful else throw an exception
            final MyUserDetails userDetails = (MyUserDetails) userDetailsService
                    .loadUserByUsername(authenticationRequest.getUsername());

            final String jwt = jwtTokenUtil.generateToken(userDetails);
            AuthenticationResponse response = new AuthenticationResponse(jwt);

            response.setId(userDetails.getId());
            response.setUsername(userDetails.getUsername());
            List<String> roles = new ArrayList<String>();
            userDetails.getAuthorities().forEach((a) -> roles.add(a.getAuthority()));
            response.setRoles(roles);

            return new ResponseEntity<>(response, responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpResponseHandler.responseHandler("error", e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User userAuth) throws Exception {
        try {
            userAuth.setRoles(UserRole.ROLE_ADMIN.toString());
            userDetailsService.createUser(userAuth);
            return new ResponseEntity<>(HttpResponseHandler.responseHandler("success", "User Created Successfully"),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpResponseHandler.responseHandler("error", e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
