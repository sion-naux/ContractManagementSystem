package logic;

import database.JDBCFacade;
import database.dbConfig;
import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Verify<main> {
    private JDBCFacade jdbc;

    //登录
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
            jdbc.open(dbConfig.driverName, dbConfig.newjdbcUrl, dbConfig.userName, dbConfig.userPwd);

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
            flag = -5;
        }


        return flag;
    }

    //注册
    //-2 -> 两次密码不一致
    //-3 -> 用户名已注册
    public int register(String username, String password, String password_confirm) {
        if (!password.equals(password_confirm)) {
            return -2;
        }
        User user = new User(username, password);
        int flag = 0;
        //数据库中插入用户
        try {
            jdbc = new JDBCFacade();
            //打开数据库连接
            jdbc.open(dbConfig.driverName, dbConfig.newjdbcUrl, dbConfig.userName, dbConfig.userPwd);
            String sql = "select * from user where name='" + user.getName() + "'";
            System.out.println("生成的sql语句是:" + sql);
            ResultSet rs = jdbc.executeQuery(sql);
            int row_count = 0;

            while (rs.next()) {
                row_count++;
            }
            if (row_count != 0) {
                jdbc.close();
                return -3;//用户名已注册
            }
            sql = "insert into user(name,password) value ('" + user.getName() + "','" + user.getPassword() + "')";
            System.out.println("生成的sql语句是:" + sql);
            flag = jdbc.executeUpdate(sql);
            jdbc.close();
            RightManage.giveRight(user.getName(),"普通用户");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    //新增用户
    public int insert_user(String username, String password, String password_confirm) {
        return register(username, password, password_confirm);
    }

    //删除用户
    // -1 -> 删除失败
    public int delete_user(String username) {
        int flag = 0;
        //数据库中插入用户
        try {
            jdbc = new JDBCFacade();
            //打开数据库连接
            jdbc.open(dbConfig.driverName, dbConfig.newjdbcUrl, dbConfig.userName, dbConfig.userPwd);
            String sql = "delete from user where name='" + username + "'";
            System.out.println("生成的sql语句是:" + sql);
            flag = jdbc.executeUpdate(sql);
            jdbc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    //修改用户密码
    //-2 -> 两次密码不一致
    //-3 -> 旧密码错误
    public int update_user(String username, String old_password, String new_password, String new_password_confirm) {
        if (!new_password.equals(new_password_confirm)) {
            return -2;//两次密码不一致
        }
        User user = new User(username, old_password);
        int flag = 0;
        //在数据库中查询用户
        try {
            jdbc = new JDBCFacade();
            //打开数据库连接
            jdbc.open(dbConfig.driverName, dbConfig.newjdbcUrl, dbConfig.userName, dbConfig.userPwd);
            String sql = "select * from user where name='" + user.getName() + "'";
            System.out.println("生成的sql语句是：" + sql);
            ResultSet rs = jdbc.executeQuery(sql);
            while (rs.next()) {
                String true_password = rs.getString("password");
                if (!user.getPassword().equals(true_password)) {
                    jdbc.close();
                    return -3;//旧密码错误
                }
            }
            sql = "update user set password='" + new_password + "' where name='" + user.getName() + "'";
            System.out.println("生成的sql语句是:" + sql);
            flag = jdbc.executeUpdate(sql);
            jdbc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;

    }

    //查询用户
    public List<String> show_user() {
        List<String> user_list = new ArrayList<String>();
        try {
            jdbc = new JDBCFacade();
            //打开数据库连接
            jdbc.open(dbConfig.driverName, dbConfig.newjdbcUrl, dbConfig.userName, dbConfig.userPwd);
            String sql = "select * from user";
            System.out.println("生成的sql语句是：" + sql);
            ResultSet rs = jdbc.executeQuery(sql);
            while (rs.next()) {
                user_list.add(rs.getString("name"));
            }
            jdbc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user_list;
    }


    public static void main(String[] args) throws SQLException {
        //注册测试用例
        /*
        Verify verify = new Verify();
        int register_result = verify.register("kkk","123456","123456");
        if(register_result == -1){
            System.out.println("注册失败！");
        }else if(register_result == -2){
            System.out.println("两次密码不一致！");
        }else if(register_result == -3){
            System.out.println("该用户名已注册！");
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

        //新增用户测试用例
        /*
        Verify verify = new Verify();
        int register_result = verify.insert_user("wjh","123456","123456");
        if(register_result == -1){
            System.out.println("新增用户失败！");
        }else if(register_result == -2){
            System.out.println("两次密码不一致！");
        }else if(register_result == -3){
            System.out.println("该用户名已注册！");
        }else{
            System.out.println("新增用户成功！");
        }
        */

        //删除用户测试用例
        /*
        Verify verify = new Verify();
        int register_result = verify.delete_user("wjh");
        if(register_result == -1){
            System.out.println("删除用户失败！");
        }else{
            System.out.println("删除用户成功！");
        }
        */

        //修改用户密码测试用例
        /*
        Verify verify = new Verify();
        int register_result = verify.update_user("kkk","123456","123","123");
        if(register_result == -1){
            System.out.println("修改用户密码失败！");
        }else if(register_result == -2){
            System.out.println("两次密码不一致！");
        }else if(register_result == -3){
            System.out.println("旧密码错误");
        }else{
            System.out.println("修改用户密码成功！");
        }
        */

        //查询所有用户
        /*
        Verify verify = new Verify();
        ArrayList<String> register_result = (ArrayList<String>) verify.show_user();
        Iterator<String> iter = register_result.iterator();
        while (iter.hasNext()) {  //执行过程中会执行数据锁定，性能稍差，若在循环过程中要去掉某个元素只能调用iter.remove()方法。
            System.out.println("username:" + iter.next());
        }
        */


    }


}
