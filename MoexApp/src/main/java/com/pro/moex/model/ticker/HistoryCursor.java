package com.pro.moex.model.ticker;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoryCursor {

    @JsonProperty("data")
    public ArrayList<ArrayList<Integer>> getData() {
        return this.data;
    }

    public void setData(ArrayList<ArrayList<Integer>> data) {
        this.data = data;
    }

    ArrayList<ArrayList<Integer>> data;
}
