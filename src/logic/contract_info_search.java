package logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.PreparedStatement;
import database.JDBCFacade;
import database.dbConfig;

public class contract_info_search {
    private JDBCFacade jdbc;
    String userName;

    public contract_info_search() {
        jdbc = new JDBCFacade();
        //打开数据库连接
        jdbc.open(dbConfig.driverName, dbConfig.newjdbcUrl, dbConfig.userName, dbConfig.userPwd);
    }

    public List<Map> likesearch(String keyword) {
        String num;
        String name;
        String beginTime;
        String userName;
        String type;
        String content;
        List<Map> list = new ArrayList<Map>();
        try {
            String sql = "select * from contract where name like '%" + keyword + "%'";
            PreparedStatement ps = (PreparedStatement) jdbc.getPrepareStatement(sql);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map map = new HashMap();
                num = rs.getString("num");
                name = rs.getString("name");
                beginTime = rs.getString("beginTime");
                userName = rs.getString("userName");
                content = rs.getString("content");
                System.out.println(rs.getString("name"));
                System.out.println(rs.getString("beginTime"));
                System.out.println(rs.getString("userName"));
                map.put("name", name);
                map.put("beginTime", beginTime);
                map.put("userName", userName);
                map.put("content", content);
                String sql2 = "select type from contract_state where num = ?";
                PreparedStatement ps2 = (PreparedStatement) jdbc.getPrepareStatement(sql2);
                ps2.setString(1, num);
                ResultSet rs2 = ps2.executeQuery();
                if (rs2.next()) {
                    type = rs2.getString("type");
                    switch (type) {
                        case ("1"):
                            map.put("type", "起草");
                            break;
                        case ("2"):
                            map.put("type", "会签完成");
                            break;
                        case ("3"):
                            map.put("type", "定稿完成");
                            break;
                        case ("4"):
                            map.put("type", "审批完成");
                            break;
                        case ("5"):
                            map.put("type", "签订完成");
                            break;
                    }
                    System.out.println(name + beginTime + userName + type);

                }
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Map> defaultsearch() {
        String num;
        String name;
        String beginTime;
        String userName;
        String type;
        String content;
        List<Map> list = new ArrayList<Map>();
        try {
            String sql = "select * from contract";
            PreparedStatement ps = (PreparedStatement) jdbc.getPrepareStatement(sql);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map map = new HashMap();
                num = rs.getString("num");
                name = rs.getString("name");
                beginTime = rs.getString("beginTime");
                userName = rs.getString("userName");
                content = rs.getString("content");
                System.out.println(rs.getString("name"));
                System.out.println(rs.getString("beginTime"));
                System.out.println(rs.getString("userName"));
                System.out.println(rs.getString("content"));
                map.put("name", name);
                map.put("beginTime", beginTime);
                map.put("userName", userName);
                map.put("content", content);
                String sql2 = "select type from contract_state where num = ?";
                PreparedStatement ps2 = (PreparedStatement) jdbc.getPrepareStatement(sql2);
                ps2.setString(1, num);
                ResultSet rs2 = ps2.executeQuery();
                if (rs2.next()) {
                    type = rs2.getString("type");
                    switch (type) {
                        case ("1"):
                            map.put("type", "起草");
                            break;
                        case ("2"):
                            map.put("type", "会签完成");
                            break;
                        case ("3"):
                            map.put("type", "定稿完成");
                            break;
                        case ("4"):
                            map.put("type", "审批完成");
                            break;
                        case ("5"):
                            map.put("type", "签订完成");
                            break;
                    }
                    System.out.println(name + beginTime + userName + type + content);
                }
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
