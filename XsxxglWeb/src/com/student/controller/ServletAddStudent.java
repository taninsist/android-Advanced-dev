package com.student.controller;

import com.student.dao.StudentDao;
import com.student.entity.Student;

import java.io.IOException;
import java.io.PrintWriter;

public class ServletAddStudent extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //实现对学生表的添加功能
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //1、获取添加数据的参数
        Student student = new Student();
        student.setNo(request.getParameter("no"));
        student.setName(request.getParameter("name"));
        student.setSex(request.getParameter("sex"));
        student.setBirthday(request.getParameter("birthday"));
        student.setTel(request.getParameter("tel"));
        student.setAddress(request.getParameter("address"));
        //2、创建studentdao对象
        StudentDao studentDao = new StudentDao();
        //3、调用添加方法
        int flag = studentDao.addStudent(student);
        //4、判断是否添加成功
        if (flag>0){
            out.print("添加成功");
        }else {
            out.print("添加失败");
        }
    }
}
