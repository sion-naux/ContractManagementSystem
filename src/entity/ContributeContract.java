package entity;

public class ContributeContract {
    private String num;//合同编号
    private String name;//合同名称
    private String userName;//起草人
    private String time;//起草时间

    public ContributeContract(String num,String name, String userName, String time) {
        this.num = num;
        this.name = name;
        this.userName = userName;
        this.time = time;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
