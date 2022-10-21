package com.pro.moex;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pro.moex.entity.Ticker;
import com.pro.moex.entity.TickerStorage;
import com.pro.moex.mapper.Mapper;
import com.pro.moex.storage.Storage;
import com.pro.moex.storage.impl.StorageImpl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) throws JsonProcessingException {
        LocalDate localDate = LocalDate.now().minusDays(1);//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedString = localDate.format(formatter);
        System.out.println(formattedString);




        LocalDate date = LocalDate.of(2022, 10, 22);
        System.out.println(getDayNumberNew(date));
    }


    public static int getDayNumberNew(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day.getValue();
    }
}
