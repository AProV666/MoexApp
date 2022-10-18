package com.pro.moex.entity;

import jakarta.persistence.*;

import java.util.Date;

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

    public TickerDB(int id, String ticker, Date date, Double low, Double high, Double close, Integer volume) {
        this.id = id;
        this.ticker = ticker;
        this.date = date;
        this.low = low;
        this.high = high;
        this.close = close;
        this.volume = volume;
    }

    public TickerDB() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }
}
