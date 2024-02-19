package com.pairing.puzzlegame.model.request;

import lombok.Data;

@Data
public class FinishGameRequestDto {

    private String gameId;

    private Integer score;

    private Boolean isWon;
}