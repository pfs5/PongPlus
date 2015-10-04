package hr.patrik;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Table {

	private int x;
	private int y;
	private int dxL;
	private int dxR;

	Image image;

	public Table (int x, int y) {
		this.x = x;
		this.y = y;

		//Init image
		ImageIcon ii = new ImageIcon(getClass().getResource("/resources/table.png"));
		image = ii.getImage();
	}

	public int getX () {
		return x;
	}

	public int getY () {
		return y;
	}

	public Image getImage () {
		return image;
	}
	
	public void move () {
		x+= (dxL+dxR);
	}

	public void keyPressed (KeyEvent e) {
		int key = e.getKeyCode();

		switch (key) {

		case KeyEvent.VK_A: dxL = -1;
		break;
		case KeyEvent.VK_D: dxR = 1;
		break;

		case KeyEvent.VK_LEFT: dxL = -1;
		break;
		case KeyEvent.VK_RIGHT: dxR = 1;
		break;
		}
	}

	public void keyReleased (KeyEvent e) {
		int key = e.getKeyCode();

		switch (key) {

		case KeyEvent.VK_A: dxL = 0;
		break;
		case KeyEvent.VK_D: dxR = 0;
		break;

		case KeyEvent.VK_LEFT: dxL = 0;
		break;
		case KeyEvent.VK_RIGHT: dxR = 0;
		break;
		}
	}

}
