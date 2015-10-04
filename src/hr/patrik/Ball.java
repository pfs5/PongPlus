package hr.patrik;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Ball {
	
	private int x;
	private int y;
	private Image image;
	
	public Ball (int x, int y) {
		this.x = x;
		this.y = y;
		ImageIcon ii = new ImageIcon(getClass().getResource("/resources/ball.png"));
		image = ii.getImage();
	}

}
