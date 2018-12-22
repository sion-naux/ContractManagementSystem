package entity;

public class Log {
    private String userName;
    private String content;
    private String time;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Log(String userName, String content, String time) {
        this.userName = userName;
        this.content = content;
        this.time = time;
    }
}
