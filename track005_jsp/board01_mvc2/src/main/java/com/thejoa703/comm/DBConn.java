package com.thejoa703.comm;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mbasic";
            String user = "root";
            String pass = "1234";
            conn = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
