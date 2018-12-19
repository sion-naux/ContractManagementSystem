package servlet;

import Utils.Get_Para_Data;
import entity.ContributeContract;
import entity.CurrentUser;
import logic.contract_countersign;
import logic.contract_drag;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import Utils.Get_Con_List;

public class ConcludeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StringBuffer sb = new Get_Para_Data().getParaData(request.getReader());

        String conNum = null;
        String sign_msg = null;
        Date sign_time = null;
        String conclude_user = CurrentUser.username;
        int type = 1;

        conNum = sb.toString().split("&")[0];
        sign_msg = sb.toString().split("&")[2];

        PrintWriter pw = response.getWriter();
        String result = null;
        result = "{ \"msg\" : \"success\"}";
        pw.print(result);
        pw.flush();
        pw.close();

        contract_countersign countersign = new contract_countersign(conclude_user);
        countersign.insertComment(conNum,sign_msg,3);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String client = CurrentUser.username;

        if(request.getRequestURL().toString().contains("over_conclude")) {
            request.setAttribute("get_contract_list", Get_Con_List.getInstance().get_contract_list(3,1,client,5));
            request.getRequestDispatcher("jsp/over_conclude.jsp").forward(request, response);
        }
        else {
            request.setAttribute("get_contract_list", Get_Con_List.getInstance().get_contract_list(3,0,client,4));
            request.getRequestDispatcher("jsp/conclude.jsp").forward(request, response);
        }




    }
}
