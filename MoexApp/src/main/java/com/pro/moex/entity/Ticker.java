package com.pro.moex.entity;

public class Ticker {
    private String date;
    private Double low;
    private Double high;
    private Double close;
    private Integer volume;

    public Ticker(String date, Double low, Double high, Double close, Integer volume) {
        this.date = date;
        this.low = low;
        this.high = high;
        this.close = close;
        this.volume = volume;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    @Override
    public String toString() {
        return "Ticker{" +
                "date='" + date + '\'' +
                ", low=" + low +
                ", high=" + high +
                ", close=" + close +
                ", volume=" + volume +
                '}';
    }
}
