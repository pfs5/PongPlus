package hr.patrik;

import hr.patrik.PongPlus.DIR;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Ball {

	private int PERIOD = 1;
	private int EDGE = 50;

	private int x;
	private int y;
	
	private int tableX;
	private int tableY;
	private int tabledx;
	private int tableWidth;
	private int tableHeight;

	private int WIDTH;
	
	private int dx;
	private int dy;

	private int hit;
	private int visible;
	
	private DIR direction;
	private int CLOCK;
	private Image image;
	private Random random;

	public Ball (int x, int y, int tableWidth, int tableHeight, int WIDTH) {
		this.x = x;
		this.y = y;
		this.tableWidth = tableWidth;
		this.tableHeight = tableHeight;
		this.WIDTH = WIDTH;
		
		hit = 0;
		visible = 1;
		CLOCK = 0;

		random = new Random();
		int directionIndex = random.nextInt(3);
		if (directionIndex==0)
			direction = DIR.SW;
		if (directionIndex==1)
			direction = DIR.S;
		if (directionIndex==2)
			direction = DIR.SE;
		
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
	
	public int isVisible() {
		return visible;
	}
	
	public int isHit () {
		return hit;
	}
	
	public void setTable (int tableX, int tableY, int tabledx) {
		this.tableX = tableX;
		this.tableY = tableY;
		
		this.tabledx = tabledx;
	}
	
	public void reset () {
		hit = 0;
	}

	public void move () {

		CLOCK++;
		
		checkCollision();
		setMove();

		if (CLOCK % PERIOD == 0) {
			x+= dx;
			y+= dy;
		}
	}

	public void checkCollision () {
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
		
		if (newY+ballWidth>tableY+tableHeight) {
			visible = 0;
			return;
		}
		
		if (direction == DIR.S) {
			if (newX>tableX-ballWidth && newX<tableX+tableWidth && newY+ballWidth==tableY){		//table collision
				
				hit=1;
				
				if (tabledx == -1)
					direction = DIR.NW;
				else if (tabledx == 0)
					direction = DIR.N;
				else if (tabledx == 1)
					direction = DIR.NE;
			}
		}
		
		if (direction == DIR.N) {
			if (newY<EDGE)				//top side
				direction = DIR.S;
		}
		
		if (direction == DIR.SE) {
			if (newX>tableX-ballWidth && newX<tableX+tableWidth && newY+ballWidth==tableY){		//table collision
				
				hit=1;
				
				if (tabledx == -1)
					direction = DIR.N;
				else if (tabledx == 0)
					direction = DIR.NW;
				else if (tabledx == 1)
					direction = DIR.NE;
			}
			if (newX+ballWidth==WIDTH-EDGE)
				direction = DIR.SW;
		}
		
		if (direction == DIR.SW) {
			if (newX>tableX-ballWidth && newX<tableX+tableWidth && newY+ballWidth==tableY){		//table collision
				
				hit=1;
				
				if (tabledx == -1)
					direction = DIR.NW;
				else if (tabledx == 0)
					direction = DIR.NE;
				else if (tabledx == 1)
					direction = DIR.N;
			}
			if (newX==EDGE)
				direction = DIR.SE;
		}
		
		if (direction == DIR.NE) {
			if (newY==EDGE)
				direction = DIR.SE;
			if (newX+ballWidth==WIDTH-EDGE)
				direction = DIR.NW;
		}
		
		if (direction == DIR.NW) {
			if (newY==EDGE)
				direction = DIR.SW;
			if (newX==EDGE)
				direction = DIR.NE;
			
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
