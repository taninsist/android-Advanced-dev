package com.example.xsxxglclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xsxxglclient.entity.Stu;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private ListView listView = null;
    private List<Stu> stuList = new ArrayList<>();  //列表的数据集
    private StuAdapter stuAdapter = new StuAdapter();


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    String result = msg.obj.toString();
                    //调用jspnTool工具类，将json数据转换为Java对象

                    Gson gson = new Gson();

                    stuList = gson.fromJson(result, new  TypeToken<List<Stu>>(){}.getType());  //json数据 转 List(集合)

                    listView.setAdapter(stuAdapter);


//                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

//                    Stu stu1 = new Stu(1,"1","name1");
//                    Stu stu2 = new Stu(2,"2","name2");
//                    stuList.add(stu1);
//                    stuList.add(stu2);
//


                            /*
                    stuList = JsonTool.getpersonList(result);
                    Toast.makeText(MainActivity.this, personList.get(1).getName(), Toast.LENGTH_SHORT).show();
                    //绑定适配器
                    personListView.setAdapter(personAdapter);
                    */
                    break;
                default:
                    break;
            }


        }
    };


    //TODO 查看数据库连接地址和数据库名等是否正确

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listView = findViewById(R.id.listV);



        Toast.makeText(getApplicationContext(), "123123", Toast.LENGTH_SHORT).show();
        NeThread neThread = new NeThread();
        neThread.start();

    }

    //列表的适配器
    class StuAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return stuList.size();
        }

        @Override
        public Object getItem(int i) {
            return stuList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View myView = View.inflate(getApplicationContext(), R.layout.student_list, null);
            TextView idV = myView.findViewById(R.id.idInp);
            TextView noV = myView.findViewById(R.id.noInp);
            TextView nameV = myView.findViewById(R.id.nameInp);

            idV.setText(String.valueOf(stuList.get(i).getId()));
            noV.setText(String.valueOf(stuList.get(i).getNo()));
            nameV.setText(String.valueOf(stuList.get(i).getName()));

            return myView;
        }
    }


    //子线程
    class NeThread extends Thread {
        @Override
        public void run() {
            super.run();
//            String urlString = "http://10.0.2.2:8081/getPersonJson";
            String urlString = "http://10.0.2.2:8081/getStudents";
            URL url = null;
            try {
                url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
//                connection.setConnectTimeout(5000);
//                connection.setReadTimeout(5000);
                int code = connection.getResponseCode();
                if (code == 200) {
                    InputStream is = connection.getInputStream();
                    int len = 0;
                    byte buffer[] = new byte[1024];
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    while ((len = is.read(buffer)) != -1) {
                        baos.write(buffer, 0, len);
                    }
                    is.close();
                    baos.close();
                    String result = new String(baos.toByteArray(), "UTf-8");
                    Message message = handler.obtainMessage();
                    message.what = 1;
                    message.obj = result;
                    handler.sendMessage(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}