package com.pro.moex.model.moex;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Root {

    @JsonProperty("boards")
    public Boards getBoards() {
        return this.boards;
    }

    public void setBoards(Boards boards) {
        this.boards = boards;
    }

    Boards boards;
}
