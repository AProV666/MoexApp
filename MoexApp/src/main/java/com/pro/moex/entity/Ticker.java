package com.pro.moex.entity;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Ticker {
    private String date;
    private Double low;
    private Double high;
    private Double close;
    private Integer volume;
}
