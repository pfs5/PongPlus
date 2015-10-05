package hr.patrik;

/*
 * 
 * TODO
 * mijenjanje smijera
 * 
 * 
 * 
 */

import hr.patrik.PongPlus.STATE;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener{

	private int WIDTH;
	private int HEIGHT;
	private int DELAY = 3;

	private int CLOCK;
	private int TIME;
	private int SCORE;
	private int BALL_NUMBER;
	private int BALL_CLOCK;
	private int BALL_PERIOD = 1000;

	private STATE state;

	private int TABLE_H = 15;
	private int TABLE_X = 100;
	private int TABLE_Y;

	//Locations
	int tableX;
	int tableY;

	Timer timer;
	Image tableImage;
	Image edgeImage;
	Table table;

	ArrayList<Ball> balls;

	public Board (int WIDTH, int HEIGHT) {
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		initBoard();
	}

	public void initBoard () {
		//Necessary part
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.BLACK);
		setDoubleBuffered(true);

		timer = new Timer(DELAY, this);
		timer.start();

		initGame();
	}

	public void initGame () {
		CLOCK = 0;
		TIME = 0;
		SCORE = 0;
		BALL_NUMBER = 0;
		BALL_CLOCK = BALL_PERIOD;
		state = STATE.GAME;

		TABLE_Y = HEIGHT - TABLE_H - 60;
		tableX = TABLE_X;
		tableY = TABLE_Y;
		table = new Table(tableX, tableY);
		balls = new ArrayList<Ball>();

		ImageIcon ii = new ImageIcon(getClass().getResource("/resources/frame.png"));
		edgeImage = ii.getImage();

	}

	////////////////////////////	DRAWING		///////////////////////////
	@Override
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		paintEdge(g2d);

		if (state == STATE.GAME)
			paintGame(g2d);
		if (state == STATE.PAUSE)
			paintPause(g2d);
		if (state == STATE.END)
			paintEnd(g2d);
	}

	public void paintGame (Graphics2D g2d) {

		//Draw table
		tableX = table.getX();
		tableY = table.getY();
		tableImage = table.getImage();

		g2d.drawImage(tableImage, tableX, tableY, this);

		//Draw balls
		for (Ball current : balls) {
			int ballX = current.getX();
			int ballY = current.getY();
			Image ballImage = current.getImage();

			g2d.drawImage(ballImage, ballX, ballY, this);
		}
	}

	public void paintPause (Graphics2D g2d) {

	}

	public void paintEnd (Graphics2D g2d) {
		int gameOverX = 160;
		int gameOverY = 400;

		Font font = new Font("MonoSpaced", Font.PLAIN, 30);
		g2d.setColor(Color.WHITE);
		g2d.setFont(font);
		g2d.drawString("GAME OVER", gameOverX, gameOverY);
	}

	public void paintEdge(Graphics2D g2d) {

		//Rectangle
		int edgeX = 50;
		int edgeY = 50;

		g2d.drawImage(edgeImage, edgeX, edgeY, this);

		//Text
		Font font = new Font("MonoSpaced", Font.PLAIN, 18);
		g2d.setColor(Color.WHITE);
		g2d.setFont(font);

		int scoreX = 60;
		int scoreY = 30;
		g2d.drawString("SCORE:  " + SCORE, scoreX, scoreY);

		int ballCountX = scoreX+150;	
		int ballCountY = 30;
		g2d.drawString(BALL_NUMBER + "x", ballCountX, ballCountY);

		int timeX = scoreX+220;
		int timeY = 30;
		g2d.drawString("TIME:  " + TIME, timeX, timeY);

		//Ball ready
		int ballReadyX = scoreX+350;
		int ballReadyY = 17;
		ImageIcon ii = new ImageIcon(getClass().getResource("/resources/ball.png"));
		Image ballImage = ii.getImage();
		if (BALL_CLOCK>BALL_PERIOD)
			g2d.drawImage(ballImage, ballReadyX, ballReadyY, this);

	}

	////////////////////////////	PERIODIC ACTION		//////////////////
	@Override
	public void actionPerformed (ActionEvent e) {
		if (state==STATE.GAME) {
			//Time
			CLOCK++;
			BALL_CLOCK++;
			TIME = CLOCK*DELAY/1000;

			//Move sprites
			table.move();
			int tabledx = table.getdx();
			for (Ball current : balls) {
				current.setTable(tableX, tableY, tabledx);
				current.move();
				if (current.isHit()==1) {
					current.reset();
					SCORE+=BALL_NUMBER;
				}
			}
			removeBall();
		}

		repaint();
	}

	///////////////////////////		KEY LISTENER		//////////////////
	private class TAdapter extends KeyAdapter {

		@Override
		public void keyReleased (KeyEvent e) {
			if (state == STATE.GAME)
				table.keyReleased(e);
		}

		@Override
		public void keyPressed (KeyEvent e) {
			if (state == STATE.GAME) { 
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_SPACE)
					addBall();
				table.keyPressed(e);
			}
			if (state == STATE.END){
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_SPACE)
					initGame();
			}
		}
	}

	public void addBall () {

		if (BALL_CLOCK>BALL_PERIOD) {
			tableX = table.getX();
			tableY = table.getY();

			Ball newBall = new Ball(WIDTH/2, 0, tableImage.getWidth(null), tableImage.getHeight(null), WIDTH);
			balls.add(newBall);
			BALL_NUMBER++;
			BALL_CLOCK = 0;
		}
	}

	public void removeBall () {
		for (int i=0; i<balls.size(); i++) {
			Ball current = balls.get(i);
			if (current.isVisible()==0) {
				state = STATE.END;
				return;
			}
		}
	}

}
