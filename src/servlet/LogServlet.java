package servlet;

import entity.CurrentUser;
import entity.Log;
import logic.LogManage;
import logic.contract_info_search;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String log_list = "";
        List<Log> list;
        list = LogManage.getLogList();
        Iterator<Log> iterator = list.iterator();
        while (iterator.hasNext()) {
            Log log = iterator.next();
            String userName = log.getUserName();
            String content = log.getContent();
            String time = log.getTime();
            String item = "";
            item = "<tr><td>" + userName +
                    "</td><td>" + content +
                    "</td><td>" + time +
                    "</td></tr>";
            log_list += item;
        }

        req.setAttribute("default_list", log_list);
        req.setAttribute("right_list", CurrentUser.right_list);
        req.getRequestDispatcher("jsp/log.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
