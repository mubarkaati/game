package com.pairing.puzzlegame.controller;

import com.pairing.puzzlegame.model.request.FinishGameRequestDto;
import com.pairing.puzzlegame.model.request.GameStartRequestDto;
import com.pairing.puzzlegame.model.response.FinishGameResponseDto;
import com.pairing.puzzlegame.model.response.GameStartResponseDto;
import com.pairing.puzzlegame.model.response.UserGameHistoryResponseDto;
import com.pairing.puzzlegame.model.response.UserInfoResponseDto;
import com.pairing.puzzlegame.service.GameService;
import com.pairing.puzzlegame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private GameService gameService;

    @Autowired
    private UserService userService;

    @PostMapping("/game/start")
    public ResponseEntity<GameStartResponseDto> startGame(@RequestBody GameStartRequestDto gameStartRequestDto) {
        return new ResponseEntity<>(gameService.startGame(gameStartRequestDto), HttpStatus.OK);
    }

    @PostMapping("/game/finish")
    public ResponseEntity<FinishGameResponseDto> finishGame(@RequestBody FinishGameRequestDto finishGameRequestDto) {
        return new ResponseEntity<>(gameService.finishGame(finishGameRequestDto), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserInfoResponseDto>> getUserList() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/history")
    public ResponseEntity<List<UserGameHistoryResponseDto>> getGameHistory() {
        return new ResponseEntity<>(gameService.getHistory(), HttpStatus.OK);
    }
}