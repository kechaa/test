package cvs.staff_view.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import cvs.staff_view.model.DBConstaff;
import cvs.staff_view.model.vo.StaffworkhourVO;

public class StaffworkhourDAO {

	Connection con;
	StaffworkhourVO vo;

	public StaffworkhourDAO() throws Exception{

		//DB연결
		con = DBConstaff.getInstance();


	}

	public String showtoday() throws Exception{
		
		String date = null;

		String sql = "SELECT 'Today : '|| to_char(SYSDATE,'yyyy\"년\"mm\"월\"dd\"일\"') dday FROM dual";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if(rs.next()) {
			date = rs.getString("dday");
		}
		return date;
	}

	public void insert(String id, String gowork, String leavework) throws Exception{
		
		String sql = "INSERT INTO workhour(staffid, gowork, leavework)\r\n" + 
				" values(?, \r\n" + 
				" to_date(?,'yyyy/mm/dd hh24:mi'), \r\n" + 
				" to_date(?,'yyyy/mm/dd hh24:mi'))";
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, id);
		st.setString(2, gowork);
		st.setString(3, leavework);
		
		st.executeUpdate();
		st.close();
		
		
		
	}
	
	public void modify(String id, String gowork, String leavework) throws Exception{
		
		String sql = "UPDATE workhour set gowork = to_date(?,'yyyy/mm/dd hh24:mi'), "
				+ " leavework =to_date(?,'yyyy/mm/dd hh24:mi') where staffid =?";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, gowork);
		st.setString(2, leavework);
		st.setString(3, id);
		
		st.executeUpdate();
		st.close();
	}
	
	public StaffworkhourVO searchhour(String id) throws Exception{
		
		vo = new StaffworkhourVO();
		
		String sql = "Select to_char(gowork,'yyyy/mm/dd hh24:mi') gowork, to_char(leavework,'yyyy/mm/dd hh24:mi') leavework\r\n" + 
				" from workhour\r\n" + 
				" where staffid=? \r\n" + 
				" and to_char(sysdate,'YYMMDD')=to_char(gowork,'yymmdd')";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, id);
		
		
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			vo.setGowork(rs.getString("gowork"));
			vo.setLeavework(rs.getString("leavework"));
		}
		
		return vo;
		
	}


}
