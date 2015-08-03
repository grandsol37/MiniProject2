package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Thread.ComThread;

public class MainClass extends JFrame implements ActionListener {
	JButton btn0, btn1, btn2, btn3, btn4;
	JLabel information, youinfo, youinfo2;
	Poker pk;
	
	boolean flag = true;

	int index = 0;
	

	public MainClass(String title) {
		super(title);
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		btn1 = new JButton("게임 시작");
		btn2 = new JButton("종료 하기");
		btn3 = new JButton("승부하기");
		btn4 = new JButton("포기하기");

		information = new JLabel();
		youinfo = new JLabel();
		pk = new Poker(information, youinfo, youinfo2, btn3, btn4);
		setLayout(null);

		btn1.setBounds(300, 500, 80, 80);
		btn2.setBounds(430, 500, 80, 80);
		btn3.setBounds(300, 500, 80, 80);
		btn4.setBounds(430, 500, 80, 80);

		information.setBounds(30, 30, 700, 200);
		youinfo.setBounds(370, 400, 100, 100);
		add(btn1);
		add(btn2);
		add(btn3);
		add(btn4);
		add(information);
		add(youinfo);

		btn3.setVisible(false);
		btn4.setVisible(false);

		setVisible(true);

		btn1.addActionListener(this);
		btn2.addActionListener(this);

		btn3.addActionListener(this);
		btn4.addActionListener(this);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainClass("이름 미정");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		
		ComThread th1 = new ComThread(pk);
		Thread th2 = new Thread(th1);
		index = pk.getIndex();
		
		st(obj);

		if (obj == btn3 && index <= 9) {
			flag = false;
			pk.vs();
			btn3.setVisible(false);
			btn4.setVisible(false);
		}

		if (obj == btn4 && index <= 9) {
			flag = false;
			pk.myStop();
			btn3.setVisible(false);
			btn4.setVisible(false);
		}
		
		if ( flag == false  ) {
			
			th2.start();
			flag = true;
		}
		
		
		if (index == 10) {
			btn1.setVisible(true);
			btn2.setVisible(true);
			btn3.setVisible(false);
			btn4.setVisible(false);
			
			st(obj);
		}

	}

	private void st(Object obj) {
		if (obj == btn1) {
			index = 0;
			gameStart();
			pk.gameStart();
		} else if (obj == btn2) {
			System.exit(0);
		}
	}


	private void gameStart() {
		btn3.setVisible(true);
		btn4.setVisible(true);
		btn1.setVisible(false);
		btn2.setVisible(false);
	}

	
}
