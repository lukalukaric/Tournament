package com.example.data;

public class NoPlayersException extends Exception {
    public String playersName;

    public NoPlayersException(String playersName) {
        super();
        this.playersName = playersName;
    }
}
