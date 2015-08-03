package ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ProjectLabel extends JFrame {

	private static Object add;
	JLabel lbId1;
	JLabel lbName1;
	JLabel lbSex1;
	JLabel lbMail1;
	JLabel lbPh1;
	JLabel lbZc1;
	JLabel lbAdr1;
	JLabel lbAddr1;

	JLabel lbId2;
	JLabel lbName2;
	JLabel lbSex2;
	JLabel lbMail2;
	JLabel lbPh2;
	JLabel lbZc2;
	JLabel lbAdr2;
	JLabel lbAddr2;
	
	JButton btn1, btn2;

	ProjectLabel() {

		setBounds(0, 0, 600, 700);
		setLayout(null);

		lbId1 = new JLabel("ID");
		lbName1 = new JLabel("이름");
		lbSex1 = new JLabel("성별");
		lbMail1 = new JLabel("EMAIL");
		lbPh1 = new JLabel("전화번호");
		lbZc1 = new JLabel("우편번호");
		lbAdr1 = new JLabel("주소");
		lbAddr1 = new JLabel("상세주소");

		lbId2 = new JLabel("라벨이 있는 자리");
		lbName2 = new JLabel("라벨이 있는 자리");
		lbSex2 = new JLabel("라벨이 있는 자리");
		lbMail2 = new JLabel("라벨이 있는 자리");
		lbPh2 = new JLabel("라벨이 있는 자리");
		lbZc2 = new JLabel("라벨이 있는 자리");
		lbAdr2 = new JLabel("라벨이 있는 자리");
		lbAddr2 = new JLabel("라벨이 있는 자리");
		
	
		
		btn1 = new JButton("버튼1");
		btn2 = new JButton("버튼2");

		lbId1.setBounds(20, 20, 80, 20);
		lbName1.setBounds(20, 60, 80, 20);
		lbSex1.setBounds(20, 100, 80, 20);
		lbMail1.setBounds(20, 140, 80, 20);
		lbPh1.setBounds(20, 180, 80, 20);
		lbZc1.setBounds(20, 220, 80, 20);
		lbAdr1.setBounds(20, 260, 80, 20);
		lbAddr1.setBounds(20, 300, 80, 20);
		
		lbId2.setBounds(300, 20, 200, 20);
		lbName2.setBounds(300, 60, 200, 20);
		lbSex2.setBounds(300, 100, 200, 20);
		lbMail2.setBounds(300, 140, 200, 20);
		lbPh2.setBounds(300, 180, 200, 20);
		lbZc2.setBounds(300, 220, 200, 20);
		lbAdr2.setBounds(300, 260, 200, 20);
		lbAddr2.setBounds(300, 300, 200, 20);

		btn1.setBounds(20, 350, 150, 50);
		btn2.setBounds(320, 350, 150, 50);


		/*
		 * JLabel lbId2; JLabel lbName2; JLabel lbSex2; JLabel lbMail2; JLabel
		 * lbPh2; JLabel lbZc2; JLabel lbAdr2; JLabel lbAddr2;
		 */

		add(lbId1);
		add(lbName1);
		add(lbSex1);
		add(lbMail1);
		add(lbPh1);
		add(lbZc1);
		add(lbId1);
		add(lbAdr1);
		add(lbAddr1);

		add(lbId2);
		add(lbName2);
		add(lbSex2);
		add(lbMail2);
		add(lbPh2);
		add(lbZc2);
		add(lbId2);
		add(lbAdr2);
		add(lbAddr2);

		add(btn1);
		add(btn2);
		setVisible(true);

	}


}
