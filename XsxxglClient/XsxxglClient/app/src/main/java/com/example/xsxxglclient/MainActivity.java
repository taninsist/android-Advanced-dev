package com.example.xsxxglclient;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private EditText edtno;
    private EditText edtname;
    private EditText edtsex;
    private EditText edtbirth;
    private EditText edttel;
    private EditText edtaddress;
    private Button btnadd;
    private Button btnquit;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    String result = msg.obj.toString();
                    Toast.makeText(MainActivity.this,result,Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtno = (EditText) findViewById(R.id.edtno);
        edtname = (EditText) findViewById(R.id.edtname);
        edtsex = (EditText) findViewById(R.id.edtsex);
        edtbirth = (EditText) findViewById(R.id.edtbirth);
        edttel = (EditText) findViewById(R.id.edttel);
        edtaddress = (EditText) findViewById(R.id.edtaddress);
        btnadd = (Button) findViewById(R.id.btnadd);
        btnquit = (Button) findViewById(R.id.btnquit);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetThread netThread = new NetThread();
                netThread.start();
            }
        });

    }
    class NetThread extends Thread{
        @Override
        public void run() {
            super.run();
            //获取用户输入的数据
            String no = edtno.getText().toString();
            String name = edtname.getText().toString();
            String sex = edtsex.getText().toString();
            String birthday = edtbirth.getText().toString();
            String tel = edttel.getText().toString();
            String address = edtaddress.getText().toString();
            String urlString = "http://10.0.2.2:8081/XsxxglWeb/addStudent?no="+no+ "&name="+name+ "&sex="+sex+ "&birthday=" +birthday+ "&tel="+tel+"&address="+address;
            URL url = null;
            try {
                url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int code = connection.getResponseCode();
            if (code == 200){
                InputStream is = connection.getInputStream();
                ByteArrayOutputStream baos= new ByteArrayOutputStream();
                int len = 0;
                byte buffer[] = new byte[1024];
                while ((len = is.read(buffer)) != -1){
                    baos.write(buffer,0,len);
                }
                baos.close();
                is.close();
                String result = new String(baos.toByteArray());
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
