package cvs.staff_view.model;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBConstaff {
	static Connection	con;
	String url = "jdbc:oracle:thin:@192.168.0.19:1521:orcl";
	String user = "project8";
	String pass = "4321";

	private DBConstaff() throws Exception {

		/*
		1. 드라이버를 드라이버메니저에 등록
		2. 연결 객체 얻어오기
		 */
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url, user, pass);

	}
	
	public static Connection getInstance() throws Exception { // new로 클래스를 할당하지 않아도 함수를 사용할 수 있는 static
		if(con==null) new DBConstaff();   //con이 null일 경우만 객체생성
		return con;  //con이 null이 아니면  기존의 con을 호출함
	}


}
