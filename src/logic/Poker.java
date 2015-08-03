package logic;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Poker {

	public int[] value;
	Random rnd = new Random();
	public int num;

	ImageIcon[] icon;

	int index = 0;
	int myScore = 0;
	int comScore = 0;
	int[] temp = new int[20];
	int[] com = new int[10];
	int[] you = new int[10];
	JLabel information, youinfo, youinfo2;
	JButton btn3, btn4;

	public Poker(JLabel information, JLabel youinfo, JLabel youinfo2, JButton btn3, JButton btn4) {
		this.information = information;
		this.youinfo = youinfo;
		this.youinfo2 = youinfo2;
		this.btn3 = btn3;
		this.btn4 = btn4;
		icon = new ImageIcon[11];
		icon[0] = new ImageIcon("");
		for (int j = 1; j < icon.length; j++) {
			icon[j] = new ImageIcon("src/image/" + (j) + ".jpg");
		}
	}
	
	public int getIndex() {
		return index;
	}
	

	public int getCom(int index) {
		return com[index];
	}

	public int getYou(int index) {
		return you[index];
	}
	

	public int[] blend() {
		value = new int[20];

		// 6개 번호를 랜덤하게 생성
		// 1~10사이의 정수를 생성
		/*
		 * (int)(0*10+1) < (int)(Math.random()*10+1) < (int)(1*10+1)
		 */
		for (int i = 0; i < 10; i++) {
			value[i] = (int) (Math.random() * 10 + 1);
			// i == 1 일경우 0 과 비교
			// i == 2 일경우 0, 1, 과 비교
			// i == 3 일경우 0, 1, 2,
			// i == 4 일경우 0, 1, 2,3
			// i == 5 일경우 0, 1, 2, 3, 4
			for (int j = 0; j < i; j++) {
				if (value[i] == value[j]) {
					i--;
				}
			}
			/*
			 * 지금 뽑은 숫자가 기존의 배열요소와 비교해서 일치하게 있으면 다시 뽑기
			 */
		}

		for (int i = 10; i < value.length; i++) {
			value[i] = (int) (Math.random() * 10 + 1);
			// i == 1 일경우 0 과 비교
			// i == 2 일경우 0, 1, 과 비교
			// i == 3 일경우 0, 1, 2,
			// i == 4 일경우 0, 1, 2,3
			// i == 5 일경우 0, 1, 2, 3, 4
			for (int j = 10; j < i; j++) {
				if (value[i] == value[j]) {
					i--;
				}
			}
			/*
			 * 지금 뽑은 숫자가 기존의 배열요소와 비교해서 일치하게 있으면 다시 뽑기
			 */
		}

		select(value);
		// 잘섞였는지 확인용 출력
		for (int i = 0; i < value.length; i++) {
			System.out.print(value[i] + " ");
		}
		System.out.println();
		return value;
	}

	// 컴퓨터가 승부할 때 랜덤값을 0 또는 1로 넘기기 위해
	public int randomValue() {
		num = rnd.nextInt(2);
		return num;
	}

	//
	public int[] select(int[] value) {

		for (int i = 0; i <= 1000; i++) {
			int first = (int) (Math.random() * 20);
			int second = (int) (Math.random() * 20);

			int temp = 0;

			temp = value[first];

			value[first] = value[second];
			value[second] = temp;
		}
		return value;
	}

	public void gameStart() {
		index = 0;
		temp = blend();

		for (int i = 0; i < 10; i++) {
			com[i] = temp[i];
		}

		int j = 0;
		for (int i = 10; i < 20; i++) {
			you[j] = temp[i];
			j++;
		}

		youinfo.setIcon(icon[com[index]]);

	}

	public void vs() {
		if (you[index] > com[index]) {
			information.setText("승부하셨습니다! 상대방 카드 : " + com[index]  + " 나의 카드 : " + you[index] + " 당신의 승리입니다.");
			myScore++;
			indexPlus();
		} else if (you[index] == com[index]) {
			information.setText("승부하셨습니다! 상대방 카드 : " + com[index] +  " 나의 카드 : " + you[index] + " 비겼습니다.");
			indexPlus();
		} else if (you[index] < com[index]) {
			information.setText("승부하셨습니다! 상대방 카드 : " + com[index] + " 나의 카드 : " + you[index] + " 졌습니다.");
			comScore++;
			indexPlus();
		}
	}

	public void indexPlus() {
		if (index <= 9) {
			this.index++;
		}
		if (index <= 9) {
			youinfo.setIcon(icon[com[index]]);
		}
		gameEnd();
	}

	public void myStop() {
		information.setText("당신은 포기하셨으므로 패배입니다. 나의 패 : " + you[index] + ", 상대방 패 : " + com[index] + " 당신의 패배입니다.");

		comScore++;
		indexPlus();
	}

	public void comStop() {
		information
				.setText("상대방이 포기하셨으므로 당신의 승리입니다. 나의 패 : " + you[index] + ", 상대방 패 : " + com[index] + " 당신의 승리입니다.");
		myScore++;
		indexPlus();
	}

	public void gameEnd() {
		if (index == 10) {
			youinfo.setIcon(icon[0]);
			information.setText("나의 스코어 : " + myScore + " 상대방 스코어 : " + comScore);
		}
	}

	public void comGoGo() {
		if (index <= 9) {
			int value = randomValue();
			int getCom = getCom(index);
			if (value == 0 && com[index] > 4) {
				vs();
				btn3.setVisible(true);
				btn4.setVisible(true);
			} else if(value == 0 && com[index] < 4)  {
				comStop();
				btn3.setVisible(true);
				btn4.setVisible(true);
			} else if (value == 1 && com[index] < 4) {
				comStop();
				btn3.setVisible(true);
				btn4.setVisible(true);
			} else {
				vs();
				btn3.setVisible(true);
				btn4.setVisible(true);
			}
			

		}

	}

}
