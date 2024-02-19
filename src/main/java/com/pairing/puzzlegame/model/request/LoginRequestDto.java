package com.pairing.puzzlegame.model.request;

import lombok.Data;

@Data
public class LoginRequestDto {

    private String username;

    private String password;
}