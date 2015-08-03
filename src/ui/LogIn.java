package ui;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import service.UserService;
import service.UserServiceImpl;
import vo.UserVo;

public class LogIn extends JFrame implements ActionListener{
	JButton btnLogin, btnJoin;
	JLabel lbId, lbPw;
	JTextField tfId; 
	JPasswordField tfPw;
	
	public LogIn(String title){
		super(title);
		setBounds(30, 30, 500, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// 배치 관리자 X
		setLayout(null);
		
		btnLogin = new JButton("로그인");
		btnJoin = new JButton("회원가입");
		
		lbId = new JLabel("ID");
		lbPw = new JLabel("PW");
		
		tfId = new JTextField();
		tfPw = new JPasswordField();
		tfPw.setEchoChar('*');
		
		btnLogin.setBounds(100, 330, 150, 50);		
		btnJoin.setBounds(250, 330, 150, 50);
		
		lbId.setBounds(96, 107, 100, 30);		
		lbPw.setBounds(96, 207, 100, 30);
		tfId.setBounds(250, 107, 100, 40);
		tfPw.setBounds(250, 207, 100, 40);
		
		add(btnLogin);
		add(btnJoin);
		add(lbId);
		add(lbPw);
		add(tfId);
		add(tfPw);
		setVisible(true);
		
		
		btnLogin.addActionListener(this);
		btnJoin.addActionListener(this);
	}
	
	public static void main(String[] args) {
		LogIn lg = new LogIn("Login");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj1 = e.getSource();
		
		if(obj1 == btnLogin) {
			//로그인 버튼을 눌렀을 때
			//1.텍스트 필드의 값을 가져오기
			//2. 디비에 연결하여 해당 id, pw가 존재하는 사람이 있는지 여부를 알아오기
			//3. 존재하면 존재한다는 메세지 출력
			//4. 없으면 가입하라는 메세지를 출력
			
			
			if (tfId.getText().trim().equals("") ) {
				JOptionPane.showMessageDialog(null, "ID를 입력하세요.");
			} else if (tfPw.getPassword().length == 0) {
				JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요.");
			} else {
				
				// char 배열로 넘어온 비밀번호를 아스키코드 기준 +3씩하여 암호화한 뒤
				// pws라는 String 타입에 담는다.
				char[] pwlist = tfPw.getPassword();
				String pws = "";
				int test;
				for (int i = 0; i < pwlist.length; i++) {
					
					test = (int)pwlist[i];
					test+=3;
					pwlist[i] = (char) test;
					pws += pwlist[i];
				}
				
				UserVo userVo = new UserVo();
				userVo.setUser_id(tfId.getText());
				userVo.setUser_pwd(pws);
				pws = null;
				
				
				UserService svc = new UserServiceImpl();
				try {
					userVo = svc.selectLoingUser(userVo);
					userVo.setUser_pwd("");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "관리자에게 문의하시기 바랍니다.");
				}
				
				if(userVo == null) {
					JOptionPane.showMessageDialog(null, "아이디 또는 암호가 맞지 않습니다.");
				} else {
					JOptionPane.showMessageDialog(null, "로그인 성공");
					dispose();
					new Choice(tfId.getText(), "Mohito에 오신 것을 환영합니다!");
				}				
			}

		} else if (obj1 == btnJoin) {
			dispose();
			new Join();
			
		}
		
	}

}
