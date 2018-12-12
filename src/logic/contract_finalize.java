package logic;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;

public class contract_finalize {
	/**
	 * 1.找到该用户尚未完成定稿的合同 显示合同标题让用户选要定稿的合同
	 * 2.用户选择一个要定稿的合同 系统显示合同名称 客户 开始时间 结束时间 合同内容 会签意见
	 * 3.用户更新合同内容
	 * 4.更新合同状态
	 */
	String userName;
	public contract_finalize(String userName){
		this.userName = userName;
	}

	public void defaultFind(){
		try {
			ResultSet rs;
			String sql = "select name from contract where userName = ? and (select "
					+ "type from contract_state state where state.num = contract.num) = 2";
			PreparedStatement pst = (PreparedStatement) controller.connect.prepareStatement(sql);
			pst.setString(1,userName);
			System.out.println(pst);
			rs = pst.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String selectHetong(String name){
		String num;
		String sql2;
		String customer;
		Date beginTime;
		Date endTime;
		String content;
		PreparedStatement pst;
		ResultSet rs;
		try{
			sql2 = "select num,customer,beginTime,endTime,content from contract where name = \""+name+"\"";
			pst = (PreparedStatement) controller.connect.prepareStatement(sql2);
			rs = pst.executeQuery();
			if(rs.next()){
				num = rs.getString(1);
				customer = rs.getString(2);
				beginTime = rs.getDate(3);
				endTime = rs.getDate(4);
				content = rs.getString(5);
				System.out.println("name:"+name+"\n"+"customer:"+customer+"\n"+"beginTime:"+beginTime+"\n"
						+"endTime:"+endTime+"\n"+"content"+content);
				String sql3 = "select content from contract_process where conNum = \""+num+"\"";
				pst = (PreparedStatement) controller.connect.prepareStatement(sql3);
				rs = pst.executeQuery();
				while(rs.next()){
					System.out.println(rs.getString(1));
				}
				return num;
			}
		}catch(SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "0";
	}

	public void updatecontent(String content,String name){
		String sql4;
		PreparedStatement pst;
		sql4 = "update contract set content = \""+content+"\"where name = \""+name+"\"";
		try {
			pst = (PreparedStatement) controller.connect.prepareStatement(sql4);
			int row=pst.executeUpdate();//执行更新操作，返回所影响的行数
			if(row == 1){
				System.out.println("更新内容成功!");
			}else{
				System.out.println("更新内容失败!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("更新报错!");
			e.printStackTrace();
		}
	}

	public void updateState(String num){
		PreparedStatement pst;
		ResultSet rs;
		String sql5 = "update contract_state set type = 3 where num = \""+num+"\"";
		try {
			pst = (PreparedStatement) controller.connect.prepareStatement(sql5);
			int row=pst.executeUpdate();
			if(row == 1){
				System.out.println("已更新合同状态!");
			}else{
				System.out.println("合同状态更新失败!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("合同状态更新错误!");
			e.printStackTrace();
		}
	}
}

