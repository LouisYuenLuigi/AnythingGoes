package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.IOException;

import javax.imageio.ImageIO;

import test_BACKINTHEGAME.KeyHandler;

import test_BACKINTHEGAME.gamePanel;

public class player extends entity {
	
	gamePanel gPanel;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	public player(gamePanel gPanel, KeyHandler keyH) {
		this.gPanel = gPanel;
		this.keyH = keyH;
		screenX = gPanel.screenWidth / 2 - gPanel.tileSize / 2;
		screenY = gPanel.screenHeight / 2 - gPanel.tileSize / 2;
		setDefaultValues();
		getPlayerImage();
		solidArea = new Rectangle(8,16, 32, 32);
		
	}
	 public void setDefaultValues() {
		 worldX = gPanel.tileSize * 23;
		 worldY = gPanel.tileSize * 22;
		 speed = 4;
		 direction = "right";
	 }
	 
	 public void getPlayerImage() {
		 try {
			 
//			 up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
//			 up2 = ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));
//			 down1 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
//			 down2 = ImageIO.read(getClass().getResourceAsStream("/player/down2.png"));
//			 left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
//			 left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
//			 right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
//			 right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
			 up1 = ImageIO.read(getClass().getResourceAsStream("/player/warrior.png"));
			 up2 = ImageIO.read(getClass().getResourceAsStream("/player/warrior.png"));
			 down1 = ImageIO.read(getClass().getResourceAsStream("/player/warrior.png"));
			 down2 = ImageIO.read(getClass().getResourceAsStream("/player/warrior.png"));
			 left1 = ImageIO.read(getClass().getResourceAsStream("/player/warrior.png"));
			 left2 = ImageIO.read(getClass().getResourceAsStream("/player/warrior.png"));
			 right1 = ImageIO.read(getClass().getResourceAsStream("/player/warrior.png"));
			 right2 = ImageIO.read(getClass().getResourceAsStream("/player/warrior.png"));
			 
		 }catch(IOException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void update() {
		 
		 if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
			 
			 if(keyH.upPressed == true) {
//				y -= speed;
				if(keyH.leftPressed == true) {
					direction = "leftUp";
				}
				else if(keyH.rightPressed == true) {		
					direction = "rightUp";
				}
				else {
					direction = "up";
				}
			}
			else if(keyH.downPressed == true) {
//				y += speed;
				if(keyH.leftPressed == true) {
					direction = "leftDown";
				}
				else if(keyH.rightPressed == true) {
					direction = "rightDown";
				}
				else {
					direction = "down";
				}
			}
				
			else if(keyH.leftPressed == true) {
				direction = "left";
			}
			else if(keyH.rightPressed == true) {
				direction = "right";
			}
			 
			 collisionOn = false;
			 gPanel.collisionChecker.checkTile(this);
			 
			 if (collisionOn == false) {
				 switch(direction) {
				 case "up":
					 worldY -= speed;
					 break;
				 case "down":
					 worldY += speed;
					 break;
				 case "left":
					 worldX -= speed;
					 break;
				 case "right":
					 worldX += speed;
					 break;
				 case "leftUp":
					 worldY -= (speed / 4 * 3);
					 worldX -= (speed / 4 * 3);
					 break;
				 case "leftDown":
					 worldY += (speed / 4 * 3);
					 worldX -= (speed / 4 * 3);
					 break;
				 case "rightUp":
					 worldY -= (speed / 4 * 3);
					 worldX += (speed / 4 * 3);
					 break;
				 case "rightDown":
					 worldY += (speed / 4 * 3);
					 worldX += (speed / 4 * 3);
					 break;
					 

				 }
			 }
			 
			spriteCounter++;
			if(spriteCounter > 10) {
				if(spriteNum == 1) {
					spriteNum = 2;
				 }
				 else if(spriteNum == 2) {
					 spriteNum = 1;
				 }
				 spriteCounter = 0;
			 }
			 
		 }
		 
		 
	 }
	 
	 public void draw(Graphics2D g2) {
//			g2.setColor(Color.white);
//			g2.fillRect(x, y, gPanel.tileSize, gPanel.tileSize);
		 
		 BufferedImage image = null;
		 
		 switch(direction) {
		 case "up":
			 if(spriteNum == 1) {
				 image = up1;				 
			 }
			 else if(spriteNum == 2) {
				 image = up2;
			 }
			 break;
		 case "down":
			 if(spriteNum == 1) {
				 image = down1;				 
			 }
			 else if(spriteNum == 2) {
				 image = down2;
			 }
			 break;
		 case "left":
		 case "leftUp":
		 case "leftDown":
			 if(spriteNum == 1) {
				 image = left1;				 
			 }
			 else if(spriteNum == 2) {
				 image = left2;
			 }
			 break;
		 case "right":
		 case "rightUp":
		 case "rightDown":
			 if(spriteNum == 1) {
				 image = right1;				 
			 }
			 else if(spriteNum == 2) {
				 image = right2;
			 }
			 break;
		 }
		 
		 g2.drawImage(image, screenX, screenY, gPanel.tileSize, gPanel.tileSize, null);
		 g2.setColor(Color.red);
		 g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
	 }

}
