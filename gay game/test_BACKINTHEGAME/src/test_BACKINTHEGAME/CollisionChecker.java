package test_BACKINTHEGAME;

import entity.entity;

public class CollisionChecker {
	
	gamePanel gPanel;
	
	public CollisionChecker(gamePanel gPanel) {
		this.gPanel = gPanel;
	}

	public void checkTile(entity entity) {
		
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX / gPanel.tileSize;
		int entityRightCol = entityRightWorldX / gPanel.tileSize;
		int entityTopRow = entityTopWorldY / gPanel.tileSize;
		int entityBottomRow = entityBottomWorldY / gPanel.tileSize;
		
		int tileNum1, tileNum2, tileNum3;
		
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed) / gPanel.tileSize;
			tileNum1 = gPanel.tileManager.mapTileNumber[entityLeftCol][entityTopRow];
			tileNum2 = gPanel.tileManager.mapTileNumber[entityRightCol][entityTopRow];
			
			if(gPanel.tileManager.tile[tileNum1].collision == true || gPanel.tileManager.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed) / gPanel.tileSize;
			tileNum1 = gPanel.tileManager.mapTileNumber[entityLeftCol][entityBottomRow];
			tileNum2 = gPanel.tileManager.mapTileNumber[entityRightCol][entityBottomRow];
			
			if(gPanel.tileManager.tile[tileNum1].collision == true || gPanel.tileManager.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed) / gPanel.tileSize;
			tileNum1 = gPanel.tileManager.mapTileNumber[entityLeftCol][entityTopRow];
			tileNum2 = gPanel.tileManager.mapTileNumber[entityLeftCol][entityBottomRow];
			
			if(gPanel.tileManager.tile[tileNum1].collision == true || gPanel.tileManager.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed) / gPanel.tileSize;
			tileNum1 = gPanel.tileManager.mapTileNumber[entityRightCol][entityTopRow];
			tileNum2 = gPanel.tileManager.mapTileNumber[entityRightCol][entityBottomRow];
			
