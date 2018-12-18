package servlet;

import logic.contract_approval;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entity.CurrentUser;
public class ApprovalServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("post");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Map> list =new ArrayList<Map>();//用于存放返回的集合
        contract_approval approval = new contract_approval(CurrentUser.username);
        list = approval.defaultFind();
        request.setAttribute("default_list",list);
        request.setAttribute("test",list);
        for (Map m : list)
        {
            for (Object k : m.keySet())
            {
                System.out.println(m.get(k));
            }
        }

        request.getRequestDispatcher("jsp/approval.jsp").forward(request, response);
//        response.sendRedirect("jsp/approval.jsp");
    }
}
