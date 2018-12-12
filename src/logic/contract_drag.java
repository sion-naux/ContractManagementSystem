package logic;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import com.mysql.jdbc.PreparedStatement;

public class contract_drag {
	String num;
	String name;
	String customer;
	Date beginTime;
	Date endTime;
	String text;
	String userName;
	public contract_drag(String name,String customer,Date beginTime,
						 Date endTime,String text,String userName){
		this.name = name;
		this.customer = customer;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.text = text;
		this.userName = userName;
	}

	public void judge(){
		//判断是否存在
	}
	public void insertcontract(){
		String sql="insert into contract values(?,?,?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) controller.connect.prepareStatement(sql);
			num = (int)(1+Math.random()*(1000000))+"";
			ps.setString(1,num);
			ps.setString(2,name);
			ps.setString(3,customer);
			ps.setDate(4,beginTime);
			ps.setDate(5,endTime);
			ps.setString(6,text);
			ps.setString(7,userName);
			int row=ps.executeUpdate();//执行更新操作，返回所影响的行数
			if(row == 0){
				System.out.println("插入失败!");
			}else{
				insertcontract_state();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void insertcontract_state(){
		String sql="insert into contract_state values(?,?,?)";
		PreparedStatement ps;
		try {

			//获取当前时间
			long l = System.currentTimeMillis();
			Date finishtime=new Date(l);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			sdf.format(finishtime);
			ps = (PreparedStatement) controller.connect.prepareStatement(sql);
			ps.setString(1, num);
			ps.setInt(2, 1);
			ps.setDate(3,finishtime);
			int row=ps.executeUpdate();//执行更新操作，返回所影响的行数
			if(row == 1){
				System.out.println("插入成功!");
			}else{
				System.out.println("插入失败!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}