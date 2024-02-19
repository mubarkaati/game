package com.pairing.puzzlegame.model.response;

import lombok.Data;

@Data
public class UserGameHistoryResponseDto {

    private String gameId;

    private String gameWonStatus;

    private String nickName;
}