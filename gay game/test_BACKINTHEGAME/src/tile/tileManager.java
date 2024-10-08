package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import test_BACKINTHEGAME.gamePanel;

public class tileManager {
	
	gamePanel gPanel;
	tile[] tile;
	int mapTileNumber[][];
	
	public tileManager(gamePanel gPanel) {
		this.gPanel = gPanel;
		
		tile = new tile[10];
		mapTileNumber = new int[gPanel.maxWorldCol][gPanel.maxWorldRow];
		
		loadMap("/maps/map02.txt");
		getTileImage();
	}
	
	public void getTileImage() {
		
		try {
			tile[0] = new tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			tile[1] = new tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/face.png"));
			
			tile[2] = new tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/bricks.png"));
			
			tile[3] = new tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/mud.png"));
			
			tile[4] = new tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass1.png"));
			
			tile[5] = new tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass2.png"));
			
			tile[6] = new tile();
			tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass3.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadMap(String filePath) {
		
		try {
			
			InputStream iStream = getClass().getResourceAsStream(filePath);
			BufferedReader bReader = new BufferedReader(new InputStreamReader(iStream));
			
			int col = 0;
			int row = 0;
			
			while(col < gPanel.maxWorldCol && row < gPanel.maxWorldRow) {
				
				String lineString = bReader.readLine();
				
				while(col < gPanel.maxWorldCol) {
					String numbers[] = lineString.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNumber[col][row]= num;
					col++;
				}
				if(col == gPanel.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			bReader.close();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void draw(Graphics2D g2) {
		
		int worldCol = 0;
		int worldRow = 0;

		
		while(worldCol < gPanel.maxWorldCol && worldRow < gPanel.maxWorldRow) {
			
			int tileNum = mapTileNumber[worldCol][worldRow];
			int worldX = worldCol * gPanel.tileSize;
			int worldY = worldRow * gPanel.tileSize;
			int screenX = worldX - gPanel.player.worldX + gPanel.player.screenX;
			int screenY = worldY - gPanel.player.worldY + gPanel.player.screenY;
			
			
			if(worldX + gPanel.tileSize > gPanel.player.worldX - gPanel.player.screenX &&
			   worldX - gPanel.tileSize < gPanel.player.worldX + gPanel.player.screenX &&
			   worldY + gPanel.tileSize > gPanel.player.worldY - gPanel.player.screenY &&
			   worldY - gPanel.tileSize < gPanel.player.worldY + gPanel.player.screenY) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, gPanel.tileSize, gPanel.tileSize, null);
			}
				
			worldCol++;

			
			if(worldCol == gPanel.maxWorldCol) {
				worldCol = 0;
				worldRow++;

			}
		}
		
	}

}
