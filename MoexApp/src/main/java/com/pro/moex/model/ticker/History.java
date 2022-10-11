package com.pro.moex.model.ticker;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class History {

    @JsonProperty("columns")
    public ArrayList<String> getColumns() {
        return this.columns;
    }

    public void setColumns(ArrayList<String> columns) {
        this.columns = columns;
    }

    ArrayList<String> columns;

    @JsonProperty("data")
    public ArrayList<ArrayList<Object>> getData() {
        return this.data;
    }

    public void setData(ArrayList<ArrayList<Object>> data) {
        this.data = data;
    }

    ArrayList<ArrayList<Object>> data;
}
