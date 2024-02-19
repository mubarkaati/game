package com.pairing.puzzlegame.controller;

import com.pairing.puzzlegame.model.request.LoginRequestDto;
import com.pairing.puzzlegame.model.request.UserRegisterRequestDto;
import com.pairing.puzzlegame.model.response.LoginResponseDto;
import com.pairing.puzzlegame.model.response.RegisterUserResponseDto;
import com.pairing.puzzlegame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseDto> registerUser(@RequestBody UserRegisterRequestDto registerRequestDto) {
        return new ResponseEntity<>(userService.registerUser(registerRequestDto), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return new ResponseEntity<>(userService.login(loginRequestDto), HttpStatus.OK);
    }
}