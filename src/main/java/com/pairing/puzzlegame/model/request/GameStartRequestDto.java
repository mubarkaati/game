package com.pairing.puzzlegame.model.request;

import lombok.Data;

@Data
public class GameStartRequestDto {

    private String difficultyLevel;

    private String nickName;
}