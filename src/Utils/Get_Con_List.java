package Utils;

import com.mysql.jdbc.PreparedStatement;
import entity.ContributeContract;
import logic.contract_drag;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static logic.contract_drag.jdbcObject;


public class Get_Con_List {

    private static Get_Con_List get_con_list;

    private Get_Con_List(){

    }

    public static Get_Con_List getInstance(){
        if (get_con_list == null)
            get_con_list = new Get_Con_List();
        return get_con_list;
    }

    public static String[] STATIONS = new String[]
            {"起草","会签","定稿","审批","签订"};
    public static String[] METHOD_NAME = new String[]
            {"draft","countersign","final","approval","conclude",
                    "over_countersign","over_final","over_approval","over_conclude"};

    public String get_contract_list(int process_type, int process_state,String client, int state_type) {
        String contract_list = "";
        List<ContributeContract> list = null;
        String btnName = "";
        String methodName = "";

        if (process_state == 0) {
            btnName = STATIONS[state_type];
            methodName = METHOD_NAME[state_type];
        } else if (process_state == 1) {
            btnName = "查看";
            methodName = METHOD_NAME[STATIONS.length + state_type - 1];
        }
        list = show_contract(process_type, process_state, client, state_type);
        contract_list = getContractList(list,methodName,btnName);
        return contract_list;
    }

    //列出待分配合同
    //合同名称 起草日期 起草人
    public static List<ContributeContract> show_contract(int process_type, int process_state, String client, int state_type) {
        List<ContributeContract> contract_state_list = new ArrayList<ContributeContract>();
        try {
            String sql = null;
            if(process_state == 0)
                sql = "select contract_state.num as num,name,contract.userName,contract_state.time as time from contract_state,contract,contract_process where contract_state.num=contract.num and contract_state.num=contract_process.conNum and state=" + process_state + " and contract_process.userName=\"" + client + "\"  and contract_process.type=" + process_type + " and contract_state.type=" + state_type;
            else if(process_state == 1)
                sql = "select contract_state.num as num,name,contract.userName,contract_state.time as time from contract_state,contract,contract_process where contract_state.num=contract.num and contract_state.num=contract_process.conNum and state=" + process_state + " and contract_process.userName=\"" + client + "\"  and contract_process.type=" + process_type + " and contract_state.type>=" + state_type;
            System.out.println("生成的sql语句是：" + sql);
            ResultSet rs = contract_drag.jdbcObject.executeQuery(sql);
            while (rs.next()) {
                contract_state_list.add(new ContributeContract(rs.getString("num"),rs.getString("name"),rs.getString("userName"),rs.getString("time")));
            }
//            jdbcObject.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contract_state_list;
    }


    /*
         定稿操作另起一个函数
         原因：头晕
     */

    public String get_final_contract_list(int process_state, String client, int state_type) {
        String contract_list = "";
        List<ContributeContract> list = null;
        String btnName = "";
        String methodName = "";

        //根据状态码判断是待定稿还是已定稿
        //0：待定稿
        //1：已定稿
        if (process_state == 0) {
            btnName = "定稿";
            methodName = "final_cont";
        } else if (process_state == 1) {
            btnName = "查看";
            methodName = "final_cont";
        }

        //获取定稿相关列表
        list = show_final_contract(process_state, client, state_type);
        contract_list = getContractList(list,methodName,btnName);
        return contract_list;

    }

    //只负责查询定稿列表
    public static List<ContributeContract> show_final_contract(int status, String client, int state_type) {
        List<ContributeContract> contract_state_list = new ArrayList<ContributeContract>();
        try {
            String sql = null;
            if(status == 0)
                sql = "select contract_state.num as num, name, userName,contract_state.time as time from contract_state,contract where contract_state.num=contract.num and userName=\"" + client + "\" and type=" + state_type;
            else if(status == 1)
                sql = "select contract_state.num as num, name, userName,contract_state.time as time from contract_state,contract where contract_state.num=contract.num and userName=\"" + client + "\" and type>=" + state_type;
            System.out.println("生成的sql语句是：" + sql);
            ResultSet rs = jdbcObject.executeQuery(sql);
            while (rs.next()) {
                contract_state_list.add(new ContributeContract(rs.getString("num"),rs.getString("name"),rs.getString("userName"),rs.getString("time")));
            }
//            jdbcObject.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contract_state_list;
    }


    public ArrayList<String> find_final_Cont_Info(String conNum, int type, String client) {
        //只适用于定稿合同信息查询
        String customer = null;
        String content = null;
        ArrayList<String> list = new ArrayList<>();
        //首先查找该合同名对应的合同编号
        String sql1 = "select customer,beginTime,endTime,content from contract,contract_state where contract.num=contract_state.num and contract.num = \"" + conNum + "\" and type = " + type + " and userName = \"" + client + "\"";
        System.out.println(sql1);
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) jdbcObject.getPrepareStatement(sql1);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                //将相关信息放入容器中
                list.add(rs.getString(1));
                list.add(rs.getDate(2).toString());
                list.add(rs.getDate(3).toString());
                list.add(rs.getString(4));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("操作失败o(╥﹏╥)");
            e.printStackTrace();
        }
        return list;
    }

    public void Change_final_status(String conNum){
        //通过合同编号将合同从会签完成改为定稿完成
        String sql = "update contract_state set type = 3,time = ? where num = ?";
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) jdbcObject.getPrepareStatement(sql);
            pst.setDate(1, new Get_Time().getCurrentTime());
            pst.setString(2, conNum);
            pst.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("操作失败o(╥﹏╥)");
            e.printStackTrace();
        }
    }


    public String find_Cont_Info(String conNum, int type, int state, String client) {
        //查找合同内容
        String content = null;
        //首先查找该合同名对应的合同编号
        String sql1 = "select content from contract_process where conNum = \"" + conNum + "\" and type = " + type + " and state = " + state + " and userName = \"" + client + "\"";
        System.out.println(sql1);
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) jdbcObject.getPrepareStatement(sql1);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                content = rs.getString(1);

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("操作失败o(╥﹏╥)");
            e.printStackTrace();
        }
        return content;
    }

    public String getContractList(List<ContributeContract> list, String methodName, String btnName){

        String contract_list = "";
        Iterator<ContributeContract> iter = list.iterator();
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

            item = "<tr id=\""+num+"\"><td>" + num +
                    "</td><td>" + name +
                    "</td><td>" + time +
                    "</td><td>" + username +
                    "</td>" + "<td><button class=\"btn btn-info \" onclick=\""+methodName+"(this)\">"+btnName+"</button></td></tr>";
            System.out.println("表格生成的一行：" + item);
            contract_list += item;
        }

        return contract_list;
    }



}
