package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Finish extends JFrame implements ActionListener {
	JButton jbtnHome, jbtnExit;
	JLabel label;
	
	String userId;

	public Finish(String title, String userId) {
		super(title);
		this.userId = userId;
		setBounds(150, 50, 400, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		jbtnHome = new JButton("HOME");
		jbtnExit = new JButton("종료");
		label=new JLabel("결제가 성공적으로 완료되었습니다");

		jbtnHome.addActionListener(this);
		jbtnExit.addActionListener(this);

		setLayout(null);
		jbtnHome.setBounds(100, 150,100, 50);
		jbtnExit.setBounds(200, 150, 100, 50);
		label.setBounds(100,100,250,50);
		add(jbtnHome);
		add(jbtnExit);
		add(label);

		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();

		if (obj == jbtnHome) {
			dispose();
			new Choice(userId, "Mohito에 오신 것을 환영합니다!");

		} else if (obj == jbtnExit) {
			System.exit(0);
		}

		// JFrame f = new JFrame();

	}
}