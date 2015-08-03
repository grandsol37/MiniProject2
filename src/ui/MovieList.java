package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import service.MovieService;
import service.MovieServiceImpl;
import vo.MovieInfoVo;

public class MovieList extends JFrame {
	
	MovieInfoVo movieInfoVo;
	
	List<MovieInfoVo> movieList;
	
	JLabel[] jlMovie_Name;
	JLabel[] jlScreen_Num;
	JLabel[] jlMovie_Time;
	JLabel[] jlSeat_Amount;
	JButton[] jbtnMovie_No;
	
	JComboBox[] personCount;
	String movieName, userId;
	
	
	public MovieList(String movieName, String userId) {
		this.movieName = movieName;
		this.userId = userId;
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(30, 30, 960, 800);
		setLayout(null);
		
		// DB로 영화 목록을 받아오게 할 것이다. 재 사용의 목적은 없으나
		// 생성자 코드의 길이를 줄이고자 private 메서드로 따로 분리처리하였다.
		moviePlayList(this.movieName);
		
		// 영화상영시간 리스트의 경우 리스트목록들이 각각 다르기 때문에
		// 만약 금일 상영을 안하는 경우 null이 발생한다.
		// 문제는 프레임들이 배열이기 때문에 리스트값을 못받으면
		// null값으로 인한 프레임 생성 Array 관련 애러가 뿜어지기 때문에
		// 방지하지 위해 조건문을 통해 이전페이지로 넘기게 한다.
		if (movieList == null) {
			JOptionPane.showMessageDialog(null, "상영중인 관이 없습니다.");
			dispose();
			new Choice(userId, "Mohito에 오신 것을 환영합니다!");
		} else {
			
			// 프레임들의 위치 처리 및 버튼이벤트를 처리하였다.
			// 코드들이 길다보니 private 메서드로 따로 분리시켰다.
			loading(movieName);
			setVisible(true);
		}
	}





	private void loading(String movieName) {
		
		jlMovie_Name = new JLabel[movieList.size()];
		jlScreen_Num = new JLabel[movieList.size()];
		jlMovie_Time = new JLabel[movieList.size()];
		jlSeat_Amount = new JLabel[movieList.size()];
		jbtnMovie_No = new JButton[movieList.size()];

		
		JComboBox personCount = new JComboBox<String>();
		personCount.addItem("1");
		personCount.addItem("2");
		personCount.addItem("3");
		personCount.addItem("4");
		personCount.addItem("5");
		personCount.addItem("6");
		personCount.addItem("7");
		personCount.addItem("8");
		
		
		int y = 60;
		for (int i = 0; i < jlScreen_Num.length; i++) {
			movieInfoVo = (MovieInfoVo) movieList.get(i);
			jlScreen_Num[i] = new JLabel(movieInfoVo.getScreen_num());
			jlScreen_Num[i].setBounds(30, y, 50, 30);
			add(jlScreen_Num[i]);
			
			jlMovie_Name[i] = new JLabel(movieInfoVo.getMovie_name());
			jlMovie_Name[i].setBounds(90, y, 200, 30);
			add(jlMovie_Name[i]);
			
			//DB에서 가져온 시간을 아래 양식으로 바꾸기 위해 사용한다.
			SimpleDateFormat sdf = new SimpleDateFormat( "yyyy년 MM월 dd일 HH시 mm분" , Locale.KOREA );
			String time = sdf.format( movieInfoVo.getMovie_time() );
			jlMovie_Time[i] = new JLabel(sdf.format( movieInfoVo.getMovie_time() ));
			jlMovie_Time[i].setBounds(300, y, 200, 30);
			add(jlMovie_Time[i]);
			
			jlSeat_Amount[i] = new JLabel(movieInfoVo.getSeat_amount()+"자리");
			jlSeat_Amount[i].setBounds(510, y, 80, 30);
			add(jlSeat_Amount[i]);
			
			// 버튼에게 이름 값을 준다. DB에서 검색에 필요한 movie_no(시퀀스값)과
			// 영화 상영 시간을 넣어뒀다. 버튼의 이름은 이미지로 덮어씌운다
			jbtnMovie_No[i] = new JButton(movieInfoVo.getMovie_no()+"/"+time, new ImageIcon("src/test/btn.png"));
			jbtnMovie_No[i].setBounds(600, y, 50, 30);
			add(jbtnMovie_No[i]);
			
			
			
			y += 40;
			
			
			jbtnMovie_No[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {

					int personTotal = Integer.parseInt( (String) personCount.getSelectedItem());					
					
					String temp = e.getActionCommand();
					String[] separate = temp.split("/");
					int movieNo = Integer.parseInt(separate[0]);
					
					// 인원이 맞는지 묻는다.
					int result = JOptionPane.showConfirmDialog(null,
							"관람 인원 " + personTotal + "명 \n"+separate[1]+"\n영화 맞으신가요?", "알림", JOptionPane.YES_NO_OPTION);
					
					// OK 할 경우 자리선택 프레임을 연다.
					if(result == JOptionPane.YES_OPTION) {
						new ActorSeat(movieNo, movieName, personTotal, userId);
						dispose();

					// 취소를 NO 할 경우	
					} else if (result == JOptionPane.NO_OPTION) {
						// 따로 처리 할것이 없기 때문에 그냥 가만히 냅둔다
					}

				}
			});
		}
		personCount.setBounds(660, 30, 100, 30);
		add(personCount);
	}
	
	
	private void moviePlayList(String movieName) {
		try {
			
			MovieService msv = new MovieServiceImpl();
			movieList = msv.moviePlayList(movieName);
			
			if (movieList != null) {
				for (int i = 0; i < movieList.size(); i++) {
					movieInfoVo = (MovieInfoVo) movieList.get(i);
				}
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();

		}
	}

}
