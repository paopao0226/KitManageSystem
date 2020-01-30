package com.example.mysql_test;

import android.telephony.PhoneNumberUtils;
import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBService {

//    Connection conn=null; //打开数据库对象
//    PreparedStatement ps=null;//操作整合sql语句的对象
//    ResultSet rs=null;//查询结果的集合

    //DBService 对象
    public static DBService dbService=null;

    /**
     * 构造方法 私有化
     * */

    public DBService(){

    }

    /**
     * 获取MySQL数据库单例类对象
     * */

    public static DBService getDbService(){
        if(dbService==null){
            dbService=new DBService();
        }
        return dbService;
    }


    /**
     * 获取要发送短信的患者信息    查
     * */
    public void test(){
        String sql = "select * from Admin;";
        Connection conn = DBOpenHelper.getConn();
//        Log.d("connection",conn.toString());
        PreparedStatement ps=null;
        ResultSet rs = null;
        try {
            if (conn != null && (!conn.isClosed())) {
                ps = (PreparedStatement) conn.prepareStatement(sql);
                if (ps != null) {
                    rs = ps.executeQuery();
                    while(rs.next()){
                        Log.d("data",rs.getString("admId"));
                        Log.d("data",rs.getString("admName"));
                        Log.d("data",rs.getString("admPwd"));
                    }
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        DBOpenHelper.closeAll(conn,ps,rs);
    }




}
