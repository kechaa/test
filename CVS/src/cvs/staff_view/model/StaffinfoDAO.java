package cvs.staff_view.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cvs.staff_view.model.vo.StaffVO;


public class StaffinfoDAO {
	
	Connection con;
	

	public StaffinfoDAO() throws Exception{
		
		//DB연결
		con = DBConstaff.getInstance();
		
	}
	
	public StaffVO search(String searchid) throws Exception {
		StaffVO vo = new StaffVO();
		String sql = "SELECT staffid, staffname, stafftel from staff where staffid =?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, searchid);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			vo.setStaffid(rs.getString("staffid"));
			vo.setStaffname(rs.getString("staffname"));
			vo.setStafftel(rs.getString("stafftel"));
			
		}
		rs.close();
		ps.close();
		
		return vo;
		
	}
	
	public void registstaff(String id, String name, String tel) throws Exception{
		
		String sql = "INSERT INTO staff(staffid, staffname, stafftel) VALUES(?, ? , ?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, id);
		ps.setString(2, name);
		ps.setString(3, tel);
		ps.executeUpdate();
		
		ps.close();
				
		
	} 
	
	public void modify(String id, String name, String tel) throws Exception{
		
		String sql = "UPDATE staff SET staffname=?, stafftel = ? WHERE staffid = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, name);
		ps.setString(2, tel);
		ps.setString(3, id);
		ps.executeUpdate();
		
		ps.close();
		
	}
	
	public void delete(String id) throws Exception{
		
		String sql = "Delete FROM staff WHERE staffid =?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, id);
		ps.executeUpdate();
		
		ps.close();
	}
	
	
	
	

}
