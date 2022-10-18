package com.pro.moex.entity;

import org.springframework.stereotype.Component;

public class Moex {
    private String boardId;
    private String market;
    private String engine;

    public Moex(String boardId, String market, String engine) {
        this.boardId = boardId;
        this.market = market;
        this.engine = engine;
    }

    public String getBoardId() {

        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }
}
