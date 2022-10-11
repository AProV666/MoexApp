package com.pro.moex;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pro.moex.exception.NotFoundException;
import com.pro.moex.model.moex.Root;
import com.pro.moex.utils.HttpClientUtils;

import java.util.*;

public class Test {
    public static void main(String[] args) throws JsonProcessingException {
        String BASE_URL = "https://iss.moex.com/iss/securities/sber.json";

        HttpClientUtils httpClientUtils = new HttpClientUtils();
        String jsonMoex = httpClientUtils.httpGet(BASE_URL);

        ObjectMapper om = new ObjectMapper();

        Root root = om.readValue(jsonMoex, Root.class);

        List<String> columnsList = root.getBoards().getColumns();
        List<ArrayList<Object>> dataList = root.getBoards().getData();

        int numBoard = getNumber(columnsList, "boardid");
        int numMarket = getNumber(columnsList, "market");
        int numEngine = getNumber(columnsList, "engine");
        int numPrimary = getNumber(columnsList, "is_primary");
        System.out.println(numBoard + " " + numMarket + " " + numEngine + " " + numPrimary);

        String boardId = getText(dataList, numPrimary, numBoard);
        String market = getText(dataList, numPrimary, numMarket);
        String engine = getText(dataList, numPrimary, numEngine);

        System.out.println(boardId + " " + market + " " + engine);
    }

    private static Integer getNumber(List<String> list, String str) {
        Map<Integer, String> mapMap = new HashMap<>();
        int num = 0;
        for (String s : list) {
            mapMap.put(num++, s);
        }
        return mapMap.keySet()
                .stream()
                .filter(key -> str.equals(mapMap.get(key)))
                .findFirst().orElseThrow(() -> new NotFoundException(String.format("%s not found!", str)));
    }

    private static String getText(List<ArrayList<Object>> list, int primary, int num) {
        String str = "";
        for (ArrayList<Object> dataList : list) {
            if ((int) dataList.get(primary) == 1) {
                str = dataList.get(num).toString();
            }
        }
        return str;
    }


}
