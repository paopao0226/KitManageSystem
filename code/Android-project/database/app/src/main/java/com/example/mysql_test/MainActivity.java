package com.example.mysql_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

//import org.json.JSONArray;
//import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //配置模型设置
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
                .build());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText usernamefield = (EditText)findViewById(R.id.editText3);
        final EditText passwordfield = (EditText)findViewById(R.id.editText4);
        final Button loginbutton = (Button)findViewById(R.id.button3);
        final EditText receivefield = (EditText)findViewById(R.id.editText5);

        final RClient rClient = new RClient();
        Log.d("main","success make socket");
        final Socket mainsocket = rClient.getSocket();
        Log.d("main","success get mainsocket");

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                receivefield.setText(rClient.transportmsg(usernamefield.getText().toString()+"+"+passwordfield.getText().toString(),mainsocket));
            }
        });
    }
}