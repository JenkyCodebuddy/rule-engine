package com.example;

public class ScoreModel {

    private final long id;
    private final String score;

    public ScoreModel(long id, String score) {
        this.id = id;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public String getScore() {
        return score;
    }
}