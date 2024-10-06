package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.IOException;

import javax.imageio.ImageIO;

import test_BACKINTHEGAME.KeyHandler;

import test_BACKINTHEGAME.gamePanel;

public class player extends entity {
	
	gamePanel gPanel;
	KeyHandler keyH;
	
	public player(gamePanel gPanel, KeyHandler keyH) {
		this.gPanel = gPanel;
		this.keyH = keyH;
		setDefaultValues();
		getPlayerImage();
		
	}
	 public void setDefaultValues() {
		 x = 100;
		 y = 100;
		 speed = 4;
		 direction = "right";
	 }
	 
	 public void getPlayerImage() {
		 try {
			 
			 up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
			 up2 = ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));
			 down1 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
			 down2 = ImageIO.read(getClass().getResourceAsStream("/player/down2.png"));
			 left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
			 left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
			 right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
			 right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
			 
		 }catch(IOException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void update() {
		 if(keyH.upPressed == true) {
//				y -= speed;
				if(keyH.leftPressed == true) {
					y -= (speed / 4 * 3);
					x -= (speed / 4 * 3);
					direction = "left";
				}
				else if(keyH.rightPressed == true) {
					y -= (speed / 4 * 3);
					x += (speed / 4 * 3);		
					direction = "right";
				}
				else {
					y -= speed;
					direction = "up";
				}
			}
			else if(keyH.downPressed == true) {
//				y += speed;
				
				if(keyH.leftPressed == true) {
					y += (speed / 4 * 3);
					x -= (speed / 4 * 3);
					direction = "left";
				}
				else if(keyH.rightPressed == true) {
					y += (speed / 4 * 3);
					x += (speed / 4 * 3);
					direction = "right";
				}
				else {
					y += speed;
					direction = "down";
				}
			}
			
			else if(keyH.leftPressed == true) {
				x -= speed;
				direction = "left";
			}
			else if(keyH.rightPressed == true) {
				x += speed;
				direction = "right";
			}
	 }
	 
	 public void draw(Graphics2D g2) {
//			g2.setColor(Color.white);
//			g2.fillRect(x, y, gPanel.tileSize, gPanel.tileSize);
		 
		 BufferedImage image = null;
		 
		 switch(direction) {
		 case "up":
			 image = up1;
			 break;
		 case "down":
			 image = down1;
			 break;
		 case "left":
			 image = left1;
			 break;
		 case "right":
			 image = right1;
			 break;
		 }
		 
		 g2.drawImage(image, x, y, gPanel.tileSize, gPanel.tileSize, null);
	 }

}
