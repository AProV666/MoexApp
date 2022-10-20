package com.pro.moex.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pro.moex.entity.Ticker;
import com.pro.moex.entity.TickerStorage;
import com.pro.moex.mapper.Mapper;
import com.pro.moex.model.ticker.Root;
import com.pro.moex.storage.Storage;
import com.pro.moex.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ParserTicker {

    private final Storage storage;

    @Autowired
    public ParserTicker(Storage storage) {
        this.storage = storage;
    }

    public void parse(String ticker, String tickerURL, String boardId) {

        Root root;
        try {
            String jsonTicker = new HttpClientUtils().httpGet(tickerURL);
            ObjectMapper om = new ObjectMapper();
            root = om.readValue(jsonTicker, Root.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        ArrayList<String> columnsList = root.getHistory().getColumns();
        int numTradeDate = ParserNumbers.getNumber(columnsList, "TRADEDATE");
        int numLow = ParserNumbers.getNumber(columnsList, "LOW");
        int numHigh = ParserNumbers.getNumber(columnsList, "HIGH");
        int numClose = ParserNumbers.getNumber(columnsList, "CLOSE");
        int numVolume = ParserNumbers.getNumber(columnsList, "VOLUME");

        for (int i = 0; i < root.getHistory().getData().size(); i++) {
            String boardEq = String.valueOf(root.getHistory().getData().get(i).get(0));
            if (boardEq.equals(boardId)) {
                if (root.getHistory().getData().get(i).get(numLow) == null ||
                        root.getHistory().getData().get(i).get(numHigh) == null ||
                        root.getHistory().getData().get(i).get(numClose) == null ||
                        root.getHistory().getData().get(i).get(numVolume) == null) {
                    continue;
                }

                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date dateSt = null;
                try {
                    dateSt = format.parse(String.valueOf(root.getHistory().getData().get(i).get(numTradeDate)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

                String date = formatter.format(dateSt);
                Double low = Double.valueOf(String.valueOf(root.getHistory().getData().get(i).get(numLow)));
                Double high = Double.valueOf(String.valueOf(root.getHistory().getData().get(i).get(numHigh)));
                Double close = Double.valueOf(String.valueOf(root.getHistory().getData().get(i).get(numClose)));
                Integer volume = Integer.valueOf(String.valueOf(root.getHistory().getData().get(i).get(numVolume)));

                TickerStorage newTicker = new TickerStorage(ticker, date, low, high, close, volume);
                storage.addTicker(newTicker);
            }
        }
    }

    public List<Ticker> getTickerList(String ticker) {
        List<TickerStorage> tickerStorageList = storage.getTickerList(ticker);
        Mapper mapper = new Mapper(tickerStorageList);
        return mapper.getMapper();
    }
}