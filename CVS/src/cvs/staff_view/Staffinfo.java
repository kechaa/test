package cvs.staff_view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import cvs.staff_view.model.StaffinfoDAO;
import cvs.staff_view.model.vo.StaffVO;



public class Staffinfo extends JPanel{
	
	JTextField tfstaffid, tfstaffname, tfstafftel, tfsearchid;
	JButton registstaff, modifystaff, search, deletestaff;
	
	StaffVO vo;
	StaffinfoDAO dao;
	
	public Staffinfo(){
		
		addLayout();
		connectDB();
		eventProc();
		
}
	void connectDB() {
		try {
			dao = new StaffinfoDAO();
			System.out.println("DB연결성공");
		} catch (Exception e) {
			System.out.println("DB연결실패:"+e.toString());
			e.printStackTrace();
		}
		
	}
	
	void addLayout() {
		
		tfsearchid = new JTextField(20);
		tfstaffname = new JTextField();
		tfstafftel = new JTextField();
		tfstaffid = new JTextField();
		
		registstaff = new JButton("직원등록");
		modifystaff = new JButton("직원수정");
		deletestaff = new JButton("직원삭제");
		search = new JButton("검색");
		
		//화면구성
		setLayout(new BorderLayout());
		//가운데
		JPanel center = new JPanel(new BorderLayout());
			JPanel centercenter = new JPanel(new GridLayout(3,2));
				centercenter.add(new JLabel("직원 ID"));
				
				centercenter.add(tfstaffid);
				centercenter.add(new JLabel("이 름"));
				centercenter.add(tfstaffname);
				centercenter.add(new JLabel("전화번호"));
				centercenter.add(tfstafftel);
			center.add(centercenter, BorderLayout.CENTER);
			
			JPanel centerright = new JPanel(new GridLayout(3,1));
				centerright.add(registstaff);
				centerright.add(modifystaff);
				centerright.add(deletestaff);
				
			center.add(centerright,BorderLayout.EAST);
		
		
		//아래
		JPanel bottom = new JPanel();
			bottom.add(new JLabel("ID 조회"));
			bottom.add(tfsearchid);
			bottom.add(search);
		
		add(center,BorderLayout.CENTER);
		add(bottom,BorderLayout.SOUTH);
		
		
	}
	
	void eventProc() {
		ButtonEventHandler hdlr = new ButtonEventHandler();
		search.addActionListener(hdlr);
		registstaff.addActionListener(hdlr);
		modifystaff.addActionListener(hdlr);
		tfsearchid.addActionListener(hdlr);
		deletestaff.addActionListener(hdlr);
		
	}
	
	// 버튼 이벤트 핸들러 만들기
		class ButtonEventHandler implements ActionListener{
			public void actionPerformed(ActionEvent ev){
				Object o = ev.getSource();

				if(o==search){  
					searchstaff();					// 비디오 등록
				}
				else if(o==registstaff){  
					regist();
					JOptionPane.showMessageDialog(null, "등록되었습니다.");
					clear();

				}
				else if (o==tfsearchid) {
					searchstaff();
				}
				
				else if(o==modifystaff) {
					modify();
					JOptionPane.showMessageDialog(null, "수정되었습니다.");
					clear();
				}
				else if(o==deletestaff) {
					delete();
					JOptionPane.showMessageDialog(null, "삭제되었습니다.");
					clear();
				}

			}
		}//end of buttoneventhandler
		
		/*
		 * 역할 : id 검색 누르면 창에 조회됨
		 */
		void searchstaff() {
			
			String searchid = tfsearchid.getText();
			try {
				StaffVO vo = dao.search(searchid);
				tfstaffid.setText(vo.getStaffid());
				tfstaffname.setText(vo.getStaffname());
				tfstafftel.setText(vo.getStafftel());
				
			} catch (Exception e) {
				System.out.println("staffinfo 연결실패 :"+e.toString());
				e.printStackTrace();
			}
		}
		
		/*
		 * 역할 : 직원등록 누르면, 창에 나와있는 것들이 테이블로 저장됨
		 */
		void regist(){
			String id  =tfstaffid.getText();
			String name = tfstaffname.getText();
			String tel = tfstafftel.getText();
			
			try {
				dao.registstaff(id,name,tel);
				
			} catch (Exception e) {
				System.out.println("등록실패:"+e.toString());
				e.printStackTrace();
			}
		}
		/*
		 * 역할 : 조회 후, 수정하고 수정버튼 누르면 새로 업데이트
		 */
		void modify() {
			
			String id = tfstaffid.getText();
			String name = tfstaffname.getText();
			String tel = tfstafftel.getText();
			
			try {
				dao.modify(id,name,tel);
				
			} catch (Exception e) {
				System.out.println("수정실패 :"+e.toString());
				e.printStackTrace();
			}
			
		}
		
		/*
		 * 역할 : 조회한 id정보를 가지고 와서 db에 삭제하게 하는 것
		 */
		void delete() {
			
			String id = tfstaffid.getText();
			
			try {
				dao.delete(id);
			} catch (Exception e) {
				System.out.println("삭제실패:"+e.toString());
				e.printStackTrace();
			}
		}
		
		void clear() {
			
			tfstaffid.setText(null);
			tfstaffname.setText(null);
			tfstafftel.setText(null);
			tfsearchid.setText(null);
		}

}
