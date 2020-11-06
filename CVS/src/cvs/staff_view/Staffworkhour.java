package cvs.staff_view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

import cvs.staff_view.model.StaffinfoDAO;
import cvs.staff_view.model.vo.StaffVO;
import cvs.staff_view.model.StaffworkhourDAO;
import cvs.staff_view.model.vo.StaffworkhourVO;

public class Staffworkhour extends JPanel{

	JTextField tfid, tfgowork, tfleavework, tfdate;
	JButton binsert, bmodify, bsearch;

	StaffVO vo;
	StaffworkhourDAO dao;

	public Staffworkhour(){

		addLayout(); // 레이아웃만들기
		connectDB(); //DB와 연동하기
		eventProc(); //이벤트 처리하기
		
		try {
			tfdate.setText(dao.showtoday());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/*
	 * 역할: DB와 연동하기
	 */
	void connectDB(){
		
		try {
			dao = new StaffworkhourDAO();
			System.out.println("DB연결성공2");
		} catch (Exception e) {
			System.out.println("DB연결실패2:"+e.toString());
			e.printStackTrace();
		}


	}
	
	/*
	 * 역할 : 레이아웃만들기
	 */
	void addLayout(){

		tfid = new JTextField(20);
		tfgowork = new JTextField(20);
		tfleavework = new JTextField(20);
		tfdate = new JTextField(20);

		binsert = new JButton("입력");
		bmodify = new JButton("수정");
		bsearch = new JButton("조회");

		//화면구성
		setLayout(new BorderLayout());
		
		//가운데 부분만들기
		JPanel center = new JPanel(new GridLayout(3,2));

			JLabel jid = new JLabel("ID");
			jid.setHorizontalAlignment(SwingConstants.CENTER);
			jid.setFont(new Font("맑은 고딕", Font.BOLD, 13));
			center.add(jid);
			
			center.add(tfid);
			
			JLabel jgowork = new JLabel("출근시간");
			center.add(jgowork);
			jgowork.setHorizontalAlignment(SwingConstants.CENTER);
			jgowork.setFont(new Font("맑은 고딕", Font.BOLD, 13));
			
			center.add(tfgowork);
			
			JLabel jleavework = new JLabel("퇴근시간");
			center.add(jleavework);
			jleavework.setHorizontalAlignment(SwingConstants.CENTER);
			jleavework.setFont(new Font("맑은 고딕", Font.BOLD, 13));
			
			center.add(tfleavework);
			
		//아래 버튼부분추가하기
		JPanel south = new JPanel(new GridLayout(1,3));
			south.add(binsert);
			binsert.setForeground(new Color(139, 0, 139));
			south.add(bmodify);
			bmodify.setForeground(new Color(139, 0, 139));
			south.add(bsearch);
			bsearch.setForeground(new Color(139, 0, 139));
			
		JPanel north = new JPanel(new BorderLayout());
			north.add(tfdate,BorderLayout.EAST);
		//모두 borderlayout에 추가함
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
		add(north, BorderLayout.NORTH);



	}

	/*
	 * 역할 : 버튼핸들러와 연동하기
	 */
	void eventProc(){
		ButtonEventHandler hdlr = new ButtonEventHandler();
		binsert.addActionListener(hdlr);
		bmodify.addActionListener(hdlr);
		bsearch.addActionListener(hdlr);

	}
	
	/*
	 * 역할: 버튼 핸들러 만들기
	 */
	class ButtonEventHandler implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			Object o = ev.getSource();

			if(o==binsert){  
				inserthour();
				JOptionPane.showMessageDialog(null, "입력되었습니다.");
				clear();
			}
			else if(o==bmodify){  
				modifyhour();
				JOptionPane.showMessageDialog(null, "수정되었습니다.");
				clear();
			}
			else if(o==bsearch){  
				searchhour();
				
			}
			

		}
	}
	//시간 입력
	void inserthour() {
		
		String id = tfid.getText();
		String gowork = tfgowork.getText();
		String leavework = tfleavework.getText();
		
		try {
			dao.insert(id,gowork,leavework);
		} catch (Exception e) {
			System.out.println("시간입력실패:"+e.toString());
			e.printStackTrace();
		}
		
	}
	
	//시간 수정
	void modifyhour() {
		String id = tfid.getText();
		String gowork = tfgowork.getText();
		String leavework = tfleavework.getText();
		
		try {
			dao.modify(id,gowork,leavework);
		} catch (Exception e) {
			System.out.println("시간수정실패:"+e.toString());
			e.printStackTrace();
		}
		
		
	}
	
	//화면지우는 함수
	void clear() {
		tfgowork.setText(null);
		tfid.setText(null);
		tfleavework.setText(null);
		
	}
	
	void searchhour() {
		
		String id = tfid.getText();
		
		
		try {
			StaffworkhourVO  vo = dao.searchhour(id);
			tfgowork.setText(vo.getGowork());
			tfleavework.setText(vo.getLeavework());
			
		} catch (Exception e) {
			System.out.println("시간조회실패:"+e.toString());
			e.printStackTrace();
		}
		
	}
	
	
	
	
}
