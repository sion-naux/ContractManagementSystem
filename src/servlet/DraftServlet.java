package servlet;

import Utils.Get_Time;
import entity.Attachment;
import entity.CurrentUser;
import entity.Log;
import logic.LogManage;
import logic.contract_drag;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.DeferredFileOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class DraftServlet extends HttpServlet {
    //上传文件存储路径
    private static final String UPLOAD_DIRECTORY = "upload";
    // 上传配置
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String contract_name = null;
        String contract_customer = null;
        String contract_begin_time = null;
        String contract_end_time = null;
        String contract_content = null;

        Attachment attachment = null;

        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // 中文处理
        upload.setHeaderEncoding("UTF-8");

        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录

        String i = getParentPath(this.getClass().getResource(".").getPath(), 6);
        String uploadPath = getParentPath(this.getClass().getResource(".").getPath(), 6) + "/web/" + UPLOAD_DIRECTORY;

        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        if (fileName.equals(""))
                            continue;
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);

                        attachment = new Attachment();
                        attachment.setFileName(fileName);
                        attachment.setPath(uploadPath);
                        attachment.setType(item.getContentType());
                        // 在控制台输出文件的上传路径
                        System.out.println(filePath);
                        // 保存文件到硬盘
                        item.write(storeFile);
                        request.setAttribute("message",
                                "文件上传成功!");
                    } else {
                        String fieldName = item.getFieldName();
                        String str = item.getString("UTF-8");

                        switch (fieldName) {
                            case "contract_name":
                                contract_name = str;
                                break;
                            case "contract_customer":
                                contract_customer = str;
                                break;
                            case "contract_begin_time":
                                contract_begin_time = str;
                                break;
                            case "contract_end_time":
                                contract_end_time = str;
                                break;
                            case "contract_content":
                                contract_content = str;
                                break;
                        }


                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message",
                    "错误信息: " + ex.getMessage());
        }

        contract_drag drag = new contract_drag(contract_name, contract_customer,
                parseDate(contract_begin_time), parseDate(contract_end_time),
                contract_content, CurrentUser.username, attachment);
        drag.insertcontract();
        LogManage.insert_log(new Log(CurrentUser.username, "起草合同：" + contract_name, new Get_Time().getCurrentTime()));
        response.sendRedirect(request.getContextPath() + "/draft");
    }

    private String getParentPath(String path, int num) {
        String s = path;
        for (int i = 0; i < num; i++) {
            File f = new File(s);
            s = f.getParent();
        }
        return s;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("right_list", CurrentUser.right_list);
        request.getRequestDispatcher("jsp/draft.jsp").forward(request, response);
    }

    private java.sql.Date parseDate(String str) {
        str = str.replaceAll("/", "-");
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
