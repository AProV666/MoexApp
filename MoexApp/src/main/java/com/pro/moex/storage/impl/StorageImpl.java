package com.pro.moex.storage.impl;

import com.pro.moex.entity.TickerStorage;
import com.pro.moex.storage.Storage;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StorageImpl implements Storage {
    private long id = 1;

    @Override
    public void addTicker(TickerStorage ticker) {
        boolean isNull = ticker == null
                || ticker.getLow() == null
                || ticker.getHigh() == null
                || ticker.getClose() == null
                || ticker.getVolume() == null;
        if (!isNull) {
            boolean isEquals = getTickerList(ticker.getTicker())
                    .stream().noneMatch(x -> x.getDate().equals(ticker.getDate()));
            if(isEquals) {
                storage.put(id++, ticker);
            }
        }
    }

    @Override
    public List<TickerStorage> getTickerList(String ticker) {
        return storage.values()
                .stream()
                .filter(x -> x.getTicker().equals(ticker))
                .collect(Collectors.toList());
    }
}
