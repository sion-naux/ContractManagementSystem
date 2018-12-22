package servlet;

import Utils.Get_Para_Data;
import Utils.Get_Time;
import entity.ContributeContract;
import entity.CurrentUser;
import entity.Log;
import logic.LogManage;
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
        request.setCharacterEncoding("utf-8");
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
        countersign.insertComment(conNum, sign_msg, 3);
        LogManage.insert_log(new Log(CurrentUser.username, "定稿合同，合同id：" + conNum + " 意见：" + sign_msg, new Get_Time().getCurrentTime()));


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String client = CurrentUser.username;
        request.setAttribute("right_list", CurrentUser.right_list);
        if (request.getRequestURL().toString().contains("get_conclude_content")) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter pw = response.getWriter();
            String content = Get_Con_List.getInstance().find_Cont_Info(request.getParameter("cont_num"), 3, 1, client);
            String result = "{ \"msg\" : \"" + content + "\"}";
            pw.print(result);
            pw.flush();
            pw.close();
        } else if (request.getRequestURL().toString().contains("over_conclude")) {
            request.setAttribute("get_contract_list", Get_Con_List.getInstance().get_contract_list(3, 1, client, 4));
            request.getRequestDispatcher("jsp/over_conclude.jsp").forward(request, response);
        } else {
            request.setAttribute("get_contract_list", Get_Con_List.getInstance().get_contract_list(3, 0, client, 4));
            request.getRequestDispatcher("jsp/conclude.jsp").forward(request, response);
        }


    }
}
