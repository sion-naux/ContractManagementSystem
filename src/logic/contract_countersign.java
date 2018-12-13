package logic;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;

public class contract_countersign {
    /**
     * 1.默认查找这个人的合同操作流程表(contract_process)中会签中且未完成的合同
     * 2.用户选择一个合同并会签 系统将更新合同流程信息表(contract_process)（改为已完成并插入意见）
     * 3.当用户点击指定合同时 可查询该合同的内容 这里和2合并（即自动显示内容并会签）
     * 4.判断是有所有人都完成了对这个合同的会签 若都完成了 将合同状态表(contract_state)中状态改为会签完成 并更新完成时间
     * 5.重新显示该用户要会签的合同
     * 注：还应提供指定条件查找功能
     */
    String username;

    public contract_countersign(String username) {
        this.username = username;
    }

    public void defaultFind() {
        String sql = "select name from contract,contract_process where contract.num = contract_process.conNum"
                + " and type = 1 and state = 0 and contract_process.userName = \"" + username + "\"";
        System.out.println(sql);
        try {
            PreparedStatement pst = (PreparedStatement) controller.connect.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            System.out.println("您需要会签的合同有：");
            while (rs.next()) {
                System.out.println(rs.getString("name") + " ");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void clickOneHetong(String name) {
        PreparedStatement pst;
        ResultSet rs;
        try {
            String sql = "select * from contract where name = ?";
            pst = (PreparedStatement) controller.connect.prepareStatement(sql);
            pst.setString(1, name);
            rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println("合同编号:" + rs.getString("num"));
                System.out.println("合同名称:" + rs.getString("name"));
                System.out.println("顾客:" + rs.getString("customer"));
                System.out.println("生效时间:" + rs.getString("beginTime"));
                System.out.println("结束时间:" + rs.getString("endTime"));
                System.out.println("起草人:" + rs.getString("userName"));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void insertComment(String name) {
        String content = "不错！";
        String num;
        //首先查找该合同名对应的合同编号
        String sql1 = "select * from contract where name = \"" + name + "\"";
        System.out.println(sql1);
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) controller.connect.prepareStatement(sql1);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                num = rs.getString(1);
                if (content == null) {
                    System.out.println("输入的会签意见不能为空!");
                } else {
                    long l = System.currentTimeMillis();
                    Date finishtime = new Date(l);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    sdf.format(finishtime);
                    String sql2 = "update contract_process set content = ?,time = ?,state = 1 where conNum = \"" + num + "\"";
                    pst = (PreparedStatement) controller.connect.prepareStatement(sql2);
                    pst.setString(1, content);
                    pst.setDate(2, finishtime);
                    System.out.println(pst);
                    pst.executeUpdate();
                    System.out.println("修改成功(*￣︶￣)");
                    Ifcompleted(num);
                }
            }
            //插入评论
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("操作失败o(╥﹏╥)");
            e.printStackTrace();
        }
    }

    public void Ifcompleted(String num) {
        PreparedStatement pst;
        String sql4 = "select * from contract_process where conNum = \"" + num + "\"" + "and type = 1 and state = 0";
        System.out.println(sql4);
        try {
            pst = (PreparedStatement) controller.connect.prepareStatement(sql4);
            ResultSet rs = pst.executeQuery();
            if (!rs.next()) {
                long l = System.currentTimeMillis();
                Date finishtime = new Date(l);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.format(finishtime);
                String sql5 = "update contract_state set type = 2,time = ? where num = ?";
                pst = (PreparedStatement) controller.connect.prepareStatement(sql5);
                pst.setDate(1, finishtime);
                pst.setString(2, num);
                pst.executeUpdate();
                System.out.println("该合同的所有会签已完成！");
            } else {
                System.out.println("还有部分会签尚未完成，革命尚需努力！");
            }
        } catch (SQLException e) {
            System.out.println("系统有点问题嘤嘤嘤");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void likeSearch(String condition) {
        String sql6 = "select name from contract,contract_process where contract.num = contract_process.conNum"
                + " and type = 1 and state = 0 and contract_process.userName = \"" + username + "\" and name like \"%" + condition + "%\"";
        System.out.println(sql6);
        try {
            PreparedStatement pst = (PreparedStatement) controller.connect.prepareStatement(sql6);
            ResultSet rs = pst.executeQuery();
            System.out.println("筛选如下：");
            while (rs.next()) {
                System.out.println(rs.getString("name") + " ");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

