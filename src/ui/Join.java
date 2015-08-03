package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import service.UserService;
import service.UserServiceImpl;
import vo.UserVo;

public class Join extends JFrame implements ActionListener {
	JLabel jlId, jlName, jlGender, jlPw, jlPwRe, jlPwHint, jlPwAnswer, jlEmail, jlPhone, jlZipCode, jlAddr1, jlAddr2;

	JTextField jtId, jtName, jtPwHint, jtPwAnswer, jtEmail, jtPhone2, jtPhone3, jtZipCode1, jtZipCode2, jtAddr1,
			jtAddr2;

	JPasswordField jPw, jPwRe;

	ButtonGroup jbGender;
	JRadioButton jrM, jrF;

	JComboBox jcPhone1;

	JButton joinBtn, noBtn, selectIdBtn;

	int genderValue; // 성별을 구별하기 위한 멤버 필드
	int selectIdCount = -1; // 아이디 중복 검사를 int로 판단한다.
							// 초기 값은 -1
							// 0 : 중복 아이디 없음.
							// 1 : 중복 아이디 있음.
							// -1 : 중복 검사를 하지 않음.

	// 기본 생성자
	public Join() {
		// 프레임 시작지점 및 크기 설정
		setBounds(30, 30, 600, 670);

		// x버튼 종료 구현
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// 레어아웃을 수동으로
		setLayout(null);

		// 라벨 초기화 및 생성
		jlId = new JLabel("아이디");
		jlName = new JLabel("이름");
		jlGender = new JLabel("성별");
		jlPw = new JLabel("비밀번호");
		jlPwRe = new JLabel("비밀번호 확인");
		jlPwHint = new JLabel("비밀번호 찾기 질문");
		jlPwAnswer = new JLabel("비밀번호 찾기 답");
		jlEmail = new JLabel("이메일");
		jlPhone = new JLabel("전화번호");
		jlZipCode = new JLabel("우편번호");
		jlAddr1 = new JLabel("주소");
		jlAddr2 = new JLabel("상세주소");

		// 라벨에 대한 위치와 크기 값
		jlId.setBounds(30, 30, 100, 30);
		jlName.setBounds(30, 70, 100, 30);
		jlGender.setBounds(30, 110, 100, 30);
		jlPw.setBounds(30, 150, 100, 30);
		jlPwRe.setBounds(30, 190, 100, 30);
		jlPwHint.setBounds(30, 230, 100, 30);
		jlPwAnswer.setBounds(30, 270, 100, 30);
		jlEmail.setBounds(30, 310, 100, 30);
		jlPhone.setBounds(30, 350, 100, 30);
		jlZipCode.setBounds(30, 390, 100, 30);
		jlAddr1.setBounds(30, 430, 100, 30);
		jlAddr2.setBounds(30, 470, 100, 30);

		// 라벨을 프레임에 부착
		add(jlId);
		add(jlName);
		add(jlGender);
		add(jlPw);
		add(jlPwRe);
		add(jlPwHint);
		add(jlPwAnswer);
		add(jlEmail);
		add(jlPhone);
		add(jlZipCode);
		add(jlAddr1);
		add(jlAddr2);

		// 텍스트 필드 생성
		jtId = new JTextField();
		jtName = new JTextField();
		jtPwHint = new JTextField();
		jtPwAnswer = new JTextField();
		jtEmail = new JTextField();
		jtPhone2 = new JTextField();
		jtPhone3 = new JTextField();
		jtZipCode1 = new JTextField();
		jtZipCode2 = new JTextField();
		jtAddr1 = new JTextField();
		jtAddr2 = new JTextField();

		// 텍스트 필드 위치와 크기 값
		jtId.setBounds(140, 30, 100, 30);
		jtName.setBounds(140, 70, 100, 30);
		jtPwHint.setBounds(140, 230, 310, 30);
		jtPwAnswer.setBounds(140, 270, 310, 30);
		jtEmail.setBounds(140, 310, 310, 30);
		jtPhone2.setBounds(240, 350, 100, 30);
		jtPhone3.setBounds(350, 350, 100, 30);
		jtZipCode1.setBounds(140, 390, 100, 30);
		jtZipCode2.setBounds(250, 390, 100, 30);
		jtAddr1.setBounds(140, 430, 310, 30);
		jtAddr2.setBounds(140, 470, 310, 30);

		// 텍스트 필드 프레임에 부착
		add(jtId);
		add(jtName);
		add(jtPwHint);
		add(jtPwAnswer);
		add(jtEmail);
		add(jtPhone2);
		add(jtPhone3);
		add(jtZipCode1);
		add(jtZipCode2);
		add(jtAddr1);
		add(jtAddr2);

		// 패스워드 필드 생성 및 값 초기화
		jPw = new JPasswordField(100);
		jPwRe = new JPasswordField(100);

		// 패스워드 필드 위치와 크기 값
		jPw.setBounds(140, 150, 100, 30);
		jPwRe.setBounds(140, 190, 100, 30);

		// 패스워드 필드 프레임에 부착
		add(jPw);
		add(jPwRe);

		// 라디오 버튼을 그룹화 시키기 위해 버튼그룹 생성
		jbGender = new ButtonGroup();

		// 라디오 버튼 생성 및 초기화
		jrM = new JRadioButton("남");
		jrF = new JRadioButton("여");

		// 라디오 버튼을 버튼그룹에 부착
		jbGender.add(jrM);
		jbGender.add(jrF);

		// 라디오 버튼의 크기와 위치 값
		jrM.setBounds(140, 110, 50, 30);
		jrF.setBounds(200, 110, 50, 30);

		// 라디오 버튼을 프레임에 부착
		add(jrM);
		add(jrF);

		// 콤보박스 생성 및 제러널 타입을 String으로
		jcPhone1 = new JComboBox<String>();

		// 콤보박스의 리스트 값 부착
		jcPhone1.addItem("010");
		jcPhone1.addItem("011");
		jcPhone1.addItem("016");
		jcPhone1.addItem("017");
		jcPhone1.addItem("019");

		// 콤보박스의 위치와 크기 값
		jcPhone1.setBounds(140, 350, 100, 30);

		// 콤보박스를 프레임에 부착
		add(jcPhone1);

		// 버튼 생성 및 위치 크기 값 조절 후 프레임 부착
		joinBtn = new JButton("가입 완료");
		joinBtn.setBounds(30, 520, 150, 30);
		add(joinBtn);

		noBtn = new JButton("취소");
		noBtn.setBounds(190, 520, 150, 30);
		add(noBtn);

		selectIdBtn = new JButton("ID 중복 검사");
		selectIdBtn.setBounds(250, 30, 100, 30);
		add(selectIdBtn);

		// 프레임을 보여준다.
		setVisible(true);

		// 라디오 버튼 남자 클릭시 멤버 필드 int 형 변수
		// genderValue값에 1을 대입하여 준다.
		// DB에서 성별을 남자는 1값 여자는 2값으로 하였기 때문이다.
		jrM.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				genderValue = 1;
			}
		});

		// 라디오 버튼 남자 클릭시 멤버 필드 int 형 변수
		// genderValue값에 2을 대입하여 준다.
		// DB에서 성별을 남자는 1값 여자는 2값으로 하였기 때문이다.
		jrF.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				genderValue = 2;
			}
		});

		// 버튼에 대한 이벤트 처리는 ActionListener로 처리하였으며
		// 이 클래스의 이벤트 처리는 implements하여 처리했다.
		joinBtn.addActionListener(this);
		noBtn.addActionListener(this);
		selectIdBtn.addActionListener(this);

	}

	// 메인 클래스 - 생성자를 호출하기 때문에 바로 프레임이 뜨게 될 것 이다.
	public static void main(String[] args) {
		new Join();
	}

	// 버튼의 이벤트 처리는 actionPerformed 메서드에서 처리한다.
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = jcPhone1.getSelectedItem();
		//
		// System.out.println(obj);
	
		Object btn = e.getSource();

		// joinBtn(가입하기) 버튼 클릭시
		if (btn == joinBtn) {

			if (selectIdCount == -1) {

				JOptionPane.showMessageDialog(null, "아이디를 조회하세요");

			} else if (selectIdCount >= 1) {

				JOptionPane.showMessageDialog(null, "중복되는 ID가 있습니다.");

			} else if (jtId.getText().trim().equals("")) {

				JOptionPane.showMessageDialog(null, "아이디를 입력하세요");

			} else if (jtName.getText().trim().equals("")) {

				JOptionPane.showMessageDialog(null, "이름을 입력하세요");

			} else if (genderValue == -1) {

				JOptionPane.showMessageDialog(null, "성별을 선택하세요");

			} else if (jPw.getPassword().equals("")) {

				JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요");

			} else if (jPwRe.getPassword().equals("")) {

				JOptionPane.showMessageDialog(null, "비밀번호 확인을 입력하세요");

			} else if (jPw.getPassword().equals(jPwRe.getPassword())) {

				JOptionPane.showMessageDialog(null, "비밀번호와 비밀번호 확인값이 틀립니다.");

			} else if (jtPwHint.getText().trim().equals("")) {

				JOptionPane.showMessageDialog(null, "비밀번호 질문을 입력하세요.");

			} else if (jtPwAnswer.getText().trim().equals("")) {

				JOptionPane.showMessageDialog(null, "비밀번호 답을 입력하세요");

			} else if (jtPhone2.getText().trim().equals("")) {

				JOptionPane.showMessageDialog(null, "연락처 중간자리를 입력하세요");

			} else if (jtPhone3.getText().trim().equals("")) {

				JOptionPane.showMessageDialog(null, "연락처 끝자리를 입력하세요");

			} else if (jtAddr1.getText().trim().equals("")) {

				JOptionPane.showMessageDialog(null, "주소를 입력하세요");

			} else if (jtAddr2.getText().trim().equals("")) {

				JOptionPane.showMessageDialog(null, "상세주소를 입력하세요");

			} else if (genderValue == 0) {

				JOptionPane.showMessageDialog(null, "성별을 선택하세요");

			} else{
			
			UserVo userVo = new UserVo();
			
			String phoneNum = (String) obj+"-"+jtPhone2.getText()+"-"+jtPhone3.getText();
			System.out.println(phoneNum);
			
			char[] pwlist = jPw.getPassword();
			String pws = "";
			int test;
			for (int i = 0; i < pwlist.length; i++) {
				
				test = (int)pwlist[i];
				test+=3;
				pwlist[i] = (char) test;
				pws += pwlist[i];
			}
			
			//userVo에 현재 창에서 입력된 값을 담는다.
			userVo.setUser_id(jtId.getText().trim());
			userVo.setUser_name(jtName.getText().trim());
			userVo.setUser_pwd(pws);
			userVo.setUser_pwd_hint(jtPwHint.getText().trim());
			userVo.setUser_pwd_answer(jtPwAnswer.getText().trim());
			userVo.setUser_gender(genderValue);
			userVo.setUser_email(jtEmail.getText().trim());
			userVo.setUser_phone(phoneNum);
			userVo.setUser_zipcode(jtZipCode1.getText()+"-"+jtZipCode2.getText());
			userVo.setUser_addr1(jtAddr1.getText().trim());
			userVo.setUser_addr2(jtAddr2.getText().trim());
			
			pws = null;

			
			//DB로 조회 하기 위해 서비스 객체를 생성한다.
			UserService svc = new UserServiceImpl();
			
			try {
				boolean f1 = svc.joinUser(userVo);
				
				if(f1 == true) {
					JOptionPane.showMessageDialog(null, "가입이 완료되었습니다.");
					userVo.setUser_pwd("");
				} else if (f1==false){
					JOptionPane.showMessageDialog(null, "음??");
				}
					
					
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "관리자에게 문의하시기 바랍니다.");
				e1.printStackTrace();
			}
			
		}
		
		
	

			// noBtn(취소) 버튼 클릭시
		} else if (btn == noBtn) {

			// 가입을 취소 할 것 인지 안할 것 인지 묻는다.
			int result = JOptionPane.showConfirmDialog(this, "가입을 취소 하시겠습니까?", "alert", JOptionPane.YES_NO_OPTION);

			// 취소를 OK 할 경우
			if (result == JOptionPane.YES_OPTION) {
				// 회원가입 프레임 종료
				dispose();
				// 로그인 프레임 실행
				new LogIn("로그인");

				// 취소를 NO 할 경우
			} else if (result == JOptionPane.NO_OPTION) {
				// 따로 처리 할것이 없기 때문에 그냥 가만히 냅둔다
			}

			// selectIdBtn(아이디 중복검사) 버튼 클릭시
		} else if (btn == selectIdBtn) {

			// 아이디를 입력한 텍스트 필드가 공백이 있는지와 띄어쓰기가 있는지 검사
			if (jtId.getText().trim().equals("")) {
				// 아이디를 입력하라는 경고창 출력
				JOptionPane.showMessageDialog(null, "아이디를 입력하세요");

				// 위에 조건을 통과 할 경우
			} else {

				// DB로 조회 하기 위해 서비스 객체를 생성한다.
				UserService svc = new UserServiceImpl();

				// DB에 접근할 경우 Exception이 발생할 수 있기 때문에
				// 예외 처리를 하였다.
				try {
					// 서비스 객체에 있는 아이디 중복검사 메서드를 호출한다.
					// 조회하기 위해 텍스트필드의 ID입력 값을 메서드에 넘겨준다.
					// DB에서 조회가 되면 selectIdCount에 0 또는 1값이 들어온다.
					selectIdCount = svc.idSelect(jtId.getText());

					// 0이 들어온 경우는 중복된 아이디가 없기 때문에 0이므로
					// 사용 가능한 아이디라는 메세지를 호출한다.
					if (selectIdCount == 0) {
						JOptionPane.showMessageDialog(null, "사용가능한 ID 입니다.");

						// 1이 들어온 경우는 중복된 아이디가 있기 때문에 1이므로
						// 중복된 아이디라는 메세지를 호출한다.
					} else if (selectIdCount == 1) {
						JOptionPane.showMessageDialog(null, "중복되는 ID 입니다.");
					}

					// 예외가 발생하여 관리자에게 문의하라는 메세지를 호출한다.
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "관리자에게 문의하시기 바랍니다.");
					e1.printStackTrace();
				}

			}
		}

	}

}
