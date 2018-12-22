package servlet;

import Utils.Get_Para_Data;
import entity.CurrentUser;
import logic.contract_info_search;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProcedureServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("right_list", CurrentUser.right_list);
        request.getRequestDispatcher("jsp/cont_proc_search.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        request.setAttribute("right_list", CurrentUser.right_list);
        request.getRequestDispatcher("jsp/cont_proc_search.jsp").forward(request, response);
    }
}
