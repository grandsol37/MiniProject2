package logic;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class TCanvas extends JPanel {
	
	Image img;
	
	public TCanvas(Image img) {
		// TODO Auto-generated constructor stub
		this.img = img;
	}
	
	
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		//g.drawLine(100, 100, 200, 200);
		//g.drawImage(img, 0, 0, 1300	, 800, this);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		setOpaque(false);
	}

}
