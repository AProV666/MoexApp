package com.pro.moex.parser;

import com.pro.moex.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class ParserInfoFromDataList {
    public static String getInfo(List<ArrayList<Object>> list, int primary, int num) {
        String str = null;
        for (ArrayList<Object> dataList : list) {
            if ((int) dataList.get(primary) == 1) {
                str = dataList.get(num).toString();
            }
        }
        if (str == null) {
            throw new NotFoundException(String.format("Info with number: %d - not found!", num));
        }
        return str;
    }

}
