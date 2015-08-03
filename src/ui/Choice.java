package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import logic.TCanvas;

public class Choice extends JFrame implements ActionListener {
	String id;
	JLabel jlbName;
	JButton jbtnMovie1, jbtnMovie2, jbtnMovie3, jbtnMovie4, jbtnResCon, jbtnExit;
	
	String userId;
	
	TCanvas can;	

	Choice(String userId, String title) {
		this.userId = userId;
		setTitle(title);

		setBounds(0, 0, 1300, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		can = new TCanvas(Toolkit.getDefaultToolkit().getImage("src/test/test.png"));

		jlbName = new JLabel(userId+"님 환영합니다!");
		
		jbtnMovie1 = new JButton("미션임파서블 로그네이션", new ImageIcon("src/test/1.jpg"));
		jbtnMovie2 = new JButton("인사이드아웃", new ImageIcon("src/test/2.jpg"));
		jbtnMovie3 = new JButton("암살", new ImageIcon("src/test/3.jpg"));
		jbtnMovie4 = new JButton("미니언즈", new ImageIcon("src/test/4.jpg"));
		jbtnResCon = new JButton("예약취소");
		jbtnExit = new JButton("종료");
		
		
		
		

		setLayout(null);
		jlbName.setFont(new Font("굴림", Font.BOLD, 20));
		jlbName.setForeground(Color.red);
		can.setBounds(0, 0, 1300, 800);
		jlbName.setBounds(100, 50, 200, 100);
		jbtnMovie1.setBounds(50, 150, 250, 350);
		jbtnMovie2.setBounds(350, 150, 250, 350);
		jbtnMovie3.setBounds(650, 150, 250, 350);
		jbtnMovie4.setBounds(950, 150, 250, 350);
		jbtnResCon.setBounds(100, 600, 700, 50);
		jbtnExit.setBounds(900, 600, 200, 50);

		add(jlbName);
		add(jbtnMovie1);
		add(jbtnMovie2);
		add(jbtnMovie3);
		add(jbtnMovie4);
		add(jbtnResCon);
		add(jbtnExit);
		add(can);

		jbtnMovie1.addActionListener(this);
		jbtnMovie2.addActionListener(this);
		jbtnMovie3.addActionListener(this);
		jbtnMovie4.addActionListener(this);
		jbtnResCon.addActionListener(this);
		jbtnExit.addActionListener(this);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == jbtnMovie1) {
			new MovieList(jbtnMovie1.getText(), userId);
			dispose();
		} else if (obj == jbtnMovie2) {
			new MovieList(jbtnMovie2.getText(), userId);
			dispose();
		} else if (obj == jbtnMovie3) {
			new MovieList(jbtnMovie3.getText(), userId);
			dispose();
		} else if (obj == jbtnMovie4) {
			new MovieList(jbtnMovie4.getText(), userId);
			dispose();
		} else if (obj == jbtnExit) {
			System.exit(0);
		} else if (obj == jbtnResCon) {
			new CancelMovie(userId);
			dispose();
		}

	}
}
