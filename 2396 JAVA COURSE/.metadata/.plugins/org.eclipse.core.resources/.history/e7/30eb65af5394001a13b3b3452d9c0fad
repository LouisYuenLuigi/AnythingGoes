import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class canvas extends JPanel{
	private BufferedImage currentimg;
	
	public canvas() {
		
	}
	public void paintit(BufferedImage bfg) {
		currentimg = bfg;
		repaint();
	}
	public void paintComponent(Graphics g) {
		g.drawImage(currentimg, 0,0,this);
	}

}
