package com.pro.moex.model.ticker;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Root {

    @JsonProperty("history")
    public History getHistory() {
        return this.history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    History history;

    @JsonProperty("history.cursor")
    public HistoryCursor getHistoryCursor() {
        return this.historyCursor;
    }

    public void setHistoryCursor(HistoryCursor historyCursor) {
        this.historyCursor = historyCursor;
    }

    HistoryCursor historyCursor;
}
