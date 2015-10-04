package hr.patrik;

import hr.patrik.PongPlus.DIR;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Ball {

	private int PERIOD = 1;

	private int x;
	private int y;
	
	private int tableX;
	private int tableY;
	private int tableWidth;

	private int dx;
	private int dy;

	private DIR direction;
	private int CLOCK;
	private Image image;

	public Ball (int x, int y, int tableX, int tableY, int tableWidth) {
		this.x = x;
		this.y = y;
		this.tableX = tableX;
		this.tableY = tableY;
		this.tableWidth = tableWidth;

		direction = DIR.S;
		CLOCK = 0;

		ImageIcon ii = new ImageIcon(getClass().getResource("/resources/ball.png"));
		image = ii.getImage();
	}

	public int getX () {
		return x;
	}

	public int getY () {
		return y;
	}

	public Image getImage() {
		return image;
	}

	public void move () {

		CLOCK++;
		
		//Check collision
		int newX;
		int newY;
		
		if (CLOCK % PERIOD == 0) {
			newX = x+dx;
			newY = y+dy;
		}
		else {
			newX = x;
			newY = y;
		}
		
		int ballWidth = image.getWidth(null);
		
		if (direction == DIR.S)
			if (newX>tableX-ballWidth && newX < tableX+tableWidth && newY+ballWidth == tableY)
				direction = DIR.N;
		
		setMove();

		if (CLOCK % PERIOD == 0) {
			x+= dx;
			y+= dy;
		}
	}

	public void setMove() {
		switch (direction) {
		case E: dx = 1; dy = 0;
		break;
		case N: dx = 0; dy = -1;
		break;
		case NE: dx = 1; dy = -1;
		break;
		case NW: dx = -1; dy = -1;
		break;
		case S: dx = 0; dy = 1;
		break;
		case SE: dx = 1; dy = 1;
		break;
		case SW: dx = -1; dy = 1;
		break;
		case W: dx = -1; dy = 0;
		break;
		default: dx = 0; dy = 0;
		break;
		}
	}

}
