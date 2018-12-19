package servlet;

import entity.ContributeContract;
import logic.contract_drag;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import Utils.Get_Con_List;

@WebServlet(name = "ConcludeServlet")
public class ConcludeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String client = "111";

        if(request.getRequestURL().toString().contains("over_countersign")) {
            request.setAttribute("get_contract_list", Get_Con_List.getInstance().get_contract_list(3,1,client));
            request.getRequestDispatcher("jsp/over_conclude.jsp").forward(request, response);
        }
        else {
            request.setAttribute("get_contract_list", Get_Con_List.getInstance().get_contract_list(3,0,client));
            request.getRequestDispatcher("jsp/conclude.jsp").forward(request, response);
        }




    }
}
