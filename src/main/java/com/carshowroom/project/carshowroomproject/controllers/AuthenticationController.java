package com.carshowroom.project.carshowroomproject.controllers;

import com.carshowroom.project.carshowroomproject.dto.AuthenticationRequestDto;
import com.carshowroom.project.carshowroomproject.entities.Car;
import com.carshowroom.project.carshowroomproject.entities.User;
import com.carshowroom.project.carshowroomproject.exceptions.ExceptionsControllerAdvice;
import com.carshowroom.project.carshowroomproject.security.jwt.JwtTokenProvider;
import com.carshowroom.project.carshowroomproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/v1")
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @Operation(summary = "User authorization endpoint.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "success",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "403", description = "User is not found",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ExceptionsControllerAdvice.class)))
            })
    @PostMapping("/authorization")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto, HttpServletResponse response) {
        try {
            String username = requestDto.getLogin();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());
            Cookie cookie = new Cookie("access_token", token);
//            cookie.setPath("/");
//            cookie.set("SameSite=None");
//            cookie.setDomain();

            response.addCookie(cookie);
//            response.addHeader("Set-Cookie", "access_token=" + token + "; Path=/v1; Secure; SameSite=None");
            response.addHeader("access_token", token);
//            response.addHeader("Set-Cookie", "SameSite=None; Secure");

            return ResponseEntity.ok().build();
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
