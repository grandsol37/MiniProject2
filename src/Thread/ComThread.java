package Thread;

import java.util.Random;

import logic.Poker;

public class ComThread extends Thread {
	private Poker pk;
	int index;

	Random rnd = new Random();
	
	public ComThread(Poker pk) {
		// TODO Auto-generated constructor stub
		this.pk = pk;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(rnd.nextInt(5000));
			pk.comGoGo();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


}
