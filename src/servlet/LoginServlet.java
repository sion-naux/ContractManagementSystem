package servlet;

import entity.CurrentUser;
import logic.RightManage;
import logic.Verify;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        userName=new String(userName.getBytes("ISO-8859-1"),"utf-8");
        String password = req.getParameter("password");
        System.err.println("username got:" + userName + " ; " + "password got" + password);

        Verify verify = new Verify();
        int register_result = verify.login(userName, password);

        if (register_result == -1) {
            req.setAttribute("error", "用户不存在！");
            System.out.println("用户不存在！");
            req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
        } else if (register_result == -2) {
            req.setAttribute("error", "密码错误！");
            System.out.println("密码错误！");
            req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
        } else if (register_result == 1) {
            CurrentUser.username = userName;
            CurrentUser.right_list = get_right_list(userName);
            resp.sendRedirect(req.getContextPath() + "/index");
        } else {
            req.setAttribute("error", "登陆失败！");
            System.out.println("登陆失败！");
            req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
        }


    }
    private List<String> get_right_list(String username) {
        List<String> right_list = new ArrayList<String>();
        right_list = RightManage.get_right_list_of_user(username);
        return right_list;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("jsp/login.jsp");
    }
}