			if(gPanel.tileManager.tile[tileNum1].collision == true || gPanel.tileManager.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
			
		case "leftUp":
			entityTopRow = (entityTopWorldY - entity.speed) / gPanel.tileSize;
			entityLeftCol = (entityLeftWorldX - entity.speed) / gPanel.tileSize;
			entityBottomRow = (entityBottomWorldY - entity.speed) / gPanel.tileSize;
			entityRightCol = (entityRightWorldX - entity.speed) / gPanel.tileSize;
			
			System.out.println(17 / 4 * 4);
			
			tileNum1 = gPanel.tileManager.mapTileNumber[entityLeftCol][entityTopRow];
			tileNum2 = gPanel.tileManager.mapTileNumber[entityRightCol][entityTopRow];
			tileNum3 = gPanel.tileManager.mapTileNumber[entityLeftCol][entityBottomRow];
			
			if(gPanel.tileManager.tile[tileNum1].collision == true && gPanel.tileManager.tile[tileNum2].collision == true && gPanel.tileManager.tile[tileNum3].collision == true) {
				entity.collisionOn = true;
			}
			else if(gPanel.tileManager.tile[tileNum1].collision == true && gPanel.tileManager.tile[tileNum2].collision == false && gPanel.tileManager.tile[tileNum3].collision == false) {
				if(entityTopWorldY - entityTopRow * gPanel.tileSize > entityLeftWorldX - entityLeftCol * gPanel.tileSize) {
					entity.direction = "left";
				}
				else if(entityTopWorldY - entityTopRow * gPanel.tileSize < entityLeftWorldX - entityLeftCol * gPanel.tileSize) {
					entity.direction = "up";
				}
				else {
					entity.collisionOn = true;
				}
			}
			else if(gPanel.tileManager.tile[tileNum2].collision == false && gPanel.tileManager.tile[tileNum3].collision == true) {
				entity.direction = "up";
			}
			else if(gPanel.tileManager.tile[tileNum2].collision == true && gPanel.tileManager.tile[tileNum3].collision == false) {
				entity.direction = "left";
			}
			else if(gPanel.tileManager.tile[tileNum1].collision == false && gPanel.tileManager.tile[tileNum2].collision == true && gPanel.tileManager.tile[tileNum3].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "rightUp":
			entityTopRow = (entityTopWorldY - entity.speed) / gPanel.tileSize;
			entityRightCol = (entityRightWorldX + entity.speed) / gPanel.tileSize;
			entityBottomRow = (entityBottomWorldY - entity.speed) / gPanel.tileSize;
			entityLeftCol = (entityLeftWorldX + entity.speed) / gPanel.tileSize;

			tileNum1 = gPanel.tileManager.mapTileNumber[entityRightCol][entityTopRow];
			tileNum2 = gPanel.tileManager.mapTileNumber[entityRightCol][entityBottomRow];
			tileNum3 = gPanel.tileManager.mapTileNumber[entityLeftCol][entityTopRow];
			
			if(gPanel.tileManager.tile[tileNum1].collision == true && gPanel.tileManager.tile[tileNum2].collision == true && gPanel.tileManager.tile[tileNum3].collision == true) {
				entity.collisionOn = true;
			}
			else if(gPanel.tileManager.tile[tileNum1].collision == true && gPanel.tileManager.tile[tileNum2].collision == false && gPanel.tileManager.tile[tileNum3].collision == false) {
				if(entityTopWorldY - entityTopRow * gPanel.tileSize > (entityRightCol + 1) * gPanel.tileSize - entityRightWorldX) {
					entity.direction = "right";
				}
				else if(entityTopWorldY - entityTopRow * gPanel.tileSize < (entityRightCol + 1) * gPanel.tileSize - entityRightWorldX) {
					entity.direction = "up";
				}
				else {
					entity.collisionOn = true;
				}
			}
			else if(gPanel.tileManager.tile[tileNum2].collision == false && gPanel.tileManager.tile[tileNum3].collision == true) {
				entity.direction = "right";
			}
			else if(gPanel.tileManager.tile[tileNum2].collision == true && gPanel.tileManager.tile[tileNum3].collision == false) {
				entity.direction = "up";
			}
			else if(gPanel.tileManager.tile[tileNum1].collision == false && gPanel.tileManager.tile[tileNum2].collision == true && gPanel.tileManager.tile[tileNum3].collision == true) {
				entity.collisionOn = true;
			}

			break;
		case "leftDown":
			entityBottomRow = (entityBottomWorldY + entity.speed) / gPanel.tileSize;
			entityLeftCol = (entityLeftWorldX - entity.speed) / gPanel.tileSize;
			entityTopRow = (entityTopWorldY + entity.speed) / gPanel.tileSize;
			entityRightCol = (entityRightWorldX - entity.speed) / gPanel.tileSize;
			


			tileNum1 = gPanel.tileManager.mapTileNumber[entityLeftCol][entityBottomRow];
			tileNum2 = gPanel.tileManager.mapTileNumber[entityLeftCol][entityTopRow];
			tileNum3 = gPanel.tileManager.mapTileNumber[entityRightCol][entityBottomRow];
			
			if(gPanel.tileManager.tile[tileNum1].collision == true && gPanel.tileManager.tile[tileNum2].collision == true && gPanel.tileManager.tile[tileNum3].collision == true) {
				entity.collisionOn = true;
			}
			else if(gPanel.tileManager.tile[tileNum1].collision == true && gPanel.tileManager.tile[tileNum2].collision == false && gPanel.tileManager.tile[tileNum3].collision == false) {
				if((entityBottomRow + 1) * gPanel.tileSize - entityBottomWorldY > entityLeftWorldX - entityLeftCol * gPanel.tileSize) {
					entity.direction = "left";
				}
				else if((entityBottomRow + 1) * gPanel.tileSize - entityBottomWorldY < entityLeftWorldX - entityLeftCol * gPanel.tileSize) {
					entity.direction = "down";
				}
				else {
					entity.collisionOn = true;
				}
			}
			else if(gPanel.tileManager.tile[tileNum2].collision == false && gPanel.tileManager.tile[tileNum3].collision == true) {
				entity.direction = "left";
			}
			else if(gPanel.tileManager.tile[tileNum2].collision == true && gPanel.tileManager.tile[tileNum3].collision == false) {
				entity.direction = "down";
			}
			else if(gPanel.tileManager.tile[tileNum1].collision == false && gPanel.tileManager.tile[tileNum2].collision == true && gPanel.tileManager.tile[tileNum3].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "rightDown":
			entityBottomRow = (entityBottomWorldY + entity.speed) / gPanel.tileSize;
			entityRightCol = (entityRightWorldX + entity.speed) / gPanel.tileSize;
			entityTopRow = (entityTopWorldY + entity.speed) / gPanel.tileSize;
			entityLeftCol = (entityLeftWorldX + entity.speed) / gPanel.tileSize;

			tileNum1 = gPanel.tileManager.mapTileNumber[entityRightCol][entityBottomRow];
			tileNum2 = gPanel.tileManager.mapTileNumber[entityRightCol][entityTopRow];
			tileNum3 = gPanel.tileManager.mapTileNumber[entityLeftCol][entityBottomRow];
			
			if(gPanel.tileManager.tile[tileNum1].collision == true && gPanel.tileManager.tile[tileNum2].collision == true && gPanel.tileManager.tile[tileNum3].collision == true) {
				entity.collisionOn = true;
			}
			else if(gPanel.tileManager.tile[tileNum1].collision == true && gPanel.tileManager.tile[tileNum2].collision == false && gPanel.tileManager.tile[tileNum3].collision == false) {
				if((entityBottomRow + 1) * gPanel.tileSize - entityBottomWorldY > (entityRightCol + 1) * gPanel.tileSize - entityRightWorldX) {
					entity.direction = "right";
				}
				else if((entityBottomRow + 1) * gPanel.tileSize - entityBottomWorldY < (entityRightCol + 1) * gPanel.tileSize - entityRightWorldX) {
					entity.direction = "down";
				}
				else {
					entity.collisionOn = true;
				}
			}
			else if(gPanel.tileManager.tile[tileNum2].collision == false && gPanel.tileManager.tile[tileNum3].collision == true) {
				entity.direction = "right";
			}
			else if(gPanel.tileManager.tile[tileNum2].collision == true && gPanel.tileManager.tile[tileNum3].collision == false) {
				entity.direction = "down";
			}
			else if(gPanel.tileManager.tile[tileNum1].collision == false && gPanel.tileManager.tile[tileNum2].collision == true && gPanel.tileManager.tile[tileNum3].collision == true) {
				entity.collisionOn = true;
			}
			
			break;
		}

	}
}
