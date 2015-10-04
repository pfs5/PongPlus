package hr.patrik;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class PongPlus extends JFrame {
	 
	public enum STATE {
		GAME,
		PAUSE,
		END
	}
	
	private final int WIDTH = 500;
	private final int HEIGHT = 800;
	
	public PongPlus() {
		add(new Board(WIDTH, HEIGHT));
		setSize(WIDTH,HEIGHT);
		setResizable(false);
		setTitle("PongPlus Alpha");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public static void main (String [] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				PongPlus game = new PongPlus();
				game.setVisible(true);
			}
		});
	}

}
