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
		mapTileNumber = new int[gPanel.maxScreenCol][gPanel.maxScreenRow];
		
		loadMap("/maps/map01.txt");
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
			
			while(col < gPanel.maxScreenCol && row < gPanel.maxScreenRow) {
				
				String lineString = bReader.readLine();
				
				while(col < gPanel.maxScreenCol) {
					String numbers[] = lineString.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNumber[col][row]= num;
					col++;
				}
				if(col == gPanel.maxScreenCol) {
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
		
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(col < gPanel.maxScreenCol && row < gPanel.maxScreenRow) {
			
			int tileNum = mapTileNumber[col][row];
			
			g2.drawImage(tile[tileNum].image, x, y, gPanel.tileSize, gPanel.tileSize, null);
			col++;
			x += gPanel.tileSize;
			
			if(col == gPanel.maxScreenCol) {
				col = 0;
				x = 0;
				row++;
				y += gPanel.tileSize;
			}
		}
		
	}

}
