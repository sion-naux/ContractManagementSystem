package servlet;

import logic.Verify;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        String password_confirm = req.getParameter("password_confirm");

        System.err.println("In register:" );
        System.err.println("username got:" + userName + " ; " + "password got:" + password+ " ; " + "password_confirm got:" + password_confirm);

        Verify verify = new Verify();
        int register_result = verify.register(userName,password,password_confirm);
        if(register_result == -1){
            req.setAttribute("register_error", "注册失败！");
            System.out.println("注册失败！");
            req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
        }else if(register_result == -2){
            req.setAttribute("register_error", "两次密码不一致！");
            System.out.println("两次密码不一致！");
            req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
        }else if(register_result == -3){
            req.setAttribute("register_error", "该用户名已注册！");
            System.out.println("该用户名已注册！");
            req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
        }else{
            System.out.println("注册成功！");
            resp.sendRedirect("jsp/login.jsp");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("jsp/login.jsp");
    }
}
