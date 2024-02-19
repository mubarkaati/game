package com.pairing.puzzlegame.service;

import com.pairing.puzzlegame.model.request.FinishGameRequestDto;
import com.pairing.puzzlegame.model.request.GameStartRequestDto;
import com.pairing.puzzlegame.model.response.FinishGameResponseDto;
import com.pairing.puzzlegame.model.response.GameStartResponseDto;
import com.pairing.puzzlegame.model.response.UserGameHistoryResponseDto;

import java.util.List;

public interface GameService {
    GameStartResponseDto startGame(GameStartRequestDto gameStartRequestDto);

    FinishGameResponseDto finishGame(FinishGameRequestDto finishGameRequestDto);

    List<UserGameHistoryResponseDto> getHistory();
}
