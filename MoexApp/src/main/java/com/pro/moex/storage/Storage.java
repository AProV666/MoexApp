package com.pro.moex.storage;

import com.pro.moex.entity.TickerStorage;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface Storage {

    Map<Long, TickerStorage> storage = new ConcurrentHashMap<>();

    void addTicker(TickerStorage ticker);

    List<TickerStorage> getTickerList(String ticker);
}
