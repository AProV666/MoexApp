package com.pro.moex.storage;

import com.pro.moex.entity.Ticker;
import com.pro.moex.entity.TickerDB;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface Storage {

    Map<Integer, Ticker> storage = new ConcurrentHashMap<>();

    TickerDB create(Ticker ticker);

    List<Ticker> getTickerList(String ticker);
}
