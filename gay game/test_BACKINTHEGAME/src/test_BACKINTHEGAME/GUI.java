package test_BACKINTHEGAME;

import javax.swing.JFrame;

public class GUI {
	
	
	public GUI() {
		
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setTitle("POKEMON RIPOFF");
		
		gamePanel gamePanel = new gamePanel();
		window.add(gamePanel);
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.startGameThread();
		
		
	}

	public static void main(String[] args) {
		new GUI();

	}

}
