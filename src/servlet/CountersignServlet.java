package servlet;

import Utils.Get_Con_List;
import Utils.Get_Para_Data;
import entity.ContributeContract;
import entity.CurrentUser;
import logic.contract_countersign;
import logic.contract_drag;
import logic.contract_sign;

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

        StringBuffer sb = new Get_Para_Data().getParaData(request.getReader());

        String conNum = null;
        String sign_msg = null;
        Date sign_time = null;
        String sign_user = CurrentUser.username;
        int type = 1;

        conNum = sb.toString().split("&")[0];
        sign_msg = sb.toString().split("&")[2];

        PrintWriter pw = response.getWriter();
        String result = null;
        result = "{ \"msg\" : \"success\"}";
        pw.print(result);
        pw.flush();
        pw.close();

        contract_countersign contract_countersign = new contract_countersign(sign_user);
        contract_countersign.insertComment(conNum, sign_msg, 1);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String client = CurrentUser.username;

        if(request.getRequestURL().toString().contains("get_countersign_content")) {
            PrintWriter pw = response.getWriter();
            String content =  Get_Con_List.getInstance().find_Cont_Info(request.getParameter("cont_num"),1,1,client);
            String result = "{ \"msg\" : \""+content+"\"}";
            pw.print(result);
            pw.flush();
            pw.close();
        }
        else if(request.getRequestURL().toString().contains("over_countersign")) {
            request.setAttribute("get_contract_list",  Get_Con_List.getInstance().get_contract_list(1,1,client,1));
            request.getRequestDispatcher("jsp/over_countersign.jsp").forward(request, response);
        }
        else {
            request.setAttribute("get_contract_list",  Get_Con_List.getInstance().get_contract_list(1,0,client,1));
            request.getRequestDispatcher("jsp/countersign.jsp").forward(request, response);
        }


    }

//    private String get_contract_list(int type, int state, String client) {
//        String contribute_contract_list = "";
//        List<ContributeContract> contribute_list;
//        contribute_list = contract_drag.show_contract(type, state, client);
//        Iterator<ContributeContract> iter = contribute_list.iterator();
//        while (iter.hasNext()) {
//            ContributeContract contributeContract = iter.next();
//            String num = new String();
//            String name = new String();
//            String time = new String();
//            String username = new String();
//            num = contributeContract.getNum();
//            name = contributeContract.getName();
//            time = contributeContract.getTime().split(" ")[0];
//            username = contributeContract.getUserName();
//            System.out.println("合同编号:" + num);
//            System.out.println("合同名称:" + name);
//            System.out.println("起草时间:" + time);
//            System.out.println("起草人:" + username);
//            String item = "";
//            String btnName = "查看";
//            String methodName = "over_countersign";
//            if(state == 0){
//                btnName = STATIONS[state];
//                methodName = "countersign";
//            }
//            item = "<tr id=\""+num+"\"><td>" + num +
//                    "</td><td>" + name +
//                    "</td><td>" + time +
//                    "</td><td>" + username +
//                    "</td>" + "<td><button class=\"btn btn-info \" onclick=\""+methodName+"(this)\">"+btnName+"</button></td></tr>";
//            System.out.println("表格生成的一行：" + item);
//            contribute_contract_list += item;
//        }
//
//        return contribute_contract_list;
//    }

//    public StringBuffer Get_Para_Data(BufferedReader bufferedReader){
//        StringBuffer sb = new StringBuffer();
//        String line = null;
//        try{
//            BufferedReader br = bufferedReader;
//            while((line = br.readLine()) != null)
//                sb.append(line);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        return sb;
//    }

}
