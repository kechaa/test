package cvs.cvs_8;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class CvsView extends JFrame{
	
	
	Cal cal;
	Staff staff;
	Stock stock;
	Income income;
	
	public CvsView() {
		super("GS25");
		cal = new Cal();
		staff = new Staff();
		stock = new Stock();
		income = new Income();
		
		JTabbedPane  pane = new JTabbedPane();
		
		
		pane.addTab("계산", cal );
		pane.addTab("알바관리", staff);
		pane.addTab("재고관리", stock );
		pane.addTab("매출관리", income );
		
		pane.setSelectedIndex(0);
		
		add("Center", pane );
		setSize(1000,700);
		setVisible( true );

		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );	
	}
	

	public static void main(String[] args) {
		
		new CvsView();

	}

}
