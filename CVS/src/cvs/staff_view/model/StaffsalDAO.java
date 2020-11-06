package cvs.staff_view.model;

import java.sql.Connection;

public class StaffsalDAO {

	Connection con;

	public StaffsalDAO() throws Exception {
		
		con = DBConstaff.getInstance();

	}
	
	
}
