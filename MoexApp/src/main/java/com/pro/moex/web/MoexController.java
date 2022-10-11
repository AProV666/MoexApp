package com.pro.moex.web;

import com.pro.moex.entity.Ticker;
import com.pro.moex.service.MoexHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/moex")
@Slf4j
public class MoexController {

    private final MoexHistoryService moexHistoryService;

    public MoexController(@Qualifier("moexHistoryServiceImpl") MoexHistoryService moexHistoryService) {
        this.moexHistoryService = moexHistoryService;
    }

    @RequestMapping(value = "/{ticker}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Ticker> index(@PathVariable("ticker") String ticker) {
        ticker = ticker.toLowerCase();
        return moexHistoryService.getHistory(ticker);
    }
}
