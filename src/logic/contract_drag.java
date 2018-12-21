package logic;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Utils.Get_Time;
import com.mysql.jdbc.PreparedStatement;
import database.JDBCFacade;
import database.dbConfig;

import entity.Attachment;
import entity.ContributeContract;

public class contract_drag {
    private String num;
    private String name;
    private String customer;
    private Date beginTime;
    private Date endTime;
    private String text;
    private String userName;
    private Attachment attachment;
    public static JDBCFacade jdbcObject = new JDBCFacade();

    static{
        jdbcObject.open(dbConfig.driverName, dbConfig.newjdbcUrl, dbConfig.userName, dbConfig.userPwd);
    }

    public contract_drag(String name, String customer, Date beginTime,
                         Date endTime, String text, String userName, Attachment attachment) {
        this.name = name;
        this.customer = customer;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.text = text;
        this.userName = userName;
        this.num = (int) (1 + Math.random() * (1000000)) + "";
        this.attachment = attachment;
    }

    public contract_drag(String name, String customer, Date beginTime,
                         Date endTime, String text, String userName) {
        this.name = name;
        this.customer = customer;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.text = text;
        this.userName = userName;
        this.num = (int) (1 + Math.random() * (1000000)) + "";
    }

    public void judge() {
        //判断是否存在
    }

    public boolean insertcontract() {

        //新添代码
        boolean result = false;
        String sql = "insert into contract values(?,?,?,?,?,?,?)";
        PreparedStatement ps;
        try {
//			ps = (PreparedStatement) controller.connect.prepareStatement(sql);
            ps = (PreparedStatement) jdbcObject.getPrepareStatement(sql);

            ps.setString(1, num);
            ps.setString(2, name);
            ps.setString(3, customer);
            ps.setDate(4, beginTime);
            ps.setDate(5, endTime);
            ps.setString(6, text);
            ps.setString(7, userName);
            int row = ps.executeUpdate();//执行更新操作，返回所影响的行数
            if (row == 0) {
                System.out.println("插入失败!");
            } else {
                result = insertcontract_state();
                result = insertcontract_process();

                if(attachment != null)
                    add_attactment();

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            return result;
        }


//		String sql="insert into contract values(?,?,?,?,?,?,?)";
//		PreparedStatement ps;
//		try {
////		ps = (PreparedStatement) controller.connect.prepareStatement(sql);
//			ps = (PreparedStatement) jdbcObject.prepareStatement(sql);
//			num = (int)(1+Math.random()*(1000000))+"";
//			ps.setString(1,num);
//			ps.setString(2,name);
//			ps.setString(3,customer);
//			ps.setDate(4,beginTime);
//			ps.setDate(5,endTime);
//			ps.setString(6,text);
//			ps.setString(7,userName);
//			int row=ps.executeUpdate();//执行更新操作，返回所影响的行数
//			if(row == 0){
//				System.out.println("插入失败!");
//			}else{
//				insertcontract_state();
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

    }

    private void add_attactment() {

        String sql = "insert into contract_attachment values(?,?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = (PreparedStatement) jdbcObject.getPrepareStatement(sql);

            ps.setString(1, this.num);
            ps.setString(2, attachment.getFileName());
            ps.setString(3, attachment.getPath());
            ps.setString(4, attachment.getType());
            ps.setDate(5, new Get_Time().getCurrentTime());

            int row = ps.executeUpdate();//执行更新操作，返回所影响的行数
            if (row == 0)
                System.out.println("插入失败!");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    //列出待分配合同
    //合同名称 起草日期 起草人
    public static List<ContributeContract> show_contribute_contract() {
        List<ContributeContract> contract_state_list = new ArrayList<ContributeContract>();
        try {
            String sql = "select contract_state.num as num,name,userName,contract_state.time as time from contract_state,contract where contract_state.num=contract.num and type=1";
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

    //列出待分配合同
    //合同名称 起草日期 起草人
    public static List<ContributeContract> show_contract(int type, int state, String client) {
        List<ContributeContract> contract_state_list = new ArrayList<ContributeContract>();
        try {

            String sql = "select contract_state.num as num,name,contract.userName,contract_state.time as time from contract_state,contract,contract_process where contract_state.num=contract.num and contract_state.num=contract_process.conNum and state=" + state + " and contract_process.userName=\"" + client + "\" and contract_state.type=";
            sql = sql + type;
            System.out.println("生成的sql语句是：" + sql);
            ResultSet rs = jdbcObject.executeQuery(sql);
            while (rs.next()) {
                contract_state_list.add(new ContributeContract(rs.getString("num"), rs.getString("name"), rs.getString("userName"), rs.getString("time")));
            }
//            jdbcObject.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contract_state_list;
    }


    public boolean insertcontract_state() {
        boolean result = false;
        String sql = "insert into contract_state values(?,?,?)";
        PreparedStatement ps;
        try {

            //获取当前时间
            Date finishtime = new Get_Time().getCurrentTime();

            ps = (PreparedStatement) jdbcObject.getPrepareStatement(sql);
//			ps = (PreparedStatement) controller.connect.prepareStatement(sql);
            ps.setString(1, num);
            ps.setInt(2, 1);
            ps.setDate(3, finishtime);
            int row = ps.executeUpdate();//执行更新操作，返回所影响的行数
            if (row == 1) {
                System.out.println("插入成功!");
                result = true;
            } else {
                System.out.println("插入失败!");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            return result;
        }

    }

    public boolean insertcontract_process() {
        boolean result = false;
        String sql = "insert into contract_process values(?,?,?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = (PreparedStatement) jdbcObject.getPrepareStatement(sql);
//			ps = (PreparedStatement) controller.connect.prepareStatement(sql);
            ps.setString(1, num);
            ps.setInt(2, 1);
            ps.setInt(3, 0);
            ps.setString(4, null);
            ps.setString(5, null);
            ps.setDate(6, null);
            int row = ps.executeUpdate();//执行更新操作，返回所影响的行数
            if (row == 1) {
                System.out.println("插入成功!");
                result = true;
            } else {
                System.out.println("插入失败!");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            return result;
        }

    }




}
