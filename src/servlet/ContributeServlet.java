package servlet;


import entity.ContributeContract;
import logic.Verify;
import logic.contract_drag;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ContributeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private String get_contribute_contract_list() {
        String contribute_contract_list = "";
        List<ContributeContract> contribute_list;
        contribute_list = contract_drag.show_contribute_contract();
        Iterator<ContributeContract> iter = contribute_list.iterator();
        while (iter.hasNext()) {
            ContributeContract contributeContract = iter.next();
            String name = new String();
            String time = new String();
            String username = new String();
            name = contributeContract.getName();
            time = contributeContract.getTime().split(" ")[0];
            username = contributeContract.getUserName();
            System.out.println("合同名称:" + name);
            System.out.println("起草时间:" + time);
            System.out.println("起草人:" + username);
            String item = "";
            item = "<tr><td>" + name +
                    "</td><td>" + time +
                    "</td><td>" + username +
                    "</td>" + "<td><button class=\"btn btn-info\" onclick=\"ShowHide(true,shadow,dialog); return false;\">分配</button></td></tr>";
            System.out.println("表格生成的一行：" + item);
            contribute_contract_list += item;
        }

        return contribute_contract_list;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("contribute_contract_list", get_contribute_contract_list());
        req.setAttribute("contribute_person_list", get_contribute_person_list());
        req.getRequestDispatcher("jsp/cont_contribute.jsp").forward(req, resp);
    }

    private String get_contribute_person_list() {
        String contribute_person_list = "";

        Verify verify = new Verify();
        List<String> person_list = verify.show_user();

        Iterator<String> iter = person_list.iterator();
        while (iter.hasNext()) {
            String name = iter.next();
            String item = "";
            item = "<li>" + name + "</li>";
            System.out.println("表格生成的一行：" + item);
            contribute_person_list += item;
        }

        return contribute_person_list;

    }
}
