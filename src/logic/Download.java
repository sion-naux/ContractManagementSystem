package logic;

import entity.Attachment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static logic.contract_drag.jdbcObject;

public class Download {

    public static Download download = null;
    private Attachment attachment = null;

    public static Download getInstance(){
        if(download == null)
            download = new Download();
        return download;
    }


    private Download(){}

    //获取附件对象
    public Attachment getAttachment(String cont_num){

        //查询所用sql语句
        String sql = "select * from contract_attachment where conNum = \"" + cont_num + "\"";
        PreparedStatement ps;
        try{
            ps = jdbcObject.getPrepareStatement(sql);
            //执行查询操作
            ResultSet rs = ps.executeQuery(sql);
            //若存在结果，表示该合同有附件
            if(rs.next()){
                attachment = new Attachment();
                attachment.setConNum(cont_num);
                attachment.setFileName(rs.getString(2));
                attachment.setPath(rs.getString(3));
                attachment.setType(rs.getString(4));
                attachment.setUploadTime(rs.getDate(5));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return attachment;
    }




}
