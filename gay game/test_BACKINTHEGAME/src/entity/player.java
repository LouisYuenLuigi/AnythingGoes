package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import test_BACKINTHEGAME.KeyHandler;

import test_BACKINTHEGAME.gamePanel;

public class player extends entity {
	
	gamePanel gPanel;
	KeyHandler keyH;
	
	public player(gamePanel gPanel, KeyHandler keyH) {
		this.gPanel = gPanel;
		this.keyH = keyH;
		setDefaultValues();
		
	}
	 public void setDefaultValues() {
		 x = 100;
		 y = 100;
		 speed = 4;
	 }
	 
	 public void update() {
		 if(keyH.upPressed == true) {
//				y -= speed;
				if(keyH.leftPressed == true) {
					y -= (speed / 4 * 3);
					x -= (speed / 4 * 3);
				}
				else if(keyH.rightPressed == true) {
					y -= (speed / 4 * 3);
					x += (speed / 4 * 3);				
				}
				else {
					y -= speed;
				}
			}
			else if(keyH.downPressed == true) {
//				y += speed;
				
				if(keyH.leftPressed == true) {
					y += (speed / 4 * 3);
					x -= (speed / 4 * 3);
				}
				else if(keyH.rightPressed == true) {
					y += (speed / 4 * 3);
					x += (speed / 4 * 3);				
				}
				else {
					y += speed;
				}
			}
			
			else if(keyH.leftPressed == true) {
				x -= speed;
			}
			else if(keyH.rightPressed == true) {
				x += speed;
			}
	 }
	 
	 public void draw(Graphics2D g2) {
			g2.setColor(Color.white);
			g2.fillRect(x, y, gPanel.tileSize, gPanel.tileSize);
	 }

}
