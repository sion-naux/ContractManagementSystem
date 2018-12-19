package servlet;

import Utils.Get_Para_Data;
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
        request.setCharacterEncoding("utf-8");
        StringBuffer sb = new Get_Para_Data().getParaData(request.getReader());
        String conNum = null;
        String conName = null;
        String content = null;
        String ifpass = null;
        String userName = CurrentUser.username;
        conNum = sb.toString().split("&")[0];
        conName = sb.toString().split("&")[1];
        content = sb.toString().split("&")[2];
        ifpass = sb.toString().split("&")[3];
        if(content.equals("")){
//            request.setAttribute("message","合同意见不能为空！");
//            request.getRequestDispatcher("jsp/approval.jsp").forward(request, response);
            response.sendRedirect("jsp/approval.jsp");
        }else{
            contract_approval approval = new contract_approval(CurrentUser.username);
            approval.decide(conName,ifpass,content);
//            request.getSession().setAttribute("message", "操作成功!");
//            request.getRequestDispatcher("jsp/approval.jsp").forward(request, response);
            response.sendRedirect("jsp/approval.jsp");
        }
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
