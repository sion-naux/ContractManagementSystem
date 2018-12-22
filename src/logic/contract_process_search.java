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

public class contract_process_search {
    private JDBCFacade jdbc;
    String userName;
    int type;

    public contract_process_search(int type) {
        jdbc = new JDBCFacade();
        //打开数据库连接
        jdbc.open(dbConfig.driverName, dbConfig.newjdbcUrl, dbConfig.userName, dbConfig.userPwd);
        this.type = type;
    }

    public List<Map> search() {
        PreparedStatement ps;
        String num;
        String name;
        String beginTime;
        String userName;
        String content;
        List<Map> list = new ArrayList<Map>();
        try {
            String sql = "select * from contract,contract_state where contract.num = contract_state.num and type = ?";
            ps = (PreparedStatement) jdbc.getPrepareStatement(sql);
            ps.setInt(1, type);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map map = new HashMap();
                num = rs.getString("num");
                name = rs.getString("name");
                beginTime = rs.getString("beginTime");
                userName = rs.getString("userName");
                content = rs.getString("content");
                map.put("name", name);
                map.put("beginTime", beginTime);
                map.put("userName", userName);
                map.put("content", content);
                list.add(map);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
}
