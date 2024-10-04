import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Yuen Chun Hin Luigi
 * The board class, for the puzzle board
 *
 */
public class board extends JPanel{
	private BufferedImage bfg;
	private ArrayList<Image> parts;
	private ArrayList<Integer> pieces;
	private ArrayList<Integer> answer;
	private int clicks;
	private int temp;
	
	/**
	 * @return list of integers that contain the puzzle pieces location
	 */
	public ArrayList<Integer> getPieces(){
		return pieces;
	}
	/**
	 * @param newPieces the new list of integers to be imported
	 * changes the current board, loads in the loaded puzzle information
	 */
	public void setPieces(ArrayList<Integer> newPieces){
		pieces = newPieces;
	}

	/**
	 * @param newBFG new image to be set as the main image
	 * 
	 */
	public void setParts(BufferedImage newBFG){
		bfg = newBFG;
		bfg = resize(bfg,500,500);
		parts.clear();
		for (int i=0;i<5;i++) {
			 for (int j=0;j<5;j++) {
				 parts.add(bfg.getSubimage(i*100, j*100, 100, 100));
			 }
		}
	}
	/**
	 * Calls a repaint
	 */
	public void paintdat() {
		repaint();
	}
	
	/**
	 * Constructor of the game board, initializes everything needed
	 * @param img String of the direct file name when first initializing the board
	 */
	public board(String img) {
		loadnew(img);
		answer = new ArrayList<Integer>();
		for (int i=0;i<25;i++) {
			answer.add(i);
		}
		clicks = 0;
		MouseAdapter ma = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				clicks++;

				if (clicks == 1) {
					int gay = e.getY()/100  + e.getX()/100*5;

					temp = gay;
					
				}
				if (clicks == 2) {
					int manny = e.getY()/100  + e.getX()/100*5;

					clicks = 0;
					Collections.swap(pieces, temp, manny);
					
					repaint();
					if (checkWin() == true) {
						JOptionPane.showMessageDialog(null, "YOU WIN!!!");
					}
					else{

					}
					
				}
			}
		};
		this.addMouseListener(ma);
		 
		
		
	}



	/**
	 * @param img string file location of the new image
	 * loads a new image into the board
	 */
	public void loadnew(String img) {
		if (pieces != null) {
			pieces.clear();
		}
		pieces = new ArrayList<Integer>();
		for (int i=0;i<25;i++) {
			pieces.add(i);
		}
		Collections.shuffle(pieces);
		
		bfg = new BufferedImage(500,500,BufferedImage.TYPE_INT_ARGB);
		try {
			bfg = ImageIO.read(new File(img));

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bfg = resize(bfg,500,500);
		parts = new ArrayList<Image>();
		for (int i=0;i<5;i++) {
			 for (int j=0;j<5;j++) {
				 parts.add(bfg.getSubimage(i*100, j*100, 100, 100));
			 }
		}
		clicks = 0;
		repaint();
	}
	
	/**
	 * @param img original image to be resized
	 * @param newW new width
	 * @param newH new height
	 * @return the resized image
	 */
	public static BufferedImage resize(BufferedImage img, int newW, int newH) {  
	    int w = img.getWidth();  
	    int h = img.getHeight();  
	    BufferedImage newimg = new BufferedImage(newW, newH, img.getType());  
	    Graphics2D g = newimg.createGraphics();  
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	    RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
	    g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);  
	    g.dispose();  
	    return newimg;  
	} 

	/**
	 * checks whether the player has won
	 * @return the boolean value whether the player has won
	 */
	public boolean checkWin() {
		return answer.equals(pieces);
	}
	
	/**
	 * the paintcomponent, responsible for drawing and displaying the puzzle pieces
	 */
	public void paintComponent(Graphics g) {
		for (int i=0;i<5;i++) {
			 for (int j=0;j<5;j++) {
				 g.drawImage(parts.get(pieces.get(j*5+i)),j*100,i*100,this); 
			 }
		}	
	}
	/**
	 * @return the current main image
	 */
	public BufferedImage getBfg() {
		
		return bfg;
	}

}
