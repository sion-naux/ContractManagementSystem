package logic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.mysql.jdbc.Statement;

public class controller {
    public static Connection connect;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //连接数据库
        controller control = new controller();
        control.connect();
        while (true) {
            System.out.println("输入操作名：");
            Scanner scan = new Scanner(System.in);
            int operate = scan.nextInt();
            scan.nextLine();
            switch (operate) {
                case 0: {
                    System.out.println("Please enter contract name:");
                    String name = scan.nextLine();
                    System.out.println("Please enter customer:");
                    String customer = scan.nextLine();
                    Date beginTime = new Date(118, 3, 2);//自1900-1-1之后过去的年月日
                    Date endTime = new Date(119, 5, 4);
                    System.out.println("Please enter text:");
                    String text = scan.nextLine();
                    System.out.println("Please enter userName:");
                    String userName = scan.nextLine();
                    contract_drag drag = new contract_drag(name, customer, beginTime, endTime, text, userName);
                    drag.insertcontract();//添加合同信息
                    break;
                }
                case 1: {
                    String userName = "小赵";
                    contract_countersign countersign = new contract_countersign(userName);
                    countersign.defaultFind();//查询该用户要会签合同
                    System.out.println("输入要会签的合同名：");
                    String name = scan.nextLine();
                    countersign.clickOneHetong(name);
//                    countersign.insertComment(name);
                    break;
                }
                case 2: {
                    String userName = "小李";
                    contract_finalize finalize = new contract_finalize(userName);
                    finalize.defaultFind();//查询该用户要会签合同
                    System.out.println("please select one hetong:");
                    Scanner in = new Scanner(System.in);
                    String name = in.nextLine();
                    String num = finalize.selectHetong(name);
                    System.out.println("输入新的合同内容");
                    String newContent = in.nextLine();
                    finalize.updatecontent(newContent, name);
                    finalize.updateState(num);
                    break;
                }
                case 3: {
                    String userName = "最可爱的毛毛球";
                    contract_approval approval = new contract_approval(userName);
                    approval.defaultFind();
                    System.out.println("请输入要审批的合同名：");
                    String name = scan.nextLine();
                    approval.clickOneHetong(name);
                    System.out.println("是否通过:输入y/n");
                    String ifpass = scan.nextLine();
                    System.out.println("输入评论意见：");
                    String content = scan.nextLine();
                    if (content == "") {
                        System.out.println("合同意见不能为空！");
                        break;
                    }
                    approval.decide(name, ifpass, content);
                    break;
                }
                case 4: {
                    String userName = "是签订毛毛球呀";
                    contract_sign sign = new contract_sign(userName);
                    sign.defaultFind();
                    System.out.println("请输入要会签的合同名：");
                    String name = scan.nextLine();
                    System.out.println("请输入签订信息:");
                    String content = scan.nextLine();
                    if (content == "") {
                        System.out.println("合同意见不能为空！");
                        break;
                    }
                    sign.showSelect(name);
                    sign.signConstract(name, content);
                    sign.Ifcompleted();
                }
            }
        }

    }

    public void connect() {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hetong"
                            + "?useUnicode=true&characterEncoding=utf-8&useSSL=false", "root", "zyt19981014");
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
