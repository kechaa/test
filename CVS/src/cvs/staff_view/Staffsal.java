package cvs.staff_view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import cvs.staff_view.model.StaffsalDAO;

public class Staffsal extends JPanel{
	
	JTextField tfsearchid;
	JButton bsearch;
	JLabel id;
	
	JTable tbsal;
	SalTableModel tbsalmodel;
	
	StaffsalDAO dao;

	public Staffsal(){
		
		addLayout(); // 화면 구성
		connectDB(); // DB연결
		eventProc(); //이벤트 발생시
		
}
	
	/*
	 * 역할: 레이아웃만들기
	 */
	void addLayout(){
		
		tfsearchid = new JTextField(20);
		bsearch = new JButton("검색");
		id = new JLabel("ID");
		
		tbsalmodel = new SalTableModel();
		tbsal = new JTable(tbsalmodel);
		
		//화면구성
		setLayout(new BorderLayout());
		//위쪽
		JPanel north = new JPanel();
			north.add(id);
			north.add(tfsearchid);
			north.add(bsearch);
			
		add(north,BorderLayout.NORTH);
		//아래쪽 테이블 붙이기
		add(new JScrollPane(tbsal),BorderLayout.CENTER);
		
	}
	
	/*
	 * 역할: DB연결하기
	 */
	void connectDB() {
		
		try {
			dao = new StaffsalDAO();
			System.out.println("DB연결성공3");
		} catch (Exception e) {
			System.out.println("DB연결실패3:"+e.toString());
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	/*
	 * 역할: 버튼핸들러랑 연결함
	 */
	void eventProc() {
		ButtonEventHandler hdlr = new ButtonEventHandler();
		bsearch.addActionListener(hdlr);
		
	}
	
	/*
	 * 역할 버튼 핸들러 만들기
	 */
	class ButtonEventHandler implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			Object o = ev.getSource();

			if(o==bsearch){  
				searchsal();
				JOptionPane.showMessageDialog(null, "검색");
			}
			

		}
	}
	/*
	 * 역할: 테이블 모델 만들기 ,컬럼네임 생성
	 */
	class SalTableModel extends AbstractTableModel {

		ArrayList data = new ArrayList();
		String [] columnNames = {"월","급여"};

		public int getRowCount() {
			return data.size();
		}

		public int getColumnCount() {
			return columnNames.length;
		}

		public Object getValueAt(int row, int col) {
			ArrayList temp = (ArrayList)data.get( row );
			return temp.get( col ); 
		}
		public String getColumnName(int col){
			return columnNames[col];
		}

	}
	
	/*
	 * 역할 : 검색버튼 눌렀을 때, 월별 sal 이 나오도록 출력
	 */
	void searchsal(){
		
		String id =tfsearchid.getText();
		
	}

}
