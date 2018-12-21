package logic;

import database.JDBCFacade;
import database.dbConfig;
import entity.Right;

import java.sql.Array;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RightManage {


    //获取权限列表
    public static List<Right> getRightList() {
        List<Right> right_list = new ArrayList<Right>();
        try {
            JDBCFacade jdbc = new JDBCFacade();
            //打开数据库连接
            jdbc.open(dbConfig.driverName, dbConfig.newjdbcUrl, dbConfig.userName, dbConfig.userPwd);
            String sql = "select * from userright";
            System.out.println("生成的sql语句是：" + sql);
            ResultSet rs = jdbc.executeQuery(sql);
            while (rs.next()) {
                right_list.add(new Right(rs.getString("userName"), rs.getString("roleName"), rs.getString("description")));
            }
            jdbc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return right_list;
    }

    //授权
    public static int giveRight(String username, String role) {
        int flag = 0;
        try {
            JDBCFacade jdbc = new JDBCFacade();
            //打开数据库连接
            jdbc.open(dbConfig.driverName, dbConfig.newjdbcUrl, dbConfig.userName, dbConfig.userPwd);
            String sql = "insert into userright values ('" + username + "','" + role + "',(select description from role where name='" + role + "'))";
            System.out.println("生成的sql语句是：" + sql);
            flag = jdbc.executeUpdate(sql);
            jdbc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    //修改权限
    public static int updateRight(String username, String role) {
        int flag = 0;
        try {
            JDBCFacade jdbc = new JDBCFacade();
            //打开数据库连接
            jdbc.open(dbConfig.driverName, dbConfig.newjdbcUrl, dbConfig.userName, dbConfig.userPwd);
            String sql = "update userright set roleName='" + role + "',description=(select description from role where name='" + role + "') where userName='" + username + "'";
            System.out.println("生成的sql语句是：" + sql);
            flag = jdbc.executeUpdate(sql);
            jdbc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static List<String> get_right_list_of_user(String username) {
        List<String> right_list = new ArrayList<String>();
        try {
            JDBCFacade jdbc = new JDBCFacade();
            //打开数据库连接
            jdbc.open(dbConfig.driverName, dbConfig.newjdbcUrl, dbConfig.userName, dbConfig.userPwd);
            String sql = "select functions from userright,role where role.name=userright.roleName and userName='" + username+"'";
            System.out.println("生成的sql语句是：" + sql);
            ResultSet rs = jdbc.executeQuery(sql);
            while (rs.next()){
                right_list = Arrays.asList(rs.getString("functions").split("，"));
            }



            jdbc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return right_list;

    }
}
