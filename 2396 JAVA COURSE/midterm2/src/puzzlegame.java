import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * @author Yuen Chun Hin Luigi
 *	The puzzlegame class, contains the main flow and the main method
 */
public class puzzlegame extends JFrame {
	private JButton upload;
	private JButton display;
	private JButton save;
	private JButton load;
	private String imagename;
	

	/**
	 * @param args the main method
	 * the main method
	 */
	public static void main(String[] args) {
		puzzlegame pg = new puzzlegame();
		pg.setVisible(true);
		pg.setResizable(false);
		pg.setSize(550,600);
		pg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pg.setLocationRelativeTo(null);

	}
	/**
	 * Constructor for a puzzlegame class
	 * initializes variables, and prompts a fileChooser
	 */
	public puzzlegame() {
		super("Puzzle Game");
		
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new java.io.File("."));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG file", "jpg");
		fc.setFileFilter(filter);
		fc.setDialogTitle("Browse the image");
		if (fc.showOpenDialog(upload) == JFileChooser.APPROVE_OPTION) {
			
		}
		imagename = fc.getSelectedFile().getAbsolutePath();
				
		JPanel mainpanel = new JPanel();
		JPanel buttonpanel = new JPanel();
		mainpanel.setLayout(null);
		buttonpanel.setLayout(null);
		buttonpanel.setBounds(25, 525, 500, 30);
		
		board gameboard = new board(imagename);
		gameboard.setBounds(25, 10, 500, 500);
		mainpanel.add(buttonpanel);
		mainpanel.add(gameboard);
		super.add(mainpanel);
		
		upload = new JButton("Load new");
		upload.setBounds(0, 0, 125, 30);
		buttonpanel.add(upload);
		
		
		display = new JButton("Show original");
		display.setBounds(375, 0, 125, 30);
		buttonpanel.add(display);
		
		save = new JButton("Save Game");
		save.setBounds(125, 0, 125, 30);
		buttonpanel.add(save);
		
		load = new JButton("Load Game");
		load.setBounds(250, 0, 125, 30);
		buttonpanel.add(load);
		
		
		handler upbutton = new handler(gameboard);
		upload.addActionListener(upbutton);
		display.addActionListener(upbutton);
		save.addActionListener(upbutton);
		load.addActionListener(upbutton);
		
		
		
	}

	/**
	 * @param savePieces the arraylist of integers storing the puzzle location
	 * @param saveParts the orignal puzzle image
	 * @throws Exception throws exception for the fileio
	 */
	public void save(ArrayList<Integer> savePieces, BufferedImage saveParts) throws Exception {
		
		
		JFileChooser fc = new JFileChooser();
		fc.setSelectedFile(new File("mySave.pgs"));
		fc.setFileFilter(new FileNameExtensionFilter("PGS file","pgs"));
		int sav = fc.showSaveDialog(this);
		if (sav == JFileChooser.APPROVE_OPTION) {
			File saveFile = fc.getSelectedFile();
			FileOutputStream fos = new FileOutputStream(saveFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(savePieces);
			ImageIO.write((RenderedImage) saveParts,"jpg",oos);
			oos.close();
		}

		
	}
	
	
	/**
	 * @param GB the game board, where the puzzle pieces are
	 * @throws Exception for the fileio
	 */
	public void loadGame(board GB) throws Exception {
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new java.io.File("."));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PuzGameSave","pgs", "PGS File");
		fc.setFileFilter(filter);
		fc.setDialogTitle("Browse the save file");
		if (fc.showOpenDialog(upload) == JFileChooser.APPROVE_OPTION) {	
		}
		if (fc.getSelectedFile() != null) {
			String filename = fc.getSelectedFile().getName();
			
			if (filename.substring(filename.length()-4,filename.length()).contentEquals(".pgs")) {
				File f = fc.getSelectedFile();
				FileInputStream fis = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(fis);
				ArrayList<Integer> newPieces = (ArrayList<Integer>) ois.readObject();
				BufferedImage newBFG = (BufferedImage) ImageIO.read(ois);
				
				GB.setPieces(newPieces);
				GB.setParts(newBFG);
				GB.paintdat();
				ois.close();
			}
			else {
				JOptionPane.showMessageDialog(null, "Error loading save file!");
			}
			
		}
		
	}
	
	
	/**
	 * @author Yuen Chun Hin Luigi
	 * the handler class which is the actionlistener for the buttons
	 */
	private class handler implements ActionListener{
		private board wow;
		
		/**
		 * @param man the game board
		 * a special constructor for taking in the gameboard as parameter in order to interact with it
		 */
		public handler(board man) {
			wow = man;
		}
		

		/**
		 *Actionperformed for the buttons, controls what the buttons do
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().contentEquals("Load new")) {
				imagename = null;
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new java.io.File("."));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG file", "jpg");
				fc.setFileFilter(filter);
				fc.setDialogTitle("Browse the image");
				if (fc.showOpenDialog(upload) == JFileChooser.APPROVE_OPTION) {
					
				}
				if (fc.getSelectedFile() != null) {
					String newfilename = fc.getSelectedFile().getName();
					if (newfilename.substring(newfilename.length()-4,newfilename.length()).contentEquals(".jpg")) {
						imagename = fc.getSelectedFile().getAbsolutePath();
						wow.loadnew(imagename);
					}
					else {
						JOptionPane.showMessageDialog(null, "Error loading image!");
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Error loading image!");
				}	
			}
			
			if(e.getActionCommand().contentEquals("Show original")) {
				JFrame showOri = new JFrame();
				showOri.setVisible(true);
				showOri.setResizable(false);
				showOri.setSize(510,530);
				showOri.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				showOri.setTitle("Original Image");
				
				ImageIcon bfgicon = new ImageIcon((Image) wow.getBfg());
				JLabel yeah = new JLabel(bfgicon);
				yeah.setBounds(0, 0, 510, 510);
				yeah.setVisible(true);
				showOri.add(yeah);
				
			}
			if(e.getActionCommand().contentEquals("Save Game")) {

				ArrayList<Integer> savePieces = wow.getPieces();
				BufferedImage newbfg = wow.getBfg();


				try {
					save(savePieces, newbfg);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				
				
			}
			if (e.getActionCommand().contentEquals("Load Game")) {

				try {
					loadGame(wow);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}	
		}

		
	}

}
