import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.io.*;
import java.net.*;

import com.sun.tools.classfile.ConstantPool;
import org.json.JSONArray;
import org.json.JSONObject;

//服务器类
public class Server {
    private int port=6666;

    public Server() {}

    public Server(int port) {
        this.port=port;
    }

    public void service() {
        try {
            ServerSocket server=new ServerSocket(port);
            System.out.println("Server started");
            Socket socket=server.accept();
            try {
                System.out.println("client connected");

                DataInputStream in=new DataInputStream(socket.getInputStream());
                DataOutputStream out =new DataOutputStream(socket.getOutputStream());
                Scanner scanner = new Scanner(System.in);
                while(true) {
                    String accept=in.readUTF();
                    /* check database */
                    String a = scanner.next();

                    /* String */
                    /*Connection connection = database.getConnection(); // getconnection throw exception
                    String[] tem = accept.split("\\+");
                    PreparedStatement ps = connection.prepareStatement("select * from Admin where admName = ? and admPwd = ?");
                    ps.setString(1,tem[0]);
                    ps.setString(2,tem[1]);

                    System.out.println(ps.toString());
                    ResultSet rs = ps.executeQuery();
                    // judge is exist
                    String send;
                    if (rs.next()) send = "login success";
                    else send = "input false";
                    out.writeUTF(send);
                    */

                    /* json */
                    JSONArray jsonArray = new JSONArray(accept);
                    // JSONObject js = new JSONObject(accept);
                    JSONObject js = jsonArray.getJSONObject(0);
                    System.out.println(js.toString());
                    Connection connection = database.getConnection();
                    PreparedStatement ps = connection.prepareStatement("select * from Admin where admName = ? and admPwd = ?");
                    ps.setString(1,js.get("username").toString());
                    ps.setString(2,js.get("password").toString());

                    System.out.println(ps.toString());
                    ResultSet rs = ps.executeQuery();
                    JSONArray jsonArrayResponse = new JSONArray();
                    if (rs.next()){
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("username",rs.getString(2));
                        jsonObject.put("password",rs.getString(3));
                        jsonArrayResponse.put(jsonObject);
                    }
                    String jsonStirngResponse = jsonArrayResponse.toString();
                    out.writeUTF("["+jsonStirngResponse+"]");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                socket.close();
                server.close();
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {//主程序方法
        new Server().service();//调用service方法
    }
}
