package com.student.util;

import com.google.gson.Gson;

//Json工具类，将Java对象换成json数据格式
public class JsonTool {
    public static String javaToJson(Object value){
        Gson gson = new Gson();
        String jsonString =gson.toJson(value);
        return jsonString;
    }
}
