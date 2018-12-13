package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        System.err.println(userName+";"+password);
        String myUser = "Dong";
        String myPwd = "5432100";
        if(userName.equals(myUser)&&password.equals(myPwd)) {
            response.sendRedirect(request.getContextPath()+"/index");
        }else {
            request.getRequestDispatcher("jsp/login.sjp").forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("jsp/login.sjp");
    }
}
