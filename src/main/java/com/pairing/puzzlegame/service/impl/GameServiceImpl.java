package com.pairing.puzzlegame.service.impl;

import com.pairing.puzzlegame.constant.GameStatus;
import com.pairing.puzzlegame.constant.WonStatus;
import com.pairing.puzzlegame.entities.Game;
import com.pairing.puzzlegame.entities.User;
import com.pairing.puzzlegame.exception.InvalidGameIdException;
import com.pairing.puzzlegame.model.request.FinishGameRequestDto;
import com.pairing.puzzlegame.model.request.GameStartRequestDto;
import com.pairing.puzzlegame.model.response.FinishGameResponseDto;
import com.pairing.puzzlegame.model.response.GameStartResponseDto;
import com.pairing.puzzlegame.model.response.UserGameHistoryResponseDto;
import com.pairing.puzzlegame.repository.GameRepository;
import com.pairing.puzzlegame.repository.UserRepository;
import com.pairing.puzzlegame.service.GameService;
import com.pairing.puzzlegame.utils.CurrentUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private CurrentUserUtils currentUserUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    @Override
    public GameStartResponseDto startGame(GameStartRequestDto gameStartRequestDto) {
        GameStartResponseDto gameStartResponseDto = new GameStartResponseDto();
        Game game = new Game();
        User player = currentUserUtils.getCurrentUser();
        game.setDifficultyLevel(gameStartRequestDto.getDifficultyLevel());
        game.setGameStatus(GameStatus.PROGRESS.toString());
        game.setNickName(gameStartRequestDto.getNickName());
        game.setUser(player);
        game.setWonStatus(WonStatus.PENDING.toString());
        game = gameRepository.save(game);
        gameStartResponseDto.setGameId(game.getId());
        return gameStartResponseDto;
    }

    @Override
    public FinishGameResponseDto finishGame(FinishGameRequestDto finishGameRequestDto) {
        FinishGameResponseDto finishGameResponseDto = new FinishGameResponseDto();
        try {
            Game game = gameRepository.findById(finishGameRequestDto.getGameId()).orElse(null);
            if (game == null) {
                throw new InvalidGameIdException();
            }
            game.setGameStatus(GameStatus.COMPLETED.toString());
            game.setScore(finishGameRequestDto.getScore());
            if (finishGameRequestDto.getIsWon()) {
                game.setWonStatus(WonStatus.WON.toString());
            } else {
                game.setWonStatus(WonStatus.LOOSE.toString());
            }
            finishGameResponseDto.setGameStatus("GAME_COMPLETED_SUCCESSFULLY!!");
        } catch (Exception e) {
            finishGameResponseDto.setGameStatus("COULD_NOT_COMPLETE_GAME");
        }
        return finishGameResponseDto;
    }

    @Override
    public List<UserGameHistoryResponseDto> getHistory() {
        User user = currentUserUtils.getCurrentUser();
        return user.getGameUserList()
                .stream()
                .map(this::mapToUserGameHistory)
                .collect(Collectors.toList());
    }

    private UserGameHistoryResponseDto mapToUserGameHistory(Game game) {
        UserGameHistoryResponseDto userGameHistoryResponseDto = new UserGameHistoryResponseDto();
        userGameHistoryResponseDto.setGameId(game.getId());
        userGameHistoryResponseDto.setNickName(game.getNickName());
        userGameHistoryResponseDto.setGameWonStatus(game.getWonStatus());
        return userGameHistoryResponseDto;
    }
}