package com.pro.moex.service.impl;

import com.pro.moex.constant.Constant;
import com.pro.moex.entity.Moex;
import com.pro.moex.entity.Ticker;
import com.pro.moex.exception.NotFoundException;
import com.pro.moex.parser.ParserMoexTicker;
import com.pro.moex.parser.ParserMoex;
import com.pro.moex.service.MoexHistoryService;
import com.pro.moex.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceImpl implements MoexHistoryService {

    private final ParserMoexTicker parserMoexTicker;

    @Autowired
    public ServiceImpl(ParserMoexTicker parserMoexTicker) {
        this.parserMoexTicker = parserMoexTicker;
    }

    @Override
    public List<Ticker> getHistory(String ticker) {

        String moexURL = String.format(Constant.BASE_URL_MOEX, ticker);
        String jsonMoex = new HttpClientUtils().httpGet(moexURL);
        if(jsonMoex == null)  {
            throw new NotFoundException(String.format("JSON with URL: %s - not found!", moexURL));
        }
        Moex moex = new ParserMoex().getMoexEntity(jsonMoex);


        return parserMoexTicker.getTickerList(moex, ticker);
    }
}
