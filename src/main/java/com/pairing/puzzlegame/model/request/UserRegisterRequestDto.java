package com.pairing.puzzlegame.model.request;

import lombok.Data;

@Data
public class UserRegisterRequestDto {

    private String fullName;

    private String username;

    private String password;
}