package com.pairing.puzzlegame.entities;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_table")
public class User extends Audit {

    private String fullName;

    private String username;

    private String password;

    private String role;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Parent parent;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Game> gameUserList;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Game> getGameUserList() {
        return gameUserList;
    }

    public void setGameUserList(List<Game> gameUserList) {
        this.gameUserList = gameUserList;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}