package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import service.MovieService;
import service.MovieServiceImpl;
import vo.MovieInfoVo;

public class PayMentScreen extends JFrame implements ActionListener {

	JLabel jlName, jlTime, jlScNum, jlSeatNum, jlTotal, jlPrice;
	JLabel jlMoName, jlMoTime, jlMoScNum, 
			jlMoSeatNum, jlMoTotal, jlMoPrice;
	
	
	JButton pay, cancel;
	
	int movieNo;
	String movieName;
	int index;
	
	ArrayList<String> seatNumList;
	String userId;
	
	String[] sList;
	
	MovieInfoVo movieInfoVo = null;
	
	PayMentScreen(ArrayList<String> seatNumList, int movieNo, String movieName, int index, String userId) {
		this.seatNumList = seatNumList;
		this.movieNo = movieNo;
		this.movieName = movieName;
		this.index = index;
		this.userId = userId;
		
		setBounds(100, 100, 600, 600);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		jlName = new JLabel("영화명 :");
		jlTime = new JLabel("상영시간 :");
		jlScNum = new JLabel("상영관 :");
		jlSeatNum = new JLabel("자리 :");
		jlTotal = new JLabel("관람인원 :");
		jlPrice = new JLabel("최종 금액 :");
		
		pay = new JButton("결제하기");
		cancel = new JButton("취소");

		jlName.setBounds(90, 50, 100, 50);
		jlTime.setBounds(90, 110, 100, 50);
		jlScNum.setBounds(90, 170, 100, 50);
		jlSeatNum.setBounds(90, 230, 100, 50);		
		jlTotal.setBounds(90, 290, 100, 50);
		jlPrice.setBounds(90, 350, 100, 50);
		
		jlMoName = new JLabel(movieName);
		jlMoTime = new JLabel();
		jlMoScNum = new JLabel();
		jlMoSeatNum = new JLabel();
		jlMoTotal = new JLabel(index+"명");
		jlMoPrice = new JLabel();
		
		jlMoName.setBounds(200, 50, 300, 50);
		jlMoTime.setBounds(200, 110, 300, 50);
		jlMoScNum.setBounds(200, 170, 300, 50);
		jlMoSeatNum.setBounds(200, 230, 300, 50);		
		jlMoTotal.setBounds(200, 290, 300, 50);
		jlMoPrice.setBounds(200, 350, 300, 50);

		
		
		pay.setBounds(90, 410, 200, 50);
		cancel.setBounds(300, 410, 200, 50);
		
		pay.addActionListener(this);
		cancel.addActionListener(this);
		
		sList = new String[seatNumList.size()];
		movieInfo();
		
		add(jlName);
		add(jlTime);
		add(jlScNum);
		add(jlSeatNum);
		add(jlTotal);
		add(jlPrice);
		add(jlMoName);
		add(jlMoTime);
		add(jlMoScNum);
		add(jlMoSeatNum);
		add(jlMoTotal);
		add(jlMoPrice);
		add(pay);
		add(cancel);

		setVisible(true);
	}



	private void movieInfo() {
		try {
			MovieService msv = new MovieServiceImpl();
			movieInfoVo = msv.movieInfo(movieNo);
			
			if (movieInfoVo == null) {
				
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat( "yyyy년 MM월 dd일 HH시 mm분" , Locale.KOREA );
				String str = sdf.format( movieInfoVo.getMovie_time() );
				
				jlMoTime.setText(str);
				jlMoScNum.setText(movieInfoVo.getScreen_num());
				jlMoPrice.setText(movieInfoVo.getPrice()*index +"원");
				
				str="";
				for (int i = 0; i < seatNumList.size(); i++) {
					sList[i] = seatNumList.get(i);
					str += seatNumList.get(i)+"번 ";
				}
				jlMoSeatNum.setText(str);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == cancel) {
			int result = JOptionPane.showConfirmDialog(this, "취소하시겠습니까?", "영화예매", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				dispose();
				new Choice(userId, "Mohito에 오신 것을 환영합니다!");
			} else {

			}
		} else if (obj == pay) {
			int insertCount = 0;
			try {
				MovieService msv = new MovieServiceImpl();
				insertCount = msv.moviePay(movieNo, sList, userId);
				
				if (insertCount > 0) {
					dispose();
					new Finish("결제완료", userId);
				} else {
					JOptionPane.showMessageDialog(null, "결제실패");
				}
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
}
