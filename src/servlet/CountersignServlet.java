package servlet;

import entity.ContributeContract;
import logic.contract_countersign;
import logic.contract_drag;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

public class CountersignServlet extends HttpServlet {

    public static String[] STATIONS = new String[]
            {"起草","会签","审批","签订","分配","定稿"};

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       StringBuffer sb = getParaData(request.getReader());

        String conNum = null;
        String sign_msg = null;
        Date sign_time = null;
        String sign_user = "DOng";
        int type = 1;

        conNum = sb.toString().split("&")[0];
        sign_msg = sb.toString().split("&")[2];

        PrintWriter pw = response.getWriter();
        String result = null;
        result = "{ \"msg\" : \"success\"}";
        pw.print(result);
        pw.flush();
        pw.close();

        contract_countersign countersign = new contract_countersign(sign_user);
        countersign.insertComment(conNum,sign_msg);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getRequestURL().toString().contains("get_countersign_content")) {
            PrintWriter pw = response.getWriter();
            contract_countersign countersign = new contract_countersign();
            String content = countersign.find_CounterSign_Cont(request.getParameter("cont_num"));
            String result = "{ \"msg\" : \""+content+"\"}";
            pw.print(result);
            pw.flush();
            pw.close();
        }
        else if(request.getRequestURL().toString().contains("over_countersign")) {
            request.setAttribute("get_contract_list", get_contract_list(1, 1));
            request.getRequestDispatcher("jsp/over_countersign.jsp").forward(request, response);
        }
        else {
            request.setAttribute("get_contract_list", get_contract_list(1, 0));
            request.getRequestDispatcher("jsp/countersign.jsp").forward(request, response);
        }


    }

    private String get_contract_list(int type, int state) {
        String contribute_contract_list = "";
        List<ContributeContract> contribute_list;
        contribute_list = contract_drag.show_contract(type, state);
        Iterator<ContributeContract> iter = contribute_list.iterator();
        while (iter.hasNext()) {
            ContributeContract contributeContract = iter.next();
            String num = new String();
            String name = new String();
            String time = new String();
            String username = new String();
            num = contributeContract.getNum();
            name = contributeContract.getName();
            time = contributeContract.getTime().split(" ")[0];
            username = contributeContract.getUserName();
            System.out.println("合同编号:" + num);
            System.out.println("合同名称:" + name);
            System.out.println("起草时间:" + time);
            System.out.println("起草人:" + username);
            String item = "";
            String btnName = "查看";
            String methodName = "over_countersign";
            if(state == 0){
                btnName = STATIONS[state];
                methodName = "countersign";
            }
            item = "<tr id=\""+num+"\"><td>" + num +
                    "</td><td>" + name +
                    "</td><td>" + time +
                    "</td><td>" + username +
                    "</td>" + "<td><button class=\"btn btn-info \" onclick=\""+methodName+"(this)\">"+btnName+"</button></td></tr>";
            System.out.println("表格生成的一行：" + item);
            contribute_contract_list += item;
        }

        return contribute_contract_list;
    }

    public StringBuffer getParaData(BufferedReader bufferedReader){
        StringBuffer sb = new StringBuffer();
        String line = null;
        try{
            BufferedReader br = bufferedReader;
            while((line = br.readLine()) != null)
                sb.append(line);
        } catch (Exception e){
            e.printStackTrace();
        }
        return sb;
    }

}
