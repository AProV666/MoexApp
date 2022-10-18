//package com.pro.moex.service.impl;
//
//import com.pro.moex.constant.Constant;
//import com.pro.moex.entity.Moex;
//import com.pro.moex.entity.Ticker;
//import com.pro.moex.exception.NotFoundException;
//import com.pro.moex.parser.ParserMoex;
//import com.pro.moex.parser.ParserMoexPages;
//import com.pro.moex.parser.ParserTicker;
//import com.pro.moex.service.MoexHistoryService;
//import com.pro.moex.utils.HttpClientUtils;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component("moexHistoryServiceImpl")
//public class MoexHistoryServiceImpl implements MoexHistoryService {
//
//    long start = System.currentTimeMillis();
//
//    @Override
//    public List<Ticker> getHistory(String ticker) {
//
//        String moexURL = String.format(Constant.BASE_URL_MOEX, ticker);
//        String jsonMoex = new HttpClientUtils().httpGet(moexURL);
//        if(jsonMoex == null)  {
//            throw new NotFoundException("JSON not found!");
//        }
//        Moex moex = new ParserMoex().getMoexEntity(jsonMoex);
//
//        String tickerURL = String.format(Constant.BASE_URL_TICKER, moex.getEngine(), moex.getMarket(), ticker, 0);
//
//        ParserMoexPages parserMoexPages = new ParserMoexPages();
//        parserMoexPages.parse(tickerURL);
//        int total = parserMoexPages.getTotal();
//        int pageSize = parserMoexPages.getPageSize();
//
//
//
//        ParserTicker parserTicker = new ParserTicker(moex.getBoardId());
//
//        List<Ticker> tickerList = parserTicker.parse(tickerURL);
//
//        for (int i = pageSize; i <= total; i += pageSize) {
//            String nextTickerURL = String.format(Constant.BASE_URL_TICKER, moex.getEngine(), moex.getMarket(), ticker, i);
//
//            tickerList.addAll(parserTicker.parse(nextTickerURL));
//        }
//
//        System.out.println(System.currentTimeMillis() - start);
//
//        return tickerList;
//    }
//}
