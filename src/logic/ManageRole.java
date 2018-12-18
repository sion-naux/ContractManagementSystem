package logic;

import database.JDBCFacade;
import database.dbConfig;
import entity.Right;
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

public class ManageRole {
    //创建角色（创建role类的方式）
    //通过创建role类 新增角色到数据库
    public boolean createRole_byclass(Role role) {

        String insert_sql = "INSERT INTO role " + "VALUES ('" + role.getName() + "','" + role.getDescription() + "','" + role.getFunctions() + "')";
        JDBCFacade jdbc = new JDBCFacade();
        jdbc.open(dbConfig.driverName, dbConfig.newjdbcUrl, dbConfig.userName, dbConfig.userPwd);
        int insert_result = jdbc.executeUpdate(insert_sql);
        if (insert_result == 0) {
            System.out.println("Failed to create.");
            return false;
        } else {
            System.out.println("Success to create.");
            return true;
        }

    }

    //创建角色(给好输入的方式)
    //input：角色名，描述，功能    //output：创建结果
    public boolean createRole(String name, String description, String functions) {

        String insert_sql = "INSERT INTO role " + "VALUES ('" + name + "','" + description + "','" + functions + "')";
        JDBCFacade jdbc = new JDBCFacade();
        jdbc.open(dbConfig.driverName, dbConfig.newjdbcUrl, dbConfig.userName, dbConfig.userPwd);
        int insert_result = jdbc.executeUpdate(insert_sql);
        if (insert_result == 0) {
            System.out.println("创建失败。请检查您的输入");
            return false;
        } else {
            System.out.println("创建成功！");
            return true;
        }

    }

    //获取角色列表
    public static List<Role> getRoleList(){
        List<Role> right_list = new ArrayList<Role>();
        try {
            JDBCFacade jdbc = new JDBCFacade();
            //打开数据库连接
            jdbc.open(dbConfig.driverName, dbConfig.newjdbcUrl, dbConfig.userName, dbConfig.userPwd);
            String sql = "select * from role";
            System.out.println("生成的sql语句是：" + sql);
            ResultSet rs = jdbc.executeQuery(sql);
            while (rs.next()) {
                right_list.add(new Role(rs.getString("name"),rs.getString("description"),rs.getString("functions")));
            }
            jdbc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return right_list;
    }


    //查询角色
    //input：角色名  //output：Role类
    public Role searchRole(String roleName) {
        String query_sql = "SELECT * FROM role WHERE NAME='" + roleName + "'";
        JDBCFacade jdbc = new JDBCFacade();
        jdbc.open(dbConfig.driverName, dbConfig.newjdbcUrl, dbConfig.userName, dbConfig.userPwd);
        Role query_role = new Role();

        try {
            ResultSet rs = jdbc.executeQuery(query_sql);

            if (!rs.next()) {
                System.out.println("角色不存在。请查询您的输入！");
            } else {
                query_role.setName(roleName);
                System.out.println("查询角色名：" + query_role.getName());
                String desc = rs.getString("description");
                query_role.setDescription(desc);
                System.out.println("描述：" + query_role.getDescription());
                String functions = rs.getString("functions");
                query_role.setFunctions(functions);
                System.out.println("功能：" + query_role.getFunctions());
            }
            return query_role;
        } catch (Exception e) {
            e.printStackTrace();
            return new Role();
        }
    }

    //删除角色
    public boolean deleteRole(String to_delete_name) {
        String delete_sql = "DELETE FROM role WHERE NAME='" + to_delete_name + "'";
        JDBCFacade jdbc = new JDBCFacade();
        jdbc.open(dbConfig.driverName, dbConfig.newjdbcUrl, dbConfig.userName, dbConfig.userPwd);
        int delete_result = jdbc.executeUpdate(delete_sql);
        if (delete_result == 0) {
            System.out.println("删除角色：" + to_delete_name + "失败");
            return false;
        } else {
            System.out.println("删除角色：" + to_delete_name + "成功；删除 " + delete_sql + " 行");
            return true;
        }
    }

    //更改角色
    //更改对应角色具有的功能（权限）
    //input：roleName,新功能 output：更改结果
    public boolean changeRole(String to_change_name, String newFunc) {
        JDBCFacade jdbc = new JDBCFacade();
        jdbc.open(dbConfig.driverName, dbConfig.newjdbcUrl, dbConfig.userName, dbConfig.userPwd);
        String update_sql = "UPDATE role SET functions='" + newFunc + "' WHERE name='" + to_change_name + "'";
        if (jdbc.executeUpdate(update_sql) == 1) {
            System.out.println("更改成功！");
            return true;
        } else {
            System.out.println("抱歉，该角色不存在");
            return false;
        }

    }

    //更改角色名
    //input：oldName output：更改结果
    public boolean changeRoleName(String old_name, String new_name) {
        JDBCFacade jdbc = new JDBCFacade();
        jdbc.open(dbConfig.driverName, dbConfig.newjdbcUrl, dbConfig.userName, dbConfig.userPwd);
        String update_sql = "UPDATE role SET name='" + new_name + "' WHERE name='" + old_name + "'";
        if (jdbc.executeUpdate(update_sql) == 1) {
            System.out.println("更改成功！");
            return true;
        } else {
            System.out.println("抱歉，该角色不存在");
            return false;
        }

    }
}
