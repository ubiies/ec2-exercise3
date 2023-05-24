package com.example.db;
import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;

public class ConnectChecker {
    public void check() throws SQLException, ClassNotFoundException {
        Connection con = getConnection();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("show databases");

        while (rs.next()) {
            String line = rs.getString(1);
            System.out.println(line);
        }
    }

    public void add() throws ClassNotFoundException, SQLException {
        Connection con = getConnection();

        PreparedStatement pstmt = con.prepareStatement(
                "insert into users(id, real_name, password) values (?, ?, ?)"
        );
        pstmt.setString(1, "12");
        pstmt.setString(2, "juhee");
        pstmt.setString(3, "1234");
        pstmt.executeUpdate();

    }

    private static Connection getConnection() throws SQLException, ClassNotFoundException {
        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        System.out.println("dbPassword = " + dbPassword);
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://" + dbHost + "/likelion",
                dbUser, dbPassword);
        return con;
    }

    public void select() throws SQLException, ClassNotFoundException {
        Connection con = getConnection();

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from user");
        rs = st.getResultSet();

        while (rs.next()) {
            for (int i = 1; i <= 3; i++) {
                String str = rs.getString(i);
                System.out.printf(str + " ");
            }
            System.out.println("\n");
        }
    }


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConnectChecker cc = new ConnectChecker();
        // cc.check();
        // cc.add();
        cc.select();
    }
}
