package servlet;

import entity.Attachment;
import logic.Download;
import net.sf.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class DownloadServlet extends HttpServlet {

    public static final String ATTACHMENT_ROOT_PATH = "upload/";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Attachment attachment = Download.getInstance().getAttachment(request.getParameter("cont_num"));
        response.setContentType("application/x-download");
        response.addHeader("Content-Disposition", "attachment;filename="
                + attachment.getFileName());
        try {
            String s= ATTACHMENT_ROOT_PATH + attachment.getFileName();
            RequestDispatcher rd = request.getRequestDispatcher(ATTACHMENT_ROOT_PATH + attachment.getFileName());
            if (rd != null)
                rd.forward(request, response);
            response.flushBuffer();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
