package logic;

import database.JDBCFacade;
import database.dbConfig;
import entity.Log;
import entity.Role;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LogManage {
    public static boolean insert_log(Log log){
        String insert_sql = "INSERT INTO log " + "VALUES ('" + log.getUserName() + "','" + log.getContent() + "','" + log.getTime() + "')";
        JDBCFacade jdbc = new JDBCFacade();
        jdbc.open(dbConfig.driverName, dbConfig.newjdbcUrl, dbConfig.userName, dbConfig.userPwd);
        int insert_result = jdbc.executeUpdate(insert_sql);
        if (insert_result == 0) {
            System.out.println("创建失败。请检查您的输入");
            return false;
        } else {
            System.out.println("创建日志成功！");
            return true;
        }
    }


    //获取日志列表
    public static List<Log> getLogList(){
        List<Log> log_list = new ArrayList<Log>();
        try {
            JDBCFacade jdbc = new JDBCFacade();
            //打开数据库连接
            jdbc.open(dbConfig.driverName, dbConfig.newjdbcUrl, dbConfig.userName, dbConfig.userPwd);
            String sql = "select * from log";
            System.out.println("生成的sql语句是：" + sql);
            ResultSet rs = jdbc.executeQuery(sql);
            while (rs.next()) {
                log_list.add(new Log(rs.getString("userName"),rs.getString("content"),rs.getString("time")));
            }
            jdbc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return log_list;
    }
}
