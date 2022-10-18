package com.pro.moex.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pro.moex.entity.Moex;
import com.pro.moex.model.moex.Root;

import java.util.*;

public class ParserMoex {

    public Moex getMoexEntity(String json) {

        List<String> columnsList;
        List<ArrayList<Object>> dataList;

        ObjectMapper om = new ObjectMapper();

        try {
            Root root = om.readValue(json, Root.class);
            columnsList = root.getBoards().getColumns();
            dataList = root.getBoards().getData();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        int numBoard = ParserNumberFromColumnList.getNumber(columnsList, "boardid");
        int numMarket = ParserNumberFromColumnList.getNumber(columnsList, "market");
        int numEngine = ParserNumberFromColumnList.getNumber(columnsList, "engine");
        int numPrimary = ParserNumberFromColumnList.getNumber(columnsList, "is_primary");

        String boardId = ParserInfoFromDataList.getInfo(dataList, numPrimary, numBoard);
        String market = ParserInfoFromDataList.getInfo(dataList, numPrimary, numMarket);
        String engine = ParserInfoFromDataList.getInfo(dataList, numPrimary, numEngine);

        return new Moex(boardId, market, engine);
    }
}