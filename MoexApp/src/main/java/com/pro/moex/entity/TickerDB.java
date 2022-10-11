package com.pro.moex.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Entity
public class TickerDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String ticker;

    @Column(name = "date_sale")
    private Date date;

    @Column(name = "low_price")
    private Double low;

    @Column(name = "high_price")
    private Double high;

    @Column(name = "close_price")
    private Double close;

    private Integer volume;
}
