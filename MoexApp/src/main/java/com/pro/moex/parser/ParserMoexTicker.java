package com.pro.moex.parser;

import com.pro.moex.constant.Constant;
import com.pro.moex.entity.Moex;
import com.pro.moex.entity.Ticker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParserMoexTicker {

    private final ParserMoexTickerStorage parserMoexTickerStorage;
    private final ParserMoexPages parserMoexPages;

    @Autowired
    public ParserMoexTicker(ParserMoexTickerStorage parserMoexTickerStorage, ParserMoexPages parserMoexPages) {
        this.parserMoexTickerStorage = parserMoexTickerStorage;
        this.parserMoexPages = parserMoexPages;
    }

    public List<Ticker> getTickerList(Moex moex, String ticker) {

        String tickerURL = String.format(Constant.BASE_URL_TICKER, moex.getEngine(), moex.getMarket(), ticker, 0);
        parserMoexPages.parse(tickerURL);
        int total = parserMoexPages.getTotal();
        int pageSize = parserMoexPages.getPageSize();

        for (int i = 0; i <= total; i += pageSize) {
            String nextTickerURL = String.format(Constant.BASE_URL_TICKER, moex.getEngine(), moex.getMarket(), ticker, i);
            parserMoexTickerStorage.parse(ticker, nextTickerURL, moex.getBoardId());
        }
        return parserMoexTickerStorage.getTickerList(ticker);
    }
}
