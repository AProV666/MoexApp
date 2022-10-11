package com.pro.moex.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pro.moex.entity.Ticker;
import com.pro.moex.model.ticker.Root;
import com.pro.moex.utils.HttpClientUtils;
import lombok.RequiredArgsConstructor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@RequiredArgsConstructor
public class ParserTicker {
    private final String boardId;
    private ArrayList<Ticker> tickerList;

    public ArrayList<Ticker> parse(String tickerURL) {

        try {
            String jsonTicker = new HttpClientUtils().httpGet(tickerURL);
            ObjectMapper om = new ObjectMapper();
            Root root = om.readValue(jsonTicker, Root.class);
            tickerList = getInfoFromTicker(root);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return tickerList;
    }

    public ArrayList<Ticker> getInfoFromTicker(Root root) {

        ArrayList<String> columnsList = root.getHistory().getColumns();

        Integer numTradeDate = NumberFromColumnsList.getNumber(columnsList, "TRADEDATE");
        Integer numLow = NumberFromColumnsList.getNumber(columnsList, "LOW");
        Integer numHigh = NumberFromColumnsList.getNumber(columnsList, "HIGH");
        Integer numClose = NumberFromColumnsList.getNumber(columnsList, "CLOSE");
        Integer numVolume = NumberFromColumnsList.getNumber(columnsList, "VOLUME");

        ArrayList<Ticker> list = new ArrayList<>();

        for (int i = 0; i < root.getHistory().getData().size(); i++) {
            String boardEq = String.valueOf(root.getHistory().getData().get(i).get(0));
            if (boardEq.equals(boardId)) {
                if (root.getHistory().getData().get(i).get(numLow) == null ||
                        root.getHistory().getData().get(i).get(numHigh) == null ||
                        root.getHistory().getData().get(i).get(numClose) == null) {
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

                Ticker newTicker = new Ticker(date, low, high, close, volume);
                list.add(newTicker);
            }
        }






        return list;
    }
}
