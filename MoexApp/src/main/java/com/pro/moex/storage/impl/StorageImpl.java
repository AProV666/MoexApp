package com.pro.moex.storage.impl;

import com.pro.moex.entity.Ticker;
import com.pro.moex.entity.TickerDB;
import com.pro.moex.storage.Storage;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class StorageImpl implements Storage {

    private static final String URL = "jdbc:postgresql://localhost:5432/MoexDB";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "testtest";

    private static Connection connection;
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public TickerDB create(Ticker ticker) {
        return null;
    }

    @Override
    public List<Ticker> getTickerList(String ticker) {
        List<Ticker> tickerList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM ticker";
            ResultSet resultSet = statement.executeQuery(SQL);
            while(resultSet.next()) {
                Ticker newTicker = new Ticker();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return null;
    }
}
