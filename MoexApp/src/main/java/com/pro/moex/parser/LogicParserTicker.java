package com.pro.moex.parser;

import com.pro.moex.constant.Constant;
import com.pro.moex.entity.Moex;
import com.pro.moex.entity.Ticker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class LogicParserTicker {

    private final ParserTicker parserTicker;
    private final ParserPages parserPages;

    @Autowired
    public LogicParserTicker(ParserTicker parserTicker, ParserPages parserPages) {
        this.parserTicker = parserTicker;
        this.parserPages = parserPages;
    }

    public List<Ticker> getTickerList(Moex moex, String ticker) {

        List<Ticker> tickerList = parserTicker.getTickerList(ticker);
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String date = formatter.format(new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000));
        boolean hasDate = tickerList.stream().anyMatch(x -> x.getDate().equals(date));
        if(hasDate){
            return tickerList;
        }

        String tickerURL = String.format(Constant.BASE_URL_TICKER, moex.getEngine(), moex.getMarket(), ticker, 0);
        parserPages.parse(tickerURL);
        int total = parserPages.getTotal();
        int pageSize = parserPages.getPageSize();

        for (int i = 0; i <= total; i += pageSize) {
            String nextTickerURL = String.format(Constant.BASE_URL_TICKER, moex.getEngine(), moex.getMarket(), ticker, i);
            parserTicker.parse(ticker, nextTickerURL, moex.getBoardId());
        }
        return parserTicker.getTickerList(ticker);
    }
}
