package database;
import java.io.*;
import java.nio.Buffer;
import java.sql.*;
import java.util.Dictionary;

import com.mysql.jdbc.Driver;
/**
 * Created by Jiqing on 2016/10/18.
 */
public class JDBCFacade {

    private Connection conn=null;
    private Statement statement=null;
    private PreparedStatement prepareStatement = null;
    private static String pwdFileName = "password.txt";

    public void open(String driver,String jdbcUrl,String userName,String userPwd) {
        try {

            Class.forName(driver).newInstance();
            String str = getPwd(pwdFileName);
            conn = DriverManager.getConnection(jdbcUrl,userName,str);
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

    public PreparedStatement getPrepareStatement(String sql) throws SQLException {
        return this.conn.prepareStatement(sql);
    }


    private String getPwd(String filename){
        /*
            个人认为还是不要把密码以明文储存在程序中
            虽然文件内可能也不安全。。。
            不过应该可以解决其他人用此类时需要修改成自己的数据库密码问题
         */
        File pwdFile = new File(filename);
        System.out.println(pwdFile.getAbsolutePath());

        BufferedReader reader;
        String pwd = "";
        try{
            reader = new BufferedReader(new FileReader(pwdFile));
            pwd = reader.readLine();
            System.out.println(pwd);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return pwd;
        }
    }
}