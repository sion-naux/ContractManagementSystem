package servlet;

import Utils.Get_Para_Data;
import entity.CurrentUser;
import logic.contract_approval;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OverArrpovalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("jsp/over_approval.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<LinkedHashMap> list =new ArrayList<LinkedHashMap>();//用于存放返回的集合
        contract_approval approval = new contract_approval(CurrentUser.username);
        list = approval.overApproval();
        request.setAttribute("default_list",list);
//        request.setAttribute("test",list);
        for (Map m : list)
        {
            for (Object k : m.keySet())
            {
                System.out.println(m.get(k));
            }
        }
        request.setAttribute("right_list", CurrentUser.right_list);
        request.getRequestDispatcher("jsp/over_approval.jsp").forward(request, response);
//        response.sendRedirect("jsp/over_approval.jsp");
    }
}
