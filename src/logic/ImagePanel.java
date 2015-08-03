package logic;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	Image image;
	
	public ImagePanel() {
		image = new ImageIcon("src/test/test.png").getImage();
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		setOpaque(false);
		super.paintComponent(g);
	}
	
	

}
