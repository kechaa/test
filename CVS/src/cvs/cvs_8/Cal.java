package cvs.cvs_8;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.TitledBorder;


public class Cal extends JPanel implements ActionListener{
	
	
	JTextField	tfProduct,tfcount, tftotalMoney;
	JButton [] jb =new JButton[10]; 	
	JButton bAdd,bOneCancle,bAllCancle, bPay;
	StringBuilder sb= new StringBuilder();
	JTextArea  taBasket, taBasket2;
	HashMap<JButton, Integer> price = new HashMap<JButton, Integer>();
	int [] pr = {1000,1200,4500,1500,700,3200,4000,2000,800,1500};
	String [] pn = {"생수", "삼각김밥","담배","컵라면","소보루크림빵","치약칫솔세트","혜자도시락","맥주","박카스","빼빼로"};
	int[] count = new int[10];
	ArrayList<JButton> list = new ArrayList<JButton>();
	String s = "";
	ArrayList addList = new ArrayList();
	
	int count2 =0;//상품 추가할 때 갯수 세어주는 변수(add버튼 누르면 0으로 초기화됨) 
	int sum=0;//총액 계산 위한 변수
	int addNum = 0;
	int cPrice=0;//상품가격*
	int i=0;//상품번호

	public Cal() {
		addLayout();
		eventProc();
	}
	//이벤트 연결
	void eventProc() {
		bOneCancle.addActionListener(this);
		bAllCancle.addActionListener(this);
		bPay.addActionListener(this);
		for(i =0; i<jb.length ; i++) {
			jb[i].addActionListener(this);}
		bAdd.addActionListener(this);
	}
	//이벤트 동작
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		//각 메뉴 눌렀을 때 메뉴이름나오고 갯수 세어지는 이벤트
		for(i = 0; i<pn.length;i++) {
			if(evt ==jb[i]) {jbButtonAct();}
		}
		if(evt ==bAdd ) {
			//addNum= Integer.parseInt(tfcount.getText());
//			sb.append(tfProduct.getText()+" "+count2+"EA"+"\n");
//				taBasket.setText(String.valueOf(sb));
//			taBasket2.append(String.valueOf(cPrice)+"\n");
			addList.add(tfProduct.getText()+" "+count2+"EA"+"\n");
			taBasket.setText(String.valueOf(addList));
			taBasket2.append(String.valueOf(cPrice)+"\n");
			sum +=cPrice;//총결제금액 올라가는 곳
			tftotalMoney.setText(String.valueOf(sum));//총결제금액 찍어주기
			count2 = 0;//갯수창 초기화 시키는 곳
			tfcount.setText(String.valueOf(count2));//갯수창0으로 초기화 
	
		
		
		}else if(evt ==bPay ) {
			
			

		}else if(evt ==bOneCancle ) {
			if(addList.size()!=0) {
			addList.remove(addList.size()-1);
			taBasket.setText(String.valueOf(addList));
			}else {JOptionPane.showMessageDialog(null,"메뉴를 추가해 주십시오.");
				
			}
		}else if(evt ==bAllCancle ) {
			int n =JOptionPane.showConfirmDialog(null, "전체 삭제 하시겠습니까?");
			if(n==0)addList.removeAll(addList);
			taBasket.setText(String.valueOf(addList));
			taBasket2.setText(null);
		}



	}
	//
	void jbButtonAct(){
		for(int j = 0; j<pn.length;j++) {

			if( i ==j ) {
				tfProduct.setText(pn[i]+" "+String.valueOf(pr[i]));//버튼 누르면 상품명 나오게 
				++count2;//갯수 하나씩 올라감 
				cPrice = pr[i]*count2;//상품가격*갯수

				tfcount.setText(String.valueOf(count2));//갯수창 계속 업데이트
				//taBasket.append(pn[j]+"\n");
				//	taBasket2.append(pr[j]+"\n");
					
				//tftotalMoney.setText(String.valueOf(sum));			
			}
		} 
	}


	//화면구성
	public void addLayout()
	{ 
		
		bAdd = new JButton("추가");
		bOneCancle =new JButton("하나 취소");
		bAllCancle=new JButton("전체 취소");
		bPay=new JButton("계산하기");
		bOneCancle.setPreferredSize(new Dimension(250, 180));
		bAllCancle.setPreferredSize(new Dimension(250, 180));
		bPay.setPreferredSize(new Dimension(20, 180));
		taBasket= new JTextArea(null,null, 40, 23);
		taBasket.setBorder(new TitledBorder("상품목록"));
		taBasket2= new JTextArea(null,null, 40, 8);
		taBasket2.setBorder(new TitledBorder("금액"));

		tftotalMoney = new JTextField(15);
		tfProduct = new JTextField(15);
		tfcount = new JTextField(3);
		tfProduct.setBorder(new TitledBorder("상품"));
		tfcount.setBorder(new TitledBorder("갯수"));
		//전체 프레임
		JPanel Jp= new JPanel();
	//	Jf.setTitle("GS25");

		Jp.setLayout(new BorderLayout());
		Jp.setSize(1500,1000);
		//west 
		//사진 붙임
		JPanel JpWest = new JPanel();
		JpWest.setLayout(new GridLayout(4,3));
		for(int i = 0; i<jb.length;i++){
			jb[i] = new JButton(new ImageIcon("src/cvs_8/"+i+".PNG"));
			price.put(jb[i],pr[i]);
			JpWest.add(jb[i]);
			count[i]=0;
		}
		for(int i = 0; i<jb.length;i++) {
			JpWest.add(jb[i]);
			JpWest.add(jb[i]);}

		//east 오른쪽 
		JPanel JpEast = new JPanel();
		JpEast.setLayout(new BorderLayout());




		//오른쪽 중앙영역 
		JPanel JpEastCenter = new JPanel();
		JpEastCenter.setLayout(new BorderLayout());
		JPanel JpEastCenterWest = new JPanel();
		JpEastCenterWest.setLayout(new BorderLayout());
		//오른쪽 윗 영역



		JPanel JpEastCenterWestCenter = new JPanel();
		JpEastCenterWestCenter.add(tfProduct);
		JpEastCenterWestCenter.add(tfcount);
		JpEastCenterWestCenter.add(bAdd);
		JpEastCenterWest.add(JpEastCenterWestCenter);		
		JpEastCenter.add(JpEastCenterWest,BorderLayout.WEST);
		JPanel JpEastCenterUpper = new JPanel();
		JpEastCenterUpper.add(taBasket);
		JpEastCenterUpper.add(taBasket2);

		JpEastCenter.add(JpEastCenterUpper,BorderLayout.CENTER);




		//오른쪽 아래 영역
		JPanel JpEastSouth = new JPanel();
		JpEastSouth.setLayout(new GridLayout(3,1));
		//상품 추가 버튼추가
		JPanel JpEastSouthUpper = new JPanel();
		JpEastSouthUpper.setLayout(new GridLayout(1,2));
		JpEastSouthUpper.add(bOneCancle);
		JpEastSouthUpper.add(bAllCancle);
		//총결제금액 추가 
		JPanel JpEastSouthTop = new JPanel();
		JpEastSouthTop.setLayout(new GridLayout(1,2));
		JpEastSouthTop.add(new JLabel("총 결제금액"));
		JpEastSouthTop.add(tftotalMoney);
		//JpEastCenter.add();
		JpEast.add(JpEastCenter, BorderLayout.CENTER);
		JPanel JpEastSouthUpperAndUpper = new JPanel();
		JpEastSouthUpperAndUpper.add(JpEastSouthTop);

		JpEastSouth.add(JpEastSouthUpperAndUpper);
		JpEastSouth.add(JpEastSouthUpper);
		JpEastSouth.add(bPay);



		JpEast.add(JpEastSouth,BorderLayout.SOUTH);
		Jp.add(JpWest,BorderLayout.WEST);
		Jp.add(JpEast,BorderLayout.EAST);
		Jp.setVisible(true);
		
	}

		


}
