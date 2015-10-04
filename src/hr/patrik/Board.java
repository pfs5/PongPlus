package hr.patrik;

import hr.patrik.PongPlus.STATE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener{
	
	private int WIDTH;
	private int HEIGTH;
	private int DELAY = 20;
	private int CLOCK;
	private STATE state;
	
	Timer timer;
	
	public Board (int WIDTH, int HEIGTH) {
		this.WIDTH = WIDTH;
		this.HEIGTH = HEIGTH;
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
		state = STATE.GAME;
	}

	////////////////////////////	DRAWING		///////////////////////////
	@Override
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		if (state == STATE.GAME)
			paintGame(g2d);
		if (state == STATE.PAUSE)
			paintPause(g2d);
		if (state == STATE.END)
			paintEnd(g2d);
	}
	
	public void paintGame (Graphics2D g2d) {
		
	}
	
	public void paintPause (Graphics2D g2d) {
		
	}
	
	public void paintEnd (Graphics2D g2d) {
		
	}
	
	////////////////////////////	PERIODIC ACTION		//////////////////
	@Override
	public void actionPerformed (ActionEvent e) {
	}
	
	///////////////////////////		KEY LISTENER		//////////////////
	private class TAdapter extends KeyAdapter {
		
		@Override
		public void keyReleased (KeyEvent e) {
			
		}
		
		@Override
		public void keyPressed (KeyEvent e) {
			
		}
	}

}
