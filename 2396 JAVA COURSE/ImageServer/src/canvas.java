import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;

/**
 * @author Yuen Chun Hin Luigi
 * the canvas for the imageserver
 *
 */
public class canvas extends JPanel{
	
	private ArrayList<Integer> order;
	private ArrayList<Image> parts;
	private Integer temp;
	private Integer clix;
	private ImageServer is;
	
	/**
	 * @param IS the imageserver, for calling its functions
	 * the constructor for the canvas
	 */
	public canvas(ImageServer IS) {
		this.is = IS;
		order = new ArrayList<Integer>();
		resetSeq();
		parts = new ArrayList<Image>();
		clix = 0;
		
		MouseAdapter ma = new MouseAdapter() {
			/**
			 *first part of moving the image parts
			 */
			public void mousePressed(MouseEvent e) {
				int gay = e.getY()/70  + e.getX()/70*10;
				temp = gay;
				clix = 1;
			}
			/**
			 *second part of moving the image parts
			 */
			public void mouseReleased(MouseEvent e) {
				if (clix ==1) {
					int manny = e.getY()/70  + e.getX()/70*10;
					Collections.swap(order, temp, manny);
					repaint();
					clix =0;
					is.sendOrder(order);
				}
				
			}
		};
		this.addMouseListener(ma);
	}
	/**
	 * To reset the image block sequence
	 */
	public void resetSeq() {
		order.clear();
		for (int i=0;i<100;i++) {
			order.add(i);
		}
	}
	/**
	 * @return the current block order
	 * return the block order
	 */
	public ArrayList<Integer> getOrder(){
		return order;
	}
	/**
	 * @param bfg image to be painted
	 * updates the canvas with new image
	 */
	public void paintit(BufferedImage bfg) {
		parts.clear();
		for (int i=0;i<10;i++) {
			 for (int j=0;j<10;j++) {
				 parts.add(bfg.getSubimage(i*70, j*70, 70, 70));
			 }
		}
		repaint();
	}
	/**
	 * @param ans new block order to be updated
	 * updates the new block order
	 */
	public synchronized void change(ArrayList<Integer> ans) {
		order = ans;
		repaint();
	}
	
	/**
	 *updates the canvas
	 */
	public void paintComponent(Graphics g) {
		for (int i=0;i<10;i++) {
			 for (int j=0;j<10;j++) {
				 g.drawImage(parts.get(order.get(j*10+i)),j*70,i*70,this); 
			 }
		}
	}

}
