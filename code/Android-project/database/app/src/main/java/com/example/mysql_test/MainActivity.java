package com.example.mysql_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // virtual device config
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
                /* String */
                // receivefield.setText(rClient.transportmsg(usernamefield.getText().toString()+"+"+passwordfield.getText().toString(),mainsocket));

                /* json */
                // send
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("username",usernamefield.getText().toString());
                    jsonObject.put("password",passwordfield.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // String jsonStringSend = jsonObject.toString();
                // multi jsonobject
                Log.d("json",jsonObject.toString());
                JSONArray jsonArray = new JSONArray();
                jsonArray.put(jsonObject);
                String jsonStringSend = jsonObject.toString();

                // receive
                String jsonStringReceive = rClient.transportmsg("["+jsonStringSend+"]",mainsocket);
                try {
                    JSONArray jsonArrayReceive = new JSONArray(jsonStringReceive);
                    if (jsonArrayReceive.isNull(0)){
                        receivefield.setText("input false");
                    }else receivefield.setText("success login");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}