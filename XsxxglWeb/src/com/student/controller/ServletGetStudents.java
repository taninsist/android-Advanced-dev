package com.student.controller;

import com.student.dao.StudentDao;
import com.student.entity.Student;
import com.student.util.JsonTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/getStudents")
public class ServletGetStudents extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO 查看数据库连接地址和数据库名等是否正确
        log("改连接数据库配置");


        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //创建集合存放学生信息
        List<Student> studentList = new ArrayList<>();
        StudentDao studentDao = new StudentDao();
        studentList = studentDao.findAllStudents();
        //将studentList对象转换成json数据
        String json = JsonTool.javaToJson(studentList);
        out.print(json);
    }
}
