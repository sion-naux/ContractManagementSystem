package logic;

import database.JDBCFacade;
import database.dbConfig;
import entity.Role;
import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author furuixuan
 * @version V1.0
 * @Project: ContractManagementSystem
 * @Package logic
 * @date 2018/12/12 14:33
 */

public class Allocate {
    //管理员界面
    //get role information from TABLE RIGHT
    //显示用户已有角色
    public String getRoleName(User user, String userName) {
        user.setName(userName);
        try {
            String select_sql = "SELECT FROM rights WHERE userName ='" + user.getName() + "'";
            JDBCFacade jdbc = new JDBCFacade();
            jdbc.open(dbConfig.driverName, dbConfig.newjdbcUrl, dbConfig.userName, dbConfig.userPwd);


            ResultSet rs = jdbc.executeQuery(select_sql);
            //     while (rs.next()) {
            String roleName = rs.getString("roleName");
            System.out.println("用户：" + userName + " 当前角色为：" + roleName);
            //          this.role.setName(roleName);
            return roleName;
            //System.out.println(name);
            //   }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }

    //管理员授权
    //获取角色列表
    //input：无  output：当前所有角色名组成的list
    public static List<String> showRole() {
        //界面上显示出已有权限角色名，以供选择
        List<String> roles = new ArrayList();

        try {
            String select_sql = "SELECT name FROM role.";
            JDBCFacade jdbc = new JDBCFacade();
            jdbc.open(dbConfig.driverName, dbConfig.newjdbcUrl, dbConfig.userName, dbConfig.userPwd);

            ResultSet rs = jdbc.executeQuery(select_sql);

            System.out.println("所有可选角色有：");
            while (rs.next()) {
                String roleName = rs.getString("name");
                roles.add(roleName);
                //return roleName;
                System.out.println(roleName);
            }
            return roles;
        } catch (Exception e) {
            e.printStackTrace();
            return roles;//list is empty
        }

    }

    //为当前用户分配角色（权限）
    //input：用户名、角色名 output：分配结果
    public static boolean allocateRole(String userName, String roleName) {
        //查询用户权限列表信息
        //获取权限配置信息，存入权限表
        String insert_sql = "INSERT INTO right(userName,roleName) VALUES('" + userName + "','" + roleName + "')";
        JDBCFacade jdbc = new JDBCFacade();
        jdbc.open(dbConfig.driverName, dbConfig.newjdbcUrl, dbConfig.userName, dbConfig.userPwd);

        int insert_result = jdbc.executeUpdate(insert_sql);
        if (insert_result == 0) {
            System.out.println("分配角色：" + roleName + " 给用户：" + userName + "，失败");
            return false;
        } else {
            System.out.println("分配角色：" + roleName + " 给用户：" + userName + "，成功");
            return true;
        }

    }

    //数据验证：显示具有对应资格的用户姓名
    //界面上显示出已有权限角色名，以供选择
    public static List<List> showWhoHasRights() {
        JDBCFacade jdbc = new JDBCFacade();
        jdbc.open(dbConfig.driverName, dbConfig.newjdbcUrl, dbConfig.userName, dbConfig.userPwd);

        List<List> persons_hasRights = new ArrayList();//所有
        List<String> countersign_roles = new ArrayList();//会签
        List<String> examine_roles = new ArrayList();//审批
        List<String> sign_roles = new ArrayList();//签订

        //会签
        try {
            String select_sql =
                    "SELECT userName FROM rights WHERE roleName IN(SELECT name FROM role WHERE functions LIKE '%会签合同%')";

            System.out.println("会签可分配人有：");

            ResultSet rs = jdbc.executeQuery(select_sql);
            while (rs.next()) {
                String sign_person = rs.getString("userName");
                countersign_roles.add(sign_person);
                System.out.println(sign_person);
            }
            persons_hasRights.add(countersign_roles);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //审批
        try {
            String select_sql =
                    "SELECT userName FROM rights WHERE roleName IN(SELECT name FROM role WHERE functions LIKE '%审批合同%')";

            System.out.println("审批可分配人有：");

            ResultSet rs = jdbc.executeQuery(select_sql);
            while (rs.next()) {
                String examin_person = rs.getString("userName");
                examine_roles.add(examin_person);
                System.out.println(examin_person);
            }
            persons_hasRights.add(examine_roles);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //签订
        try {
            String select_sql =
                    "SELECT userName FROM rights WHERE roleName IN(SELECT name FROM role WHERE functions LIKE '%签订合同%')";

            System.out.println("签订可分配人有：");

            ResultSet rs = jdbc.executeQuery(select_sql);
            while (rs.next()) {
                String sign_person = rs.getString("userName");
                sign_roles.add(sign_person);
                System.out.println(sign_person);
            }
            persons_hasRights.add(sign_roles);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return persons_hasRights;
    }

    //分配会签人
    //input：合同编号，分配用户名 output：分配结果
    public static boolean allocateCountersign(String contractID, List<String> usersName) {
        JDBCFacade jdbc = new JDBCFacade();
        jdbc.open(dbConfig.driverName, dbConfig.newjdbcUrl, dbConfig.userName, dbConfig.userPwd);

        for (int i = 0; i < usersName.size(); i++) {
            String insert_sql = "INSERT INTO contract_process(conNum,type,state,userName) values('" + contractID + "',1,0,'" + usersName.get(i) + "')";
            int insert_result = jdbc.executeUpdate(insert_sql);
            if (insert_result == 0) {
                System.out.println("分配会签权给：" + usersName.get(i) + "失败");
                return false;
            } else {
                System.out.println("分配会签权给：" + usersName.get(i) + "成功");
            }
        }

        return true;
    }

    //分配审批人
    //input：合同编号，分配用户名 output：分配结果
    public static boolean allocateExamine(String contractID, List<String> usersName) {
        JDBCFacade jdbc = new JDBCFacade();
        jdbc.open(dbConfig.driverName, dbConfig.newjdbcUrl, dbConfig.userName, dbConfig.userPwd);

        for (int i = 0; i < usersName.size(); i++) {
            String insert_sql = "INSERT INTO contract_process(conNum,type,state,userName) values('" + contractID + "',2,0,'" + usersName.get(i) + "')";
            int insert_result = jdbc.executeUpdate(insert_sql);
            if (insert_result == 0) {
                System.out.println("分配审批权给：" + usersName.get(i) + "失败");
                return false;
            } else {
                System.out.println("分配审批权给：" + usersName.get(i) + "成功");
            }
        }

        return true;
    }

    //分配签订人
    //input：合同编号，分配用户名 output：分配结果
    public static boolean allocateSign(String contractID, List<String> usersName) {
        JDBCFacade jdbc = new JDBCFacade();
        jdbc.open(dbConfig.driverName, dbConfig.newjdbcUrl, dbConfig.userName, dbConfig.userPwd);

        for (int i = 0; i < usersName.size(); i++) {
            String insert_sql = "INSERT INTO contract_process(conNum,type,state,userName) values('" + contractID + "',3,0,'" + usersName.get(i) + "')";
            int insert_result = jdbc.executeUpdate(insert_sql);
            if (insert_result == 0) {
                System.out.println("分配签订权给：" + usersName.get(i) + "失败");
                return false;
            } else {
                System.out.println("分配签订权给：" + usersName.get(i) + "成功");
            }
        }

        return true;
    }
}
