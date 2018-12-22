package servlet;

import Utils.Get_Time;
import entity.CurrentUser;
import entity.Log;
import logic.LogManage;
import logic.ManageRole;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddRoleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String add_role_name = new String(req.getParameter("add_role_name").getBytes("ISO-8859-1"), "utf-8");
        String add_role_description = new String(req.getParameter("add_role_description").getBytes("ISO-8859-1"), "utf-8");
        String addrole_list[] = new String(req.getParameter("addrole_list").getBytes("ISO-8859-1"), "utf-8").split(",");
        ManageRole manageRole = new ManageRole();
        String fuctions = "";
        for (int i = 0; i < addrole_list.length - 1; i++) {
            fuctions += (addrole_list[i] + "，");//注意这里是中文逗号
        }
        fuctions += addrole_list[addrole_list.length - 1];
        manageRole.createRole(add_role_name, add_role_description, fuctions);
        LogManage.insert_log(new Log(CurrentUser.username, "添加角色，角色名：" + add_role_name, new Get_Time().getCurrentTime()));



        AuthManageServlet authManageServlet = new AuthManageServlet();
        authManageServlet.doGet(req, resp);
    }
}
