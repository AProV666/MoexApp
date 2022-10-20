package com.pro.moex.parser;

import com.pro.moex.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UbiqDateInTicker {
    private final Storage storage;

    @Autowired
    public UbiqDateInTicker(Storage storage) {
        this.storage = storage;
    }

    public static boolean getUniq(String ticker, Date date) {






        return false;
    }
}
