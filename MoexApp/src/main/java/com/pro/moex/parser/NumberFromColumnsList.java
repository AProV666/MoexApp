package com.pro.moex.parser;

import com.pro.moex.exception.NotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class NumberFromColumnsList {
    public static Integer getNumber(List<String> list, String str) {
        Map<Integer, String> map = new HashMap<>();
        int num = 0;
        for (String s : list) {
            map.put(num++, s);
        }
        return map.keySet()
                .stream()
                .filter(Objects::nonNull)
                .filter(key -> str.equals(map.get(key)))
                .findFirst().orElseThrow(() -> new NotFoundException(String.format("%s not found!", str)));
    }
}
