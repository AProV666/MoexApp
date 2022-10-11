package com.pro.moex.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pro.moex.model.ticker.Root;
import com.pro.moex.utils.HttpClientUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ParserPageSizeAndTotal {
    private int total;
    private int pageSize;

    public void parse(String tickerURL) {
        try {
            String jsonTicker = new HttpClientUtils().httpGet(tickerURL);
            ObjectMapper om = new ObjectMapper();
            Root root = om.readValue(jsonTicker, Root.class);
            total = root.getHistoryCursor().getData().get(0).get(1);
            pageSize = root.getHistoryCursor().getData().get(0).get(2);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
