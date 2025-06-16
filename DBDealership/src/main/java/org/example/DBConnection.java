package org.example;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
    private static final BasicDataSource dataSource = new org.example.BasicDataSource();

    static {
        dataSource.setUrl("jdbc:mysql://localhost:3306/CarDealership");
        dataSource.setUsername("dealership_user");
        dataSource.setPassword("secure_password");
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxTotal(25);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}