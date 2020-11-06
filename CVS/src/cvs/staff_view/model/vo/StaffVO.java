package cvs.staff_view.model.vo;

public class StaffVO {
	
	//테이블 구조에 맞는 레코드 클래스
	String staffid;
	String staffname;
	String stafftel;
	String staffpw;
	
	//생성자 함수
	public StaffVO() {
		
	}

	//getter, setter
	public String getStaffid() {
		return staffid;
	}

	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}

	public String getStaffname() {
		return staffname;
	}

	public void setStaffname(String staffname) {
		this.staffname = staffname;
	}

	public String getStafftel() {
		return stafftel;
	}

	public void setStafftel(String stafftel) {
		this.stafftel = stafftel;
	}

	public String getStaffpw() {
		return staffpw;
	}

	public void setStaffpw(String staffpw) {
		this.staffpw = staffpw;
	}
	
	
	
	
	

}
