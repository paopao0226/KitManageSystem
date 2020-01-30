package com.example.mysql_test;

import android.net.SocketKeepalive;
import android.util.Log;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class RClient {
    private Socket socket;

    public Socket getSocket(){ return socket;}

    public RClient(){
        try {
            Socket soc = new Socket("192.168.1.104",6666);
            this.socket = soc;
        }catch (IOException e){
            Log.d("socket","error to connect");
            e.printStackTrace();
        }
    }

    public String transportmsg(String msg,Socket socket){
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(msg);

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            return dataInputStream.readUTF();
        }catch (IOException e){
            Log.d("socket","error to generate outstream");
            e.printStackTrace();
        }
        return "error";
    }
}
