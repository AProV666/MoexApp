package com.pro.moex.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pro.moex.entity.Moex;
import com.pro.moex.exception.NotFoundException;
import com.pro.moex.model.moex.Root;

import java.util.*;

public class ParserMoex {

    public Moex getMoexEntity(String jsonMoex) {

        List<String> columnsList;
        List<ArrayList<Object>> dataList;

        ObjectMapper om = new ObjectMapper();

        try {
            Root root = om.readValue(jsonMoex, Root.class);
            columnsList = root.getBoards().getColumns();
            dataList = root.getBoards().getData();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        Integer numBoard = NumberFromColumnsList.getNumber(columnsList, "boardid");
        Integer numMarket = NumberFromColumnsList.getNumber(columnsList, "market");
        Integer numEngine = NumberFromColumnsList.getNumber(columnsList, "engine");
        Integer numPrimary = NumberFromColumnsList.getNumber(columnsList, "is_primary");

//        Integer numBoard = getNumber(columnsList, "boardid");
//        Integer numMarket = getNumber(columnsList, "market");
//        Integer numEngine = getNumber(columnsList, "engine");
//        Integer numPrimary = getNumber(columnsList, "is_primary");

        String boardId = getText(dataList, numPrimary, numBoard);
        String market = getText(dataList, numPrimary, numMarket);
        String engine = getText(dataList, numPrimary, numEngine);

        return new Moex(boardId, market, engine);
    }

//    private Integer getNumber(List<String> list, String str) {
//        Map<Integer, String> map = new HashMap<>();
//        int num = 0;
//        for (String s : list) {
//            map.put(num++, s);
//        }
//        return map.keySet()
//                .stream()
//                .filter(Objects::nonNull)
//                .filter(key -> str.equals(map.get(key)))
//                .findFirst().orElseThrow(() -> new NotFoundException(String.format("%s not found!", str)));
//    }

    private String getText(List<ArrayList<Object>> list, int primary, int num) {
        String str = "";
        for (ArrayList<Object> dataList : list) {
            if ((int) dataList.get(primary) == 1) {
                str = dataList.get(num).toString();
            }
        }
        return str;
    }
}