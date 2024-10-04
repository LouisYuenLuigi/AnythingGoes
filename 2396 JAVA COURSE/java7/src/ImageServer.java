import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageServer extends JFrame{
	private BufferedImage bfg;
	private String imgAddress;
	
	private static ExecutorService pool;
	private JPanel mainpanel;
	
	
	public ImageServer() throws IOException{
		super("Image Server");
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new java.io.File("."));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG file", "jpg");
		fc.setFileFilter(filter);
		fc.setDialogTitle("Browse the image");
		if (fc.showOpenDialog(new JButton()) == JFileChooser.APPROVE_OPTION) {	
		}
		imgAddress = fc.getSelectedFile().getAbsolutePath();
		loadnew();
		mainpanel = new JPanel();
		mainpanel.setLayout(null);
		canvas cv = new canvas();
		cv.setLayout(null);
		cv.setBounds(0, 0, 700, 700);
		JButton load = new JButton("Load another image");
		load.setBounds(0, 700, 700, 30);
		mainpanel.add(load);
		mainpanel.add(cv);
		add(mainpanel);
		cv.paintit(bfg);
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		connector con = new connector();
		pool = Executors.newFixedThreadPool(1);
		pool.execute(con);
		
	}
	
	
		
	public void loadnew() {
		bfg = new BufferedImage(700,700,BufferedImage.TYPE_INT_ARGB);
		try {
			bfg = ImageIO.read(new File(imgAddress));

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bfg = resize(bfg,700,700);
	}
	
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
	

	public static void main(String[] args) {
		try {
			ImageServer IS = new ImageServer();
			IS.setVisible(true);
			IS.setResizable(false);
			IS.setSize(700,759);
			IS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			IS.setLocationRelativeTo(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
