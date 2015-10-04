package hr.patrik;

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

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener{
	
	private int WIDTH;
	private int HEIGHT;
	private int DELAY = 5;
	private int CLOCK;
	private int TIME;
	private int SCORE;
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
		state = STATE.GAME;

		TABLE_Y = HEIGHT - TABLE_H - 60;
		tableX = TABLE_X;
		tableY = TABLE_Y;
		table = new Table(tableX, tableY);
		
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
		tableX = table.getX();
		tableY = table.getY();
		tableImage = table.getImage();
		
		g2d.drawImage(tableImage, tableX, tableY, this);
	}
	
	public void paintPause (Graphics2D g2d) {
		
	}
	
	public void paintEnd (Graphics2D g2d) {
		
	}
	
	public void paintEdge(Graphics2D g2d) {

		//Rectangle
		int edgeX = 50;
		int edgeY = 50;
		
		g2d.drawImage(edgeImage, edgeX, edgeY, this);
		
		//Text
		int scoreX = 80;
		int scoreY = 30;
		
		Font font = new Font("MonoSpaced", Font.PLAIN, 18);
		g2d.setColor(Color.WHITE);
		g2d.setFont(font);
		g2d.drawString("SCORE:  " + SCORE, scoreX, scoreY);
		
		int timeX = 300;
		int timeY = 30;
		
		g2d.drawString("TIME:  " + TIME, timeX, timeY);
	}
	
	////////////////////////////	PERIODIC ACTION		//////////////////
	@Override
	public void actionPerformed (ActionEvent e) {
		//Time
		CLOCK++;
		TIME = CLOCK*DELAY/1000;
		
		//Move sprites
		table.move();
		
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
			if (state == STATE.GAME) 
				table.keyPressed(e);
		}
	}

}
