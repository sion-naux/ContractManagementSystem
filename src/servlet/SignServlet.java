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

public class SignServlet extends HttpServlet {

    public static String[] STATIONS = new String[]{"起草","审批","签订","分配","定稿"};

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("get_contract_list", get_contract_list(1));

        request.getRequestDispatcher("jsp/countersign.jsp").forward(request, response);



    }

    private String get_contract_list(int state) {
        String contribute_contract_list = "";
        List<ContributeContract> contribute_list;
        contribute_list = contract_drag.show_contract(state);
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
            item = "<tr><td id=\"contract_num\">" + num +
                    "</td><td>" + name +
                    "</td><td>" + time +
                    "</td><td>" + username +
                    "</td>" + "<td><button class=\"btn btn-info \" onclick=\"countersign(this)\">"+STATIONS[state]+"</button></td></tr>";
            System.out.println("表格生成的一行：" + item);
            contribute_contract_list += item;
        }

        return contribute_contract_list;
    }

}
