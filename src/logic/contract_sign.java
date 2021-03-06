package logic;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import Utils.Get_Time;
import com.mysql.jdbc.PreparedStatement;

import static logic.contract_drag.jdbcObject;

public class contract_sign {
	String num;
	String username;
	public contract_sign(String username){
		this.username = username;
	}
	/**
	 * 1.查询该用户能签订的合同名
	 * 2.用户选择一个合同
	 * 3.显示合同具体信息
	 * 4.用户填写签订信息
	 * 5.更新合同过程
	 * 6.若该合同所有签订已完成 更新合同状态
	 */
	public void defaultFind(){
		PreparedStatement pst;
		ResultSet rs;
		String conNum;
		String sql = "select conNum from contract_process where userName = ? and type = 3 and state = 0";
		try {
			pst = (PreparedStatement) controller.connect.prepareStatement(sql);
			pst.setString(1, username);
			System.out.println(pst);
			rs = pst.executeQuery();
			System.out.println("您要签订的合同如下：");
			while(rs.next()){
				String name;
				String customer;
				String beginTime;
				String endTime;

				conNum = rs.getString(1);
				//查询该合同有没有被拒绝
				String sql2 = "select * from contract_process where conNum = ? and state = 2";
				pst = (PreparedStatement) controller.connect.prepareStatement(sql2);
				pst.setString(1, conNum);
				System.out.println(pst);
				rs = pst.executeQuery();
				if(!rs.next()){
					//该合同没有被拒绝
					String sql3 = "select * from contract where num = ?";
					pst = (PreparedStatement) controller.connect.prepareStatement(sql3);
					pst.setString(1, conNum);
					System.out.println(pst);
					rs = pst.executeQuery();
					if(rs.next()){
						System.out.println(rs.getString("name"));
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showSelect(String name){
		PreparedStatement pst;
		ResultSet rs;
		try {
			String sql = "select * from contract where name = ?";
			pst = (PreparedStatement) controller.connect.prepareStatement(sql);
			pst.setString(1, name);
			System.out.println(pst);
			rs = pst.executeQuery();
			if(rs.next()){
				num = rs.getString("num");
				System.out.println("name:"+name);
				System.out.println("beginTime:"+rs.getString("beginTime"));
				System.out.println("endTime:"+rs.getString("endTime"));
				System.out.println("customer:"+rs.getString("customer"));
				System.out.println("content:"+rs.getString("content"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	public void signConstract(String name,String content){
//		PreparedStatement pst;
//		String sql = "update contract_process set content = ?,state = 1 where userName = ? and type = 3";
//		try {
//			pst = (PreparedStatement) controller.connect.prepareStatement(sql);
//			pst.setString(1, content);
//			pst.setString(2, username);
//			int row = pst.executeUpdate();
//			if(row == 1){
//				System.out.println("更新成功！");
//			}else{
//				System.out.println("更新失败！");
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public void insertComment(String conNum, String content) {
		String num;
		//首先查找该合同名对应的合同编号
		String sql1 = "select * from contract where num = \"" + conNum + "\"";
		System.out.println(sql1);
		PreparedStatement pst;
		try {
			pst = (PreparedStatement) jdbcObject.getPrepareStatement(sql1);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				num = rs.getString(1);

				long l = System.currentTimeMillis();
				Date finishtime = new Date(l);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				sdf.format(finishtime);
				String sql2 = "update contract_process set content = ?,time = ?,state = 1,userName = \""+this.username+"\" where conNum = \"" + num + "\"";
				pst = (PreparedStatement) jdbcObject.getPrepareStatement(sql2);
				pst.setString(1, content);
				pst.setDate(2, finishtime);
				System.out.println(pst);
				pst.executeUpdate();
				System.out.println("修改成功(*￣︶￣)");
				Ifcompleted(conNum);

			}
			//插入评论
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("操作失败o(╥﹏╥)");
			e.printStackTrace();
		}
	}




	public boolean Ifcompleted(String conNum){
		boolean result = false;
		PreparedStatement pst;
		ResultSet rs;
		String sql = "select * from contract_process where conNum = ? and type = 3 and state = 0";
		try {
			pst = (PreparedStatement) controller.connect.prepareStatement(sql);
			pst.setString(1, conNum);
			System.out.println(pst);
			rs = pst.executeQuery();
			if(!rs.next()){
				System.out.println("该合同的所有签订已完成！");
				String sql2 = "update contract_state set type = 5,time = ? where num = ?";
				pst = (PreparedStatement) controller.connect.prepareStatement(sql2);
				pst.setTimestamp(1, new java.sql.Timestamp(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(new Get_Time().getCurrentTime()).getTime()));
				pst.setString(2, conNum);
				System.out.println(pst);
				int count = pst.executeUpdate();
				if(count == 1){
					System.out.println("合同状态更新成功o(*￣▽￣*)");
					result = true;
				}else{
					System.out.println("合同状态更新失败(；′⌒`)");
				}
			}else{
				System.out.println("还有部分签订尚未完成，革命尚需努力！");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return result;
		}
	}
}
