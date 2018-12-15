package servlet;

import logic.Verify;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
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
            resp.sendRedirect(req.getContextPath() + "/index");
        } else {
            req.setAttribute("error", "登陆失败！");
            System.out.println("登陆失败！");
            req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("jsp/login.jsp");
    }
}
