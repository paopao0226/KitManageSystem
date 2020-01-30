import java.sql.*;

public class database {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/Datebase";
    static final String username = "root";
    static final String password = "";

    public static Connection getConnection() throws Exception{
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,username,password);
        }catch (Exception e){
            throw e;
        }
        return conn;
    }
    public static void closeAll(Connection conn, PreparedStatement ps, ResultSet rs){
        try {
            if(conn != null)
                conn.close();
            if(ps != null)
                ps.close();
            if (rs != null)
                rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Connection connection = database.getConnection();
            System.out.println(connection);
            String[] tem = ("111+aaa").split("\\+");
            PreparedStatement ps = connection.prepareStatement("select * from Admin where admName = ? and admPwd = ?");
            ps.setString(1,tem[0]);
            ps.setString(2,tem[1]);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
