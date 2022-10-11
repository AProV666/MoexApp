package com.pro.moex.service;

import com.pro.moex.entity.Ticker;

import java.util.List;

public interface MoexHistoryService {
    List<Ticker> getHistory(String ticker);
}
