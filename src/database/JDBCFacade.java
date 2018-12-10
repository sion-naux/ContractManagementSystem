package database;
import java.sql.*;
/**
 * Created by Jiqing on 2016/10/18.
 */
public class JDBCFacade {

    private Connection conn=null;
    private Statement statement=null;

    public void open(String driver,String jdbcUrl,String userName,String userPwd) {
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(jdbcUrl,userName,userPwd);
            statement = conn.createStatement();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
    }

    public int executeUpdate(String sql) {
        try {
            return statement.executeUpdate(sql);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public ResultSet executeQuery(String sql) {
        try {
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void close() {
        try {
            conn.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}