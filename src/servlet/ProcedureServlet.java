package servlet;

import Utils.Get_Para_Data;
import entity.CurrentUser;
import logic.contract_info_search;
import logic.contract_process_search;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProcedureServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        System.out.println(request.getRequestURL().toString());
        if(request.getRequestURL().toString().contains("choice")){
            //设置编码格式
            request.setCharacterEncoding("utf-8");
            String choice = request.getParameter("search_message");
            int type = Integer.parseInt(choice);
            //获取搜索结果
            List<Map> list;
            contract_process_search pro = new contract_process_search(type);
            list = pro.search();

            //返回json对象
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            JSONArray jsonArray = JSONArray.fromObject(list);

            //将数据传入前端
            PrintWriter pw = response.getWriter();
            pw.print(jsonArray);
            pw.flush();
            pw.close();
        }
        else {
            request.removeAttribute("default_list");
            int type = 1;
            //获取搜索结果
            List<Map> list;
            contract_process_search pro = new contract_process_search(type);
            list = pro.search();
            request.setAttribute("default_list", list);
            request.setAttribute("right_list", CurrentUser.right_list);
            request.getRequestDispatcher("jsp/cont_proc_search.jsp").forward(request, response);

        }

    }
}
