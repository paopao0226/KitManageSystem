import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.io.*;
import java.net.*;

//服务器类
public class Server {//ChatServer类
    private int port=6666;//默认服务器端口

    public Server() {

    }

    //创建指定端口的服务器
    public Server(int port) {
        this.port=port;
    }

    //提供服务
    public void service() {
        try {
            ServerSocket server=new ServerSocket(port);//创建ServerSocket类
            System.out.println("Server started");
            Socket socket=server.accept();//等待客户连接
            try {
                System.out.println("client connected");

                DataInputStream in=new DataInputStream(socket.getInputStream());
                DataOutputStream out =new DataOutputStream(socket.getOutputStream());
                Scanner scanner = new Scanner(System.in);
                while(true) {
                    String accept=in.readUTF();
                    /* check database */
                    String a = scanner.next();
                    Connection connection = database.getConnection(); // getconnection throw exception
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
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {//建立连接失败的话不会执行socket.close();
                socket.close();//关闭连接
                server.close();//关闭
            }
        }catch(IOException e) {//捕获异常
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {//主程序方法
        new Server().service();//调用service方法
    }
}
