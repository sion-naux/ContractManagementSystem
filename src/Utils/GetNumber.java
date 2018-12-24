package Utils;

import entity.ContributeContract;
import entity.Number;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static logic.contract_drag.jdbcObject;

public class GetNumber {
    public static Number getAll(String username) {
        Number number = new Number();

        try {
            String sql = "select count(*) as num from contract_process where userName='" + username + "' and type=1 and state=0 ";
            System.out.println("生成的sql语句是：" + sql);
            ResultSet rs = jdbcObject.executeQuery(sql);
            while (rs.next()) {
                number.setHuiQian(Integer.parseInt(rs.getString("num")));
            }

            sql = "select count(*) as num from contract_process where userName='" + username + "' and type=2 and state=0 and conNum in (select num from contract_state where type=3)";
            System.out.println("生成的sql语句是：" + sql);
            rs = jdbcObject.executeQuery(sql);
            while (rs.next()) {
                number.setShenPi(Integer.parseInt(rs.getString("num")));
            }

            sql = "select count(*) as num from contract_process where userName='" + username + "' and type=3 and state=0 and conNum in (select num from contract_state where type=4)";
            System.out.println("生成的sql语句是：" + sql);
            rs = jdbcObject.executeQuery(sql);
            while (rs.next()) {
                number.setDingGao(Integer.parseInt(rs.getString("num")));
            }

            sql = "select count(*) as countnum from contract where userName='" + username + "' and contract.num in (select num from contract_state where type=2)";
            System.out.println("生成的sql语句是：" + sql);
            rs = jdbcObject.executeQuery(sql);
            while (rs.next()) {
                number.setDingGao(Integer.parseInt(rs.getString("countnum")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return number;
    }
}
