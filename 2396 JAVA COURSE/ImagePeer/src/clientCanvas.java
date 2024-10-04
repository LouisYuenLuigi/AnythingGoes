import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

/**
 * @author Yuen Chun Hin Luigi
 * the clientcanvas class, for the client/imagepeer
 */
public class clientCanvas extends JPanel {
	private ArrayList<Integer> order;
	private ArrayList<Image> parts;
	private Integer temp;
	private Integer clix;
	private ImagePeer ip;
	
	/**
	 * @param IP the imagepeer it attaches to, in order to call its functions
	 * constructor for the canvas
	 */
	public clientCanvas(ImagePeer IP) {
		this.ip = IP;
		order = new ArrayList<Integer>();
		parts = new ArrayList<Image>();
		clix = 0;
		
		MouseAdapter ma = new MouseAdapter() {
			/**
			 *first part of moving the image block
			 */
			public void mousePressed(MouseEvent e) {
				int gay = e.getY()/70  + e.getX()/70*10;
				temp = gay;
				clix = 1;
			}
			/**
			 *second part of moving the image block
			 */
			public void mouseReleased(MouseEvent e) {
				if (clix ==1) {
					int manny = e.getY()/70  + e.getX()/70*10;
					Collections.swap(order, temp, manny);
					repaint();
					clix =0;
					ip.sendOrder(order);
				}
				
			}
		};
		this.addMouseListener(ma);
	}
	/**
	 * @param bfg new image to be painted 
	 * @param newlist new block order to be updated
	 * loads a new image
	 */
	public void paintit(BufferedImage bfg, ArrayList<Integer> newlist) {
		parts.clear();
		for (int i=0;i<10;i++) {
			 for (int j=0;j<10;j++) {
				 parts.add(bfg.getSubimage(i*70, j*70, 70, 70));
			 }
		}
		order = newlist;
		repaint();
	}

	
	/**
	 * @param ans new block order to be updated
	 * updates the block order only
	 */
	public void change(ArrayList<Integer> ans) {
		order = ans;
		repaint();
	}
	/**
	 *updates the canvas image
	 */
	public void paintComponent(Graphics g) {
		if (parts!= null && order!=null) {
			for (int i=0;i<10;i++) {
				 for (int j=0;j<10;j++) {
					 
					 try {
						 g.drawImage(parts.get(order.get(j*10+i)),j*70,i*70,this);
					 }catch(Exception e) {
						 
					 }
					 
					 
				 }
			}
		}
		
	}

}
