package com.example.dao;

import com.example.domain.User;

import java.sql.*;
import java.util.Map;
import static java.lang.System.getenv;

public abstract class UserDAO {

    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
//    {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Map<String, String> env = getenv();
//        Connection conn = DriverManager.getConnection(
//                env.get("DB_HOST"), env.get("DB_USER"),env.get("DB_PASSWORD")
//        );
//        return conn;
//    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();

        PreparedStatement pstmt = conn.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getPassword());
        pstmt.executeUpdate(); // 쿼리를 실행 시키고 상태값 반환
        //사용 후에는 반드시 종료해야함
        pstmt.close();
        conn.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement("select name, password from users where id = ?");
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        rs.next(); // ctrl + enter

        //사용 후에는 반드시 종료해야함
        User user = new User();
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        pstmt.close();
        conn.close();

        return new User();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDAO dao = new NUserDao();
//        User user = new User();
//        user.setId("2");
//        user.setName("test");
//        user.setPassword("1234");
//        dao.add(user);

        User selectUser = dao.get("2");
        System.out.println(selectUser.getName());
        System.out.println(selectUser.getPassword());
    }
}