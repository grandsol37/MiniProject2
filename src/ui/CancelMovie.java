package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import service.MovieService;
import service.MovieServiceImpl;
import vo.MovieInfoVo;

public class CancelMovie extends JFrame {
	
	MovieInfoVo movieInfoVo;
	
	List<MovieInfoVo> myBooKingList;
	
	JLabel[] jlMovie_Name;
	JLabel[] jlScreen_Num;
	JLabel[] jlMovie_Time;
	JLabel[] jlPrice;
	JLabel[] jlSeatNum;
	JButton[] jbtnMovie_No;
	
	String userId;
	
	
	public CancelMovie(String userId) {
		this.userId = userId;
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(30, 30, 960, 800);
		setLayout(null);
		

		movieSelectList();
		
		if (myBooKingList == null) {
			JOptionPane.showMessageDialog(null, "예매취소 할 게 없습니다!");
			new Choice(userId, "Mohito에 오신 것을 환영합니다!");
			dispose();
		} else {
			loading();
			setVisible(true);
		}
	}





	private void loading() {
		
		jlMovie_Name = new JLabel[myBooKingList.size()];
		jlScreen_Num = new JLabel[myBooKingList.size()];
		jlMovie_Time = new JLabel[myBooKingList.size()];
		jlPrice = new JLabel[myBooKingList.size()];
		jlSeatNum = new JLabel[myBooKingList.size()];
		jbtnMovie_No = new JButton[myBooKingList.size()];

		int y = 30;
		for (int i = 0; i < jlScreen_Num.length; i++) {
			movieInfoVo = (MovieInfoVo) myBooKingList.get(i);
			jlScreen_Num[i] = new JLabel(movieInfoVo.getScreen_num());
			jlScreen_Num[i].setBounds(30, y, 50, 30);
			add(jlScreen_Num[i]);
			
			jlMovie_Name[i] = new JLabel(movieInfoVo.getMovie_name());
			jlMovie_Name[i].setBounds(90, y, 200, 30);
			add(jlMovie_Name[i]);
			
			jlMovie_Time[i] = new JLabel(movieInfoVo.getMovie_time()+"");
			jlMovie_Time[i].setBounds(300, y, 200, 30);
			add(jlMovie_Time[i]);
			
			jlPrice[i] = new JLabel(movieInfoVo.getPrice()+"원");
			jlPrice[i].setBounds(510, y, 80, 30);
			add(jlPrice[i]);

			jlSeatNum[i] = new JLabel(movieInfoVo.getMovieBooKingVo().getRes_seat_num()+"번자리");
			jlSeatNum[i].setBounds(600, y, 80, 30);
			add(jlSeatNum[i]);

			jbtnMovie_No[i] = new JButton(movieInfoVo.getMovie_no()+"-"+movieInfoVo.getMovieBooKingVo().getRes_seat_num());
			jbtnMovie_No[i].setBounds(690, y, 50, 30);
			add(jbtnMovie_No[i]);
			
			
			
			y += 40;
			
			
			jbtnMovie_No[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					String[] temp = e.getActionCommand().split("-");
					int[] cancelInfoNum = new int[2];
					cancelInfoNum[0] = Integer.parseInt(temp[0]);
					cancelInfoNum[1] = Integer.parseInt(temp[1]);
					
					// 인원이 맞는지 묻는다.
					int result = JOptionPane.showConfirmDialog(null,
							"취소 하시겠습니까?", "알림", JOptionPane.YES_NO_OPTION);
					
					if(result == JOptionPane.YES_OPTION) {
						MovieService msv = new MovieServiceImpl();
						int deleteCount = 0;
						//고유번호, 자리번호
						try {
							deleteCount = msv.movieCancel(cancelInfoNum[0], cancelInfoNum[1]);
							if (deleteCount > 0) {
								JOptionPane.showMessageDialog(null, "취소완료!");
								dispose();
								new Choice(userId, "Mohito에 오신 것을 환영합니다!");
							} else {
								JOptionPane.showMessageDialog(null, "취소실패");
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					// 취소를 NO 할 경우	
					} else if (result == JOptionPane.NO_OPTION) {
						// 따로 처리 할것이 없기 때문에 그냥 가만히 냅둔다
					}

				}
			});
		}

	}
	
	private void movieSelectList() {
		try {
			
			MovieService msv = new MovieServiceImpl();
			myBooKingList = msv.myBooKingList(userId);
			
			if (myBooKingList != null) {
				for (int i = 0; i < myBooKingList.size(); i++) {
					movieInfoVo = (MovieInfoVo) myBooKingList.get(i);
				}
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();

		}
	}

}
