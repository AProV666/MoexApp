package com.pro.moex.parser;

import com.pro.moex.exception.NotFoundException;

import java.util.List;

public class ParserNumberFromColumnList {
    public static int getNumber(List<String> list, String str) {
        int num = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(str)) {
                num = i;
            }
        }
        if (num == -1) {
            throw new NotFoundException(str + " not found!");
        }
        return num;

//                Map<Integer, String> map = new HashMap<>();
//        int num = 0;
//        for (String s : list) {
//            map.put(num++, s);
//        }
//        return map.keySet()
//                .stream()
//                .filter(Objects::nonNull)
//                .filter(key -> str.equals(map.get(key)))
//                .findFirst().orElseThrow(() -> new NotFoundException(String.format("%s not found!", str)));
    }
}
