package cvs.cvs_8;

import javax.swing.*;

import cvs.staff_view.Staffinfo;
import cvs.staff_view.Staffsal;
import cvs.staff_view.Staffworkhour;


public class Staff extends JPanel{

	Staffinfo stinfo;
	Staffsal stsal;
	Staffworkhour stworkhour;

	public Staff(){

		stinfo = new Staffinfo();
		stsal = new Staffsal();
		stworkhour = new Staffworkhour();

		JTabbedPane  pane = new JTabbedPane();

		pane.addTab("기본정보", stinfo );
		pane.addTab("출퇴근관리", stworkhour);
		pane.addTab("급여관리", stsal );
		

		pane.setSelectedIndex(0);

		add("Center", pane );
		setSize(900,600);
		setVisible( true );






	}

}
