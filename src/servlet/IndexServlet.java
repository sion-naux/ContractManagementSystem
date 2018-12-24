package servlet;

import Utils.GetNumber;
import entity.CurrentUser;
import entity.Number;
import logic.RightManage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Number number = GetNumber.getAll(CurrentUser.username);
        req.setAttribute("number", number);

        req.setAttribute("right_list", CurrentUser.right_list);
        req.getRequestDispatcher("jsp/index.jsp").forward(req, resp);
    }
}
