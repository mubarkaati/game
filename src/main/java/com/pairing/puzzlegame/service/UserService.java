package com.pairing.puzzlegame.service;

import com.pairing.puzzlegame.model.request.LoginRequestDto;
import com.pairing.puzzlegame.model.request.UserRegisterRequestDto;
import com.pairing.puzzlegame.model.response.LoginResponseDto;
import com.pairing.puzzlegame.model.response.RegisterUserResponseDto;
import com.pairing.puzzlegame.model.response.UserInfoResponseDto;

import java.util.List;

public interface UserService {
    RegisterUserResponseDto registerUser(UserRegisterRequestDto registerRequestDto);

    LoginResponseDto login(LoginRequestDto loginRequestDto);

    List<UserInfoResponseDto> getAllUsers();
}