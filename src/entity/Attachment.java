package entity;

import java.sql.Date;

public class Attachment {


    private String conNum;
    private String fileName;
    private String path;
    private String type;
    private Date uploadTime;

    public Attachment(String conNum, String fileName, String path,
                      String type, Date uploadTime){
        this.conNum = conNum;
        this.fileName = fileName;
        this.path = path;
        this.type = type;
        this.uploadTime = uploadTime;
    }

    public Attachment(){}

    public String getConNum() {
        return conNum;
    }

    public void setConNum(String conNum) {
        this.conNum = conNum;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }


}
