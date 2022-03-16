package com.student.entity;

public class Student {
    private int id;
    private String no;
    private String name;
    private String sex;
    private String birthday;
    private String tel;
    private String address;

    public Student() {
    }

    public Student(int id, String no, String name, String sex, String birthday, String tel, String address) {
        this.id = id;
        this.no = no;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.tel = tel;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
