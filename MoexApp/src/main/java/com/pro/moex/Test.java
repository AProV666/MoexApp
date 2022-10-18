package com.pro.moex;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pro.moex.entity.Ticker;
import com.pro.moex.entity.TickerStorage;
import com.pro.moex.mapper.Mapper;
import com.pro.moex.storage.Storage;
import com.pro.moex.storage.impl.StorageImpl;

import java.util.List;

public class Test {
    static Storage storage = new StorageImpl();
    public static void main(String[] args) throws JsonProcessingException {

        for(int i = 1; i <= 12; i++) {
//            TickerStorage newTicker = new TickerStorage("sber", "12." + i + ".2022", 10.0 * i, 100.0 * i, 50.0 * i, 1000 * i);
            TickerStorage newTicker = new TickerStorage("sber", "12.10.2022", 10.0, 100.0, 50.0, 1000);
            TickerStorage newTicker1 = new TickerStorage("sber", "12.10.2023", 10.0, 100.0, 50.0, 1000);
            TickerStorage newTicker2 = new TickerStorage("sber", "12.10.2022", 10.0, 100.0, 50.0, 1000);
            storage.addTicker(newTicker);
            storage.addTicker(newTicker1);
            storage.addTicker(newTicker2);
        }
        List<Ticker> tickers = getTickerList("sber");

        tickers.forEach(System.out::println);

    }


        public static List<Ticker> getTickerList(String ticker) {
        List<TickerStorage> tickerStorageList = storage.getTickerList(ticker);
        Mapper mapper = new Mapper(tickerStorageList);
        return mapper.getMapper();
    }
}
