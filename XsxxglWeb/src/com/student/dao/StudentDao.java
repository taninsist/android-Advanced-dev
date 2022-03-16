package com.student.dao;

import com.student.entity.Student;
import com.student.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//创建一个学生操作类，实现对数据表的增删改查操作
public class StudentDao {
    //创建一个添加学生的方法，实现对数据表的添加功能
    public int addStudent(Student student){
        //标识位 用来判断用户是否添加成功
        int flag = 0;
        //1、创建一个连接对象
        Connection connection = null;
        //2、创建语句对象，用来执行sql语句
        PreparedStatement ps = null;
        //3、构造要执行的sql语句
        String sqlString = "insert into students(no,name,sex,birthday,tel,address) values (?,?,?,?,?,?);";
        //4、连接对象初始化
        connection = DBUtil.getConnection();
        //5、语句对象初始化
        try {
            ps = connection.prepareStatement(sqlString);
            //6、给占位符赋值
            ps.setString(1,student.getNo());
            ps.setString(2,student.getName());
            ps.setString(3,student.getSex());
            ps.setString(4,student.getBirthday());
            ps.setString(5,student.getTel());
            ps.setString(6,student.getAddress());
            //7、执行
            flag = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return flag;
    }
    //实现获取所有学生信息的方法，返回值的类型是集合
    public List<Student> findAllStudents(){
        List<Student> studentList = new ArrayList<>();
        //1、创建一个连接对象
        Connection connection = null;
        //2、创建语句对象，用来执行sql语句
        PreparedStatement ps = null;
        //3、创建结果集对象
        ResultSet rs = null;
        //4、构造sql语句
        String sqlString = "select * from students;";
        //5、连接对象初始化
        connection = DBUtil.getConnection();
        try {
            ps = connection.prepareStatement(sqlString);
            rs = ps.executeQuery();
            //获取结果集对象中的学生信息
            while (rs.next()){
                Student student = new Student();
                student.setId(rs.getInt(1));
                student.setNo(rs.getString(2));
                student.setName(rs.getString(3));
//                student.setSex(rs.getString(4));
//                student.setBirthday(rs.getString(5));
//                student.setTel(rs.getString(6));
//                student.setAddress(rs.getString(7));
                //将student对象存入集合中
                studentList.add(student);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return studentList;
    }
}
