package com.pro.moex.mapper;

import com.pro.moex.entity.Ticker;
import com.pro.moex.entity.TickerStorage;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    private final List<TickerStorage> tickerStorageList;

    public Mapper(List<TickerStorage> tickerStorageList) {
        this.tickerStorageList = tickerStorageList;
    }

    public List<Ticker> getMapper() {
        List<Ticker> tickerList = new ArrayList<>();
        for (TickerStorage t : tickerStorageList) {
            Ticker ticker = new Ticker(t.getDate(), t.getLow(), t.getHigh(), t.getClose(), t.getVolume());
            tickerList.add(ticker);
        }
        return tickerList;
    }
}
