package com.example.dao;

import com.example.domain.User;
import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;

public class UserDao {

    ConnectionMaker connectionMaker;
    public UserDao() {
        this.connectionMaker = new DConnectionMaker();
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection conn = connectionMaker.makeConnection();
        PreparedStatement pstmt = conn.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getPassword());
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection conn = connectionMaker.makeConnection();

        PreparedStatement pstmt = conn.prepareStatement("select id, name, password from users where id = ?");
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        rs.next(); // ctrl + enter

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        pstmt.close();
        conn.close();

        return user;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();
        User user = new User();
        user.setId("8");
        user.setName("kyeongrok8");
        user.setPassword("1234");
        userDao.add(user);
        User selectedUser = userDao.get("8");
        System.out.println(selectedUser.getId());
        System.out.println(selectedUser.getName());
        System.out.println(selectedUser.getPassword());

        User selectUser = userDao.get("2");
        System.out.printf("%s %s %s",selectUser.getId(),selectUser.getName(),selectUser.getPassword());
    }
}
