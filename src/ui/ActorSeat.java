package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import service.MovieService;
import service.MovieServiceImpl;
import vo.MovieBooKingVo;
import vo.MovieInfoVo;

public class ActorSeat extends JFrame {

	JButton btnPay, btnBack;

	JLabel as, sc;

	JCheckBox[] jcb;

	List<MovieBooKingVo> booKingList;
	
	ArrayList<String> seatNumList = new ArrayList();

	MovieBooKingVo movieBooKingVo;

	int movieNo;
	String movieName;
	int personTotal;
	String userId;

	int checkBoxCount;

	ActorSeat(int movieNo, String movieName, int personTotal, String userId) {
		this.movieNo = movieNo;
		this.movieName = movieName;
		this.personTotal = personTotal;
		this.userId = userId;

		// DB에서 해당 상영관의 예약된 자리의 값을 받아온다.
		movieSeatList(this.movieNo);

		setBounds(0, 0, 800, 500);
		setLayout(null);

		sc = new JLabel("SCREEN");
		as = new JLabel("좌석을 선택해주세요");

		btnPay = new JButton("선택 완료");
		btnBack = new JButton("뒤로 가기");

		jcb = new JCheckBox[37];

		// 계산하기 귀찮아서 배열 0번째는 버리고 시작했음...
		// 나중에 시간되면 수정하는걸로..
		jcb[0] = new JCheckBox();

		// 체크박스를 반복문으로 통해 생성
		for (int i = 1; i < jcb.length; i++) {
			jcb[i] = new JCheckBox();
			jcb[i].setName(i + "");
			add(jcb[i]);
		}

		if (booKingList != null) {
			for (int i = 0; i < booKingList.size(); i++) {
				// 예약된 자리를 리스트에서 꺼내기 위해 사용한다.
				// 예약된 정보를 리스트에서 꺼내면서 movieBooKingVo에 담는다
				movieBooKingVo = (MovieBooKingVo) booKingList.get(i);
				
				// movieBooKingVo에 담긴 정보중 예약 자리번호만 a에 담는다
				int a = movieBooKingVo.getRes_seat_num();
				
				// 해당 예약 번호를 체크박스 배열을 통해 대입시킨 후
				// 그 자리는 클릭하지 못하게 비활성화 시킨다.
				jcb[a].setEnabled(false);

			}
		}

		as.setBounds(50, 400, 120, 30);
		sc.setBounds(200, 10, 100, 10);

		jcb[1].setBounds(30, 40, 30, 30);
		jcb[2].setBounds(60, 40, 30, 30);
		jcb[3].setBounds(90, 40, 30, 30);
		jcb[4].setBounds(180, 40, 30, 30);
		jcb[5].setBounds(210, 40, 30, 30);
		jcb[6].setBounds(240, 40, 30, 30);
		jcb[7].setBounds(270, 40, 30, 30);
		jcb[8].setBounds(360, 40, 30, 30);
		jcb[9].setBounds(390, 40, 30, 30);
		jcb[10].setBounds(420, 40, 30, 30);
		jcb[11].setBounds(30, 120, 30, 30);
		jcb[12].setBounds(60, 120, 30, 30);
		jcb[13].setBounds(90, 120, 30, 30);
		jcb[14].setBounds(180, 120, 30, 30);
		jcb[15].setBounds(210, 120, 30, 30);
		jcb[16].setBounds(240, 120, 30, 30);
		jcb[17].setBounds(270, 120, 30, 30);
		jcb[18].setBounds(360, 120, 30, 30);
		jcb[19].setBounds(390, 120, 30, 30);
		jcb[20].setBounds(420, 120, 30, 30);
		jcb[21].setBounds(40, 200, 30, 30);
		jcb[22].setBounds(70, 200, 30, 30);
		jcb[23].setBounds(180, 200, 30, 30);
		jcb[24].setBounds(210, 200, 30, 30);
		jcb[25].setBounds(240, 200, 30, 30);
		jcb[26].setBounds(270, 200, 30, 30);
		jcb[27].setBounds(375, 200, 30, 30);
		jcb[28].setBounds(405, 200, 30, 30);
		jcb[29].setBounds(40, 280, 30, 30);
		jcb[30].setBounds(70, 280, 30, 30);
		jcb[31].setBounds(180, 280, 30, 30);
		jcb[32].setBounds(210, 280, 30, 30);
		jcb[33].setBounds(240, 280, 30, 30);
		jcb[34].setBounds(270, 280, 30, 30);
		jcb[35].setBounds(375, 280, 30, 30);
		jcb[36].setBounds(405, 280, 30, 30);

		btnPay.setBounds(250, 400, 100, 30);
		btnBack.setBounds(350, 400, 100, 30);

		add(as);
		add(sc);

		add(btnPay);
		add(btnBack);

		setVisible(true);

		btnPay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				

				int result = JOptionPane.showConfirmDialog(null, "자리선택을 완료 하셨습니까?", "알림", JOptionPane.YES_NO_OPTION);
				// OK 할 경우
				if (result == JOptionPane.YES_OPTION) {

					// 체크박스를 클릭한 갯수를 알아보기 위한 부분
					for (int i = 1; i < jcb.length; i++) {
						if (jcb[i].isSelected()) {
							checkBoxCount++;
						}
					}

					// 체크박스를 체크한 갯수와 영화관람할 인원을
					// 제어문으로 비교
					if (checkBoxCount > personTotal || checkBoxCount < personTotal) {
						JOptionPane.showMessageDialog(null, "관람 인원은 " + personTotal + "명 입니다. \n다시 선택하시기 바랍니다.");
						checkBoxCount = 0;
						
					//맞게 선택하였다면 아래의 코드를 처리한다.	
					} else if (checkBoxCount == personTotal) {
						
						// 체크한 체크박스의 번호값을 ArrayList에 담는다.
						for (int i = 1; i < jcb.length; i++) {
							if (jcb[i].isSelected()) {
								seatNumList.add(jcb[i].getName());
							}
						}
						// 해당 프레임 종료
						dispose();
						// 선택 결과 및 결제하기 프레임으로 이동
						new PayMentScreen(seatNumList, movieNo, movieName, personTotal, userId);
					}

					// 취소를 NO 할 경우
				} else if (result == JOptionPane.NO_OPTION) {
					// 따로 처리 할것이 없기 때문에 그냥 가만히 냅둔다
				}

			}

		});
		
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new MovieList(movieName, userId);
			}
		});

	}

	private void movieSeatList(int movieNo) {
		try {

			MovieService msv = new MovieServiceImpl();
			booKingList = msv.selectSeatNum(movieNo);


		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "관리자에게 문의하세요!");
		}
	}

}
