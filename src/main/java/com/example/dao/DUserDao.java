package com.example.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import static java.lang.System.getenv;

public class DUserDAO extends UserDAO{
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Map<String, String> env = getenv();
        Connection conn = DriverManager.getConnection(
                "","",""
        );
        return conn;
    }
}
