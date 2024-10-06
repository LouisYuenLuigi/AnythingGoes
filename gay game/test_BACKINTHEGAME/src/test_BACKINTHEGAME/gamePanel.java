package test_BACKINTHEGAME;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Timer;

import javax.swing.JPanel;

import entity.player;

public class gamePanel extends JPanel implements Runnable {
	//SCREEN SETTINGS
	final int originalTileSize = 16;
	final int scale = 3;
	public int tileSize = originalTileSize * scale;
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol;
	final int screenHeight = tileSize * maxScreenRow;
	
	final int fps = 60;
	
	Thread gameThread;
	KeyHandler keyH = new KeyHandler();
	player player = new player(this, keyH);
	
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	
	public gamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void run() {
		
		double drawInterval = 1000000000/fps;
		double delta = 0;
		long lastTime = System.nanoTime();
		long timer = 0;
		long currentTime;
		int drawCount = 0;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000) {
				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
			
		}
	}
	
	public void update() {
		
		player.update();
		
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		player.draw(g2);
		
		g2.dispose();
	}

}


//public void run() {
//
//double drawInterval = 1000000000/fps;
//double nextDrawTime = System.nanoTime() + drawInterval;
//
//while(gameThread != null) {
//	
////	long currentTime = System.nanoTime();
////	System.out.println("Current Time is: " + currentTime);
//	
//	update();
//
//	repaint();
//	
//	
//	try {
//		double remainingTime = nextDrawTime - System.nanoTime();
//		remainingTime = remainingTime/1000000;
//		if(remainingTime < 0) {
//			remainingTime = 0;
//		}
//		Thread.sleep((long) remainingTime);
//		
//		nextDrawTime += drawInterval;
//	} catch (InterruptedException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	
//}
//	
//}


















