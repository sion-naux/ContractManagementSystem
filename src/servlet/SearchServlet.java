package servlet;

import Utils.Get_Para_Data;
import entity.CurrentUser;
import logic.contract_approval;
import logic.contract_info_search;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.removeAttribute("default_list");
        request.setCharacterEncoding("utf-8");
        StringBuffer sb = new Get_Para_Data().getParaData(request.getReader());
        String keyword = sb.toString();
        List<Map> list =new ArrayList<Map>();//用于存放返回的集合
        contract_info_search info_search = new contract_info_search();
        list = info_search.likesearch(keyword);
        request.setAttribute("default_list",list);
//        response.sendRedirect("jsp/approval.jsp");
        request.getRequestDispatcher("jsp/cont_info_search.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        request.removeAttribute("default_list");
        List<Map> list =new ArrayList<Map>();//用于存放返回的集合
        contract_info_search info_search = new contract_info_search();
        list = info_search.defaultsearch();
        request.setAttribute("default_list",list);
        request.setAttribute("right_list", CurrentUser.right_list);

        request.getRequestDispatcher("jsp/cont_info_search.jsp").forward(request, response);
    }
}
