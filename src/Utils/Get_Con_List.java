package Utils;

import entity.ContributeContract;
import logic.contract_drag;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


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
        String contribute_contract_list = "";
        List<ContributeContract> contribute_list;
        String btnName = "";
        String methodName = "";
        if(process_state == 0){
            btnName = STATIONS[state_type];
            methodName = METHOD_NAME[state_type];
        }
        else if(process_state == 1){
            btnName = "查看";
            methodName = METHOD_NAME[STATIONS.length + state_type - 2];
        }
        contribute_list = show_contract(process_type, process_state, client,state_type);
        Iterator<ContributeContract> iter = contribute_list.iterator();
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
            contribute_contract_list += item;
        }

        return contribute_contract_list;
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


}
