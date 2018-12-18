package servlet;

import entity.Right;
import entity.Role;
import logic.ManageRole;
import logic.RightManage;
import logic.Verify;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class AuthManageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("role_list", get_role_list());
        req.setAttribute("auth_person_list", get_auth_person_list());
        req.getRequestDispatcher("jsp/auth_manage.jsp").forward(req, resp);
    }

    private String get_role_list() {
        String role_list_string = "";
        List<Role> role_list = ManageRole.getRoleList();
        Iterator<Role> iter = role_list.iterator();
        while (iter.hasNext()) {
            Role role = iter.next();
            String name = role.getName();
            String item = "";
            item = "<div class=\"row-fluid\"><label class=\"role_label\"><input type=\"checkbox\" name=\"checkbox_role\" value=\""+name+"\"></label>" + name + "</div>";
            System.out.println("表格生成的一行：" + item);
            role_list_string += item;
        }
        return role_list_string;

    }

    private String get_auth_person_list() {
        String auth_person_list = "";


        List<Right> right_list = RightManage.getRightList();

        Iterator<Right> iter = right_list.iterator();
        while (iter.hasNext()) {
            Right right = iter.next();
            String userName = right.getUserName();
            String roleName = right.getRoleName();


            String item = "";
            item = "<tr><td>" + userName +
                    "</td><td>" + roleName +
                    "</td><td><button class=\"btn btn-info\" onclick=\"auth(this);\">授权</button></td></tr>";
            System.out.println("表格生成的一行：" + item);
            auth_person_list += item;
        }

        return auth_person_list;

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = new String(req.getParameter("role_list").getBytes("ISO-8859-1"), "utf-8");
        String username = new String(req.getParameter("username_input").getBytes("ISO-8859-1"), "utf-8");
        System.out.println("用户："+username);
        System.out.println("授权角色："+role);
        int r = RightManage.updateRight(username,role);
        if(r==-1){
            System.out.println("授权失败");
        }else{
            System.out.println("授权成功");
        }

        doGet(req, resp);
    }
}
