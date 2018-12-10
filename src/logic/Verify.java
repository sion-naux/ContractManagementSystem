package logic;

import database.JDBCFacade;
import database.dbConfig;
import entity.User;

import java.sql.ResultSet;

public class Verify<main> {
    private JDBCFacade jdbc;

    public int login(String username, String password) {
        User user = new User(username, password);
        /*
        flag:
        1 -> 登陆成功
        -1 -> 用户名不存在
        -2 -> 用户名存在，密码错误
        */
        int flag = 1;
        //在数据库中查询用户
        try {
            jdbc = new JDBCFacade();
            //打开数据库连接
            jdbc.open("com.mysql.jdbc.Driver", dbConfig.jdbcUrl, dbConfig.userName, dbConfig.userPwd);
            String sql = "select * from user where name='" + user.getName() + "'";
            System.out.println("生成的sql语句是：" + sql);
            ResultSet rs = jdbc.executeQuery(sql);
            int row_count = 0;

            while (rs.next()) {
                row_count++;
                String true_password = rs.getString("password");
                if (!user.getPassword().equals(true_password)) {
                    flag = -2;//密码错误
                }
            }

            if (row_count == 0) {
                flag = -1;//用户名不存在
            }

            jdbc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return flag;
    }

    public int register(String username, String password) {
        User user = new User(username, password);
        int flag = 0;
        //数据库中插入用户
        try {
            jdbc = new JDBCFacade();
            //打开数据库连接
            jdbc.open("com.mysql.jdbc.Driver", dbConfig.jdbcUrl, dbConfig.userName, dbConfig.userPwd);
            String sql = "insert into user(name,password) value ('" + user.getName() + "','" + user.getPassword() + "')";
            System.out.println("生成的sql语句是" + sql);
            flag = jdbc.executeUpdate(sql);
            jdbc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static void main(String[] args) {
        //注册测试用例
        /*
        Verify verify = new Verify();
        int register_result = verify.register("WuJiahang","123456");
        if(register_result == -1){
            System.out.println("注册失败！");
        }else{
            System.out.println("注册成功！");
        }
        */

        //登录测试用例
        /*
        Verify verify = new Verify();
        int register_result = verify.login("WuJiahang", "123456");
        if (register_result == -1) {
            System.out.println("用户不存在！");
        } else if (register_result == -2) {
            System.out.println("密码错误！");
        } else {
            System.out.println("登录成功！");
        }
        */
    }


}
