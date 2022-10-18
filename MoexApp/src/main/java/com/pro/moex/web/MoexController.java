package com.pro.moex.web;

import com.pro.moex.entity.Ticker;
import com.pro.moex.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/moex")
public class MoexController {

    private final ServiceImpl moexService;

    @Autowired
    public MoexController(ServiceImpl moexService) {
        this.moexService = moexService;
    }

    @RequestMapping(value = "/{ticker}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Ticker> index(@PathVariable("ticker") String ticker) {
        ticker = ticker.toLowerCase();
        //словать ексепшин нот фаунд
        return moexService.getHistory(ticker);
    }
}
