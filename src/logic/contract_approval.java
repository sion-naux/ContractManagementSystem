package logic;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.mysql.jdbc.PreparedStatement;

public class contract_approval {
	/**
	 * 1.查询所有有权利审批的合同
	 * 2.选择一个合同 显示
	 * 3.选择通过不通过 输入审批意见
	 * 4.更新合同状态(若这个人拒绝了合同 合同状态改为已拒绝 若所有人都审批通过 则审批完成)
	 */
	String  userName;
	String num = "";
	public contract_approval(String userName){
		this.userName = userName;
	}

	public void defaultFind(){
		try {
			String conNum;
			String sql = "select conNum from contract_process where type = 2 and state = 0 and userName = ?";
			PreparedStatement ps = (PreparedStatement) controller.connect.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			System.out.println("需要审批的合同有：");
			while(rs.next()){
				conNum = rs.getString(1);
				String sql2 = "select name from contract where num = ? and (select type from contract_state where num = " +"\""+ conNum +"\""+ ")"+" = 3";
				ps = (PreparedStatement) controller.connect.prepareStatement(sql2);
				ps.setString(1,conNum);
				System.out.println(ps);
				rs = ps.executeQuery();
				if(rs.next()){
					//查看合同是否已否决
					String sql3 = "select * from contract_process where conNum = ? and state = 2";
					PreparedStatement ps2 = (PreparedStatement) controller.connect.prepareStatement(sql3);
					ps2.setString(1, conNum);
					System.out.println(ps2);
					ResultSet rs2 = ps2.executeQuery();
					if(!rs2.next()){
						System.out.println(rs.getString(1));
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickOneHetong(String name){
		PreparedStatement pst;
		ResultSet rs;
		try {
			String sql = "select * from contract where name = ?";
			pst = (PreparedStatement) controller.connect.prepareStatement(sql);
			pst.setString(1, name);
			rs = pst.executeQuery();
			if(rs.next()){
				System.out.println("合同编号:"+rs.getString("num"));
				System.out.println("合同名称:"+rs.getString("name"));
				System.out.println("顾客:"+rs.getString("customer"));
				System.out.println("生效时间:"+rs.getString("beginTime"));
				System.out.println("结束时间:"+rs.getString("endTime"));
				System.out.println("起草人:"+rs.getString("userName"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void decide(String name,String ifpass,String content){
		try {
			//1.查找该合同的编号
			String sql = "select num from contract where name = ?";
			PreparedStatement ps = (PreparedStatement) controller.connect.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				num = rs.getString(1);
			}
			//2.更新该操作者状态
			String sql2 = "";
			if(ifpass.equals("n")){
				sql2 = "update contract_process set state = 2,content = ? where conNum = ? and userName = ? and type = 2";
			}else{
				sql2 = "update contract_process set state = 1,content = ? where conNum = ? and userName = ? and type = 2";
			}
			ps = (PreparedStatement) controller.connect.prepareStatement(sql2);
			ps.setString(1, content);
			ps.setString(2, num);
			ps.setString(3, userName);
			System.out.println(ps);
			int count = ps.executeUpdate();
			if(count == 1){
				System.out.println("更新成功！");
			}else{
				System.out.println("更新失败！");
			}
			if(ifpass.equals("y")){
				Ifcomplete();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Ifcomplete(){
		String sql = "select * from contract_process where conNum = ? and type = 2 and state = 0";
		PreparedStatement pst;
		ResultSet rs;
		try {
			pst = (PreparedStatement) controller.connect.prepareStatement(sql);
			pst.setString(1, num);
			System.out.println(pst);
			rs = pst.executeQuery();
			if(!rs.next()){
				System.out.println("所有审批都已完毕！");
				long l = System.currentTimeMillis();
				Date finishtime=new Date(l);
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				sdf.format(finishtime);
				String sql2 = "update contract_state set type = 4,time = ? where num = ?";
				pst.setDate(1, finishtime);
				pst.setString(2, num);
				pst = (PreparedStatement) controller.connect.prepareStatement(sql2);
				pst.executeUpdate();
			}else{
				System.out.println("还有部分审批完成！");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
