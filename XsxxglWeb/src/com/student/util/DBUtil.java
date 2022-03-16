package com.student.util;

import java.sql.Connection;
import java.sql.DriverManager;

//创建一个数据库工具类
public class DBUtil {
    //连接数据库的静态方法
    public static Connection getConnection(){
        //1、定义连接对象
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/android","root","123");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
