package com.pairing.puzzlegame.entities;

import com.pairing.puzzlegame.constant.WonStatus;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Game extends Audit {

    private String gameStatus;

    private String difficultyLevel;

    private String nickName;

    private Integer score;

    private String wonStatus = WonStatus.PENDING.toString();

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private User user;

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getWonStatus() {
        return wonStatus;
    }

    public void setWonStatus(String wonStatus) {
        this.wonStatus = wonStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}