package servlet;

import Utils.Get_Con_List;
import Utils.Get_Para_Data;
//import com.google.gson.JsonObject;
import Utils.Get_Time;
import entity.CurrentUser;
import entity.Log;
import logic.LogManage;
import logic.contract_countersign;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "FinalServlet")
public class FinalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StringBuffer sb = new Get_Para_Data().getParaData(request.getReader());

        String conNum = null;

        PrintWriter pw = response.getWriter();
        String result = "{ \"msg\" : \"success\"}";
        pw.print(result);
        pw.flush();
        pw.close();

        Get_Con_List.getInstance().Change_final_status(sb.toString());
        LogManage.insert_log(new Log(CurrentUser.username, "签订合同，合同id：" + sb.toString(), new Get_Time().getCurrentTime()));


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String client = CurrentUser.username;
        request.setAttribute("right_list", CurrentUser.right_list);

        if(request.getRequestURL().toString().contains("get_final_content")) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter pw = response.getWriter();
            ArrayList<String> content =  Get_Con_List.getInstance().find_final_Cont_Info(request.getParameter("cont_num"), 2,client);



            String result = "{ \"client_name\" : \""+ content.get(0) + "\", " +
                              "\"begin_time\" : \"" + content.get(1) + "\", " +
                              "\"end_time\" : \"" + content.get(2) + "\", " +
                              "\"contract_content\" : \"" + content.get(3) + "\"}";
            pw.print(result);

            pw.flush();
            pw.close();
        }
        else if(request.getRequestURL().toString().contains("over_cont_final")) {
            request.setAttribute("get_contract_list",  Get_Con_List.getInstance().get_final_contract_list(1,client,3));
            request.getRequestDispatcher("jsp/over_cont_final.jsp").forward(request, response);
        }
        else {
            request.setAttribute("get_contract_list",  Get_Con_List.getInstance().get_final_contract_list(0,client,2));
            request.getRequestDispatcher("jsp/cont_final.jsp").forward(request, response);
        }


    }
}
