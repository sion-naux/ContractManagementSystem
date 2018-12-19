package Utils;

import entity.ContributeContract;
import logic.contract_drag;

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
            {"起草","会签","审批","签订","分配","定稿"};
    public static String[] METHOD_NAME = new String[]
            {"draft","countersign","approval","conclude","contribute","final",
                    "over_countersign","over_approval","over_conclude","over_contribute","over_final"};

    public String get_contract_list(int type, int state,String client) {
        String contribute_contract_list = "";
        List<ContributeContract> contribute_list;
        contribute_list = contract_drag.show_contract(type, state, client);
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
            String btnName = "";
            String methodName = "";
            if(state == 0){
                btnName = STATIONS[state];
                methodName = METHOD_NAME[state];
            }
            else if(state == 1){
                btnName = "查看";
                methodName = METHOD_NAME[STATIONS.length + type - 1];
            }

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

}
