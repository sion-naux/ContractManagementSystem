package servlet;

import entity.CurrentUser;
import logic.contract_drag;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class DraftServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String contract_name = request.getParameter("contract_name");
        contract_name=new String(contract_name.getBytes("ISO-8859-1"),"utf-8");
        String contract_customer = request.getParameter("contract_customer");
        contract_customer=new String(contract_customer.getBytes("ISO-8859-1"),"utf-8");
        String contract_begin_time = request.getParameter("contract_begin_time");
        String contract_end_time = request.getParameter("contract_end_time");
        String option_name = request.getParameter("img[]");
        String contract_content = request.getParameter("contract_content");
        contract_content=new String(contract_content.getBytes("ISO-8859-1"),"utf-8");


        contract_drag drag = new contract_drag(contract_name,contract_customer,
                parseDate(contract_begin_time), parseDate(contract_end_time),
                contract_content, CurrentUser.username);
        drag.insertcontract();
        response.sendRedirect(request.getContextPath() + "jsp/draft.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private java.sql.Date parseDate(String str){
        str = str.replaceAll("/","-");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = null;
        try {
            d = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.sql.Date date = new java.sql.Date(d.getTime());
        return date;
    }
}
