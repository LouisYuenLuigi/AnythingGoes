import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

/**
 * @author Yuen Chun Hin Luigi
 * The main class for the drawobject editor
 *
 */
public class DrawObjectEditor extends JFrame{
	
	private JButton LineButton;
	private JButton CircleButton;
	private JButton TriangleButton;
	private JButton QuadButton;
	private JButton SelectButton;
	private JButton MoveButton;
	private JButton DeleteButton;
	private JButton CopyButton;
	private JButton RanColButton;
	private JButton SaveButton;
	private JButton LoadButton;
	private JButton ExportButton;
	private JButton ImportButton;
	
	/**
	 * @param args
	 * main function, creates and initializes the main drawobjecteditor
	 */
	public static void main(String[] args) {
		
		DrawObjectEditor doe = new DrawObjectEditor();
		doe.setResizable(false);
		doe.setSize(400,509);
		doe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		doe.setLocationRelativeTo(null);
		doe.setVisible(true);

	}
	
	
	/**
	 * Constructor for the draw object editor
	 * Initializes and adds all the components needed, including all the buttons, the button panel for the buttons, and the canvas JPanel used for drawing.
	 */
	public DrawObjectEditor() {
		super("Draw Object Editor");
		int width = 132;
		int height = 29;
		JPanel mainpanel = new JPanel();
		JPanel buttonpanel = new JPanel();
		JPanel bottompanel = new JPanel();
		mainpanel.setLayout(null);
		Canvas canvas = new Canvas(this);
		canvas.setBounds(0, 91, 400, 360);
		buttonpanel.setLayout(null);
		buttonpanel.setBounds(0, 0, 400, 90);
		bottompanel.setBounds(0,450,400, 50);
		bottompanel.setLayout(null);
		mainpanel.add(canvas);
		mainpanel.add(buttonpanel);
		mainpanel.add(bottompanel);
		super.add(mainpanel);
		
		LineButton = new JButton("Line");
		LineButton.setBounds(1, 1, width, height);
		buttonpanel.add(LineButton);
		
		CircleButton = new JButton("Circle");
		CircleButton.setBounds(134, 1, width, height);
		buttonpanel.add(CircleButton);
		
		TriangleButton = new JButton("Triangle");
		TriangleButton.setBounds(267, 1, width, height);
		buttonpanel.add(TriangleButton);
		
		 QuadButton = new JButton("Quadrilateral");
		QuadButton.setBounds(1, 31, width, height);
		buttonpanel.add(QuadButton);
		
		SelectButton = new JButton("Select");
		SelectButton.setBounds(134, 31, width, height);
		buttonpanel.add(SelectButton);
		
		MoveButton = new JButton("Move");
		MoveButton.setBounds(267, 31, width, height);
		buttonpanel.add(MoveButton);
		
		DeleteButton = new JButton("Delete");
		DeleteButton.setBounds(1, 61, width, height);
		buttonpanel.add(DeleteButton);
		
		CopyButton = new JButton("Copy");
		CopyButton.setBounds(134, 61, width, height);
		buttonpanel.add(CopyButton);
		
		RanColButton = new JButton("Random Color");
		RanColButton.setBounds(267, 61, width, height);
		buttonpanel.add(RanColButton);
		
		SaveButton = new JButton("Save");
		SaveButton.setBounds(0, 1, 100, height);
		bottompanel.add(SaveButton);
		
		LoadButton = new JButton("Load");
		LoadButton.setBounds(100, 1, 100, height);
		bottompanel.add(LoadButton);
		
		ExportButton = new JButton("Export");
		ExportButton.setBounds(200, 1, 100, height);
		bottompanel.add(ExportButton);
		
		ImportButton = new JButton("Import");
		ImportButton.setBounds(300, 1, 100, height);
		bottompanel.add(ImportButton);
		
		clickHandler handler = new clickHandler(canvas, this);
		
		LineButton.addActionListener(handler);		
		CircleButton.addActionListener(handler);
		TriangleButton.addActionListener(handler);
		QuadButton.addActionListener(handler);
		SelectButton.addActionListener(handler);
		MoveButton.addActionListener(handler);
		DeleteButton.addActionListener(handler);
		CopyButton.addActionListener(handler);
		RanColButton.addActionListener(handler);
		
		SaveButton.addActionListener(handler);
		LoadButton.addActionListener(handler);
		ImportButton.addActionListener(handler);
		ExportButton.addActionListener(handler);
		
		
		MoveButton.setBackground(Color.GRAY);
		DeleteButton.setBackground(Color.GRAY);
		CopyButton.setBackground(Color.GRAY);
		RanColButton.setBackground(Color.GRAY);
		
		
		
	}
	/**
	 * @param mode whatever the current task is, started by clicking the task's button
	 * Updates the button color and whether the buttons are usable
	 */
	public void setbuttons(String mode) {
		if (mode.contentEquals("line")) {
			LineButton.setBackground(new JButton().getBackground());
		}
		if (mode.contentEquals("circle")) {
			CircleButton.setBackground(new JButton().getBackground());
		}
		if (mode.contentEquals("triangle")) {
			TriangleButton.setBackground(new JButton().getBackground());
		}
		if (mode.contentEquals("quad")) {
			QuadButton.setBackground(new JButton().getBackground());
		}
		if (mode.contentEquals("select")) {
			MoveButton.setBackground(new JButton().getBackground());
			DeleteButton.setBackground(new JButton().getBackground());
			CopyButton.setBackground(new JButton().getBackground());
			RanColButton.setBackground(new JButton().getBackground());
		}
		if (mode.contentEquals("other")) {
			SelectButton.setBackground(new JButton().getBackground());
			MoveButton.setBackground(Color.GRAY);
			DeleteButton.setBackground(Color.GRAY);
			CopyButton.setBackground(Color.GRAY);
			RanColButton.setBackground(Color.GRAY);
		}
		
		
	}
	/**
	 * @author Yuen Chun Hin Luigi
	 * handler for the buttons.
	 *
	 */
	private class clickHandler implements ActionListener{
		Canvas doe;
		DrawObjectEditor wow;
		private ArrayList<Line2D> implines;
		private ArrayList<Ellipse2D> impcircles;
		private ArrayList<Polygon> imptriangles;
		private ArrayList<Polygon> impquads;
		private ArrayList<Color> implinecolors;
		private ArrayList<Color> impcirclecolors;
		private ArrayList<Color> imptricolors;
		private ArrayList<Color> impquadcolors;
		/**
		 * @param ddd the canvas to be edited
		 * initializes the button handler
		 */
		public clickHandler(Canvas ddd, DrawObjectEditor wowza) {
			doe = ddd;
			wow = wowza;
		}
		
		/**
		 * @throws Exception
		 * saves the drawing as a doe file
		 */
		public void saveDrawing() throws Exception{
			JFileChooser fc = new JFileChooser();
			fc.setSelectedFile(new File("mySave.doe"));
			fc.setFileFilter(new FileNameExtensionFilter("DOE file","doe"));
			int sav = fc.showSaveDialog(wow);
			if (sav == JFileChooser.APPROVE_OPTION) {
				File saveFile = fc.getSelectedFile();
				FileOutputStream fos = new FileOutputStream(saveFile);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(doe.getLines());
				oos.writeObject(doe.getLinecolors());
				oos.writeObject(doe.getCircles());
				oos.writeObject(doe.getCirclecolors());
				oos.writeObject(doe.getTriangles());
				oos.writeObject(doe.getTricolors());
				oos.writeObject(doe.getQuads());
				oos.writeObject(doe.getQuadcolors());
				
				oos.close();
			}
		}
		
		/**
		 * @throws Exception
		 * loads a doe file into the canvas
		 */
		public void loadDrawing() throws Exception{
			JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory(new java.io.File("."));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("DrawObjectEditorSave","doe", "DOE File");
			fc.setFileFilter(filter);
			fc.setDialogTitle("Browse the save file");
			if (fc.showOpenDialog(wow) == JFileChooser.APPROVE_OPTION) {	
			}
			if (fc.getSelectedFile() != null) {
				String filename = fc.getSelectedFile().getName();
				
				if (filename.substring(filename.length()-4,filename.length()).contentEquals(".doe")) {
					File f = fc.getSelectedFile();
					FileInputStream fis = new FileInputStream(f);
					ObjectInputStream ois = new ObjectInputStream(fis);					
					
					doe.setLines((ArrayList<Line2D>) ois.readObject());
					doe.setLinecolors((ArrayList<Color>) ois.readObject());
					doe.setCircles((ArrayList<Ellipse2D>) ois.readObject());
					doe.setCirclecolors((ArrayList<Color>) ois.readObject());
					doe.setTriangles((ArrayList<Polygon>) ois.readObject());
					doe.setTricolors((ArrayList<Color>) ois.readObject());
					doe.setQuads((ArrayList<Polygon>) ois.readObject());
					doe.setQuadcolors((ArrayList<Color>) ois.readObject());
					doe.paintdat();					
					
					ois.close();
				}
				else {
					JOptionPane.showMessageDialog(null, "Error loading save file!");
				}
				
			}
		}
		
		/**
		 * @throws Exception
		 * exports the drawing into a text file
		 */
		public void exportDrawing() throws Exception {
			JFileChooser fc = new JFileChooser();
			fc.setSelectedFile(new File("myDrawing.txt"));
			fc.setFileFilter(new FileNameExtensionFilter("Text file","txt"));
			int sav = fc.showSaveDialog(wow);
			if (sav == JFileChooser.APPROVE_OPTION) {
				File saveFile = fc.getSelectedFile();
				FileOutputStream fos = new FileOutputStream(saveFile);
				PrintWriter oos = new PrintWriter(fos);
				
				for (int i=0;i<doe.getLines().size();i++) {
					oos.println("line;"+(int)doe.getLines().get(i).getX1()+";"+(int)doe.getLines().get(i).getY1()+";"+(int)doe.getLines().get(i).getX2()+";"+(int)doe.getLines().get(i).getY2()+";"+doe.getLinecolors().get(i).getRed()+";"+doe.getLinecolors().get(i).getGreen()+";"+doe.getLinecolors().get(i).getBlue());
				}
				for (int i=0;i<doe.getCircles().size();i++) {
					oos.println("circle;"+(int)doe.getCircles().get(i).getCenterX()+";"+(int)doe.getCircles().get(i).getCenterY()+";"+doe.getCircles().get(i).getWidth()/2+";"+doe.getCirclecolors().get(i).getRed()+";"+doe.getCirclecolors().get(i).getGreen()+";"+doe.getCirclecolors().get(i).getBlue());
				}
				for (int i=0;i<doe.getTriangles().size();i++) {
					oos.printf("triangle;");
					for (int j=0;j<doe.getTriangles().get(i).npoints;j++) {
						oos.printf(doe.getTriangles().get(i).xpoints[j]+";"+doe.getTriangles().get(i).ypoints[j]+";");
					}
					oos.printf(doe.getTricolors().get(i).getRed()+";"+doe.getTricolors().get(i).getGreen()+";"+doe.getTricolors().get(i).getBlue()+"\n");
				}
				for (int i=0;i<doe.getQuads().size();i++) {
					oos.printf("quadrilateral;"+doe.getQuads().get(i).npoints+";");
					for (int j=0;j<doe.getQuads().get(i).npoints;j++) {
						oos.printf(doe.getQuads().get(i).xpoints[j]+";"+doe.getQuads().get(i).ypoints[j]+";");
					}
					oos.printf(doe.getQuadcolors().get(i).getRed()+";"+doe.getQuadcolors().get(i).getGreen()+";"+doe.getQuadcolors().get(i).getBlue()+"\n");
				}
				
				oos.close();
			}
		}
		/**
		 * @param cv the canvas to update
		 * @throws Exception
		 * imports a text file into the canvas
		 */
		public void importDrawing(Canvas cv) throws Exception{
			JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory(new java.io.File("."));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File","txt");
			fc.setFileFilter(filter);
			fc.setDialogTitle("Browse the text file");
			if (fc.showOpenDialog(wow) == JFileChooser.APPROVE_OPTION) {	
			}
			if (fc.getSelectedFile() != null) {
				String filename = fc.getSelectedFile().getName();
				
				if (filename.substring(filename.length()-4,filename.length()).contentEquals(".txt")) {
					File f = fc.getSelectedFile();
					FileInputStream fis = new FileInputStream(f);
					Scanner ois = new Scanner(fis);
					
					implines = new ArrayList<Line2D>();
					impcircles = new ArrayList<Ellipse2D>();
					imptriangles = new ArrayList<Polygon>();
					impquads = new ArrayList<Polygon>();
					implinecolors = new ArrayList<Color>();
					impcirclecolors = new ArrayList<Color>();
					imptricolors = new ArrayList<Color>();
					impquadcolors = new ArrayList<Color>();
					
					while(ois.hasNext()) {
						String nextLine = ois.nextLine();
						
						String[] tokens = nextLine.split(";");
						if (tokens[0].contentEquals("line")) {
							implines.add(new Line2D.Double(Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2]),Integer.parseInt(tokens[3]),Integer.parseInt(tokens[4])));
							implinecolors.add(new Color(Integer.parseInt(tokens[5]),Integer.parseInt(tokens[6]),Integer.parseInt(tokens[7])));
						}
						if (tokens[0].contentEquals("circle")) {
							impcircles.add(new Ellipse2D.Double(Double.parseDouble(tokens[1])-Double.parseDouble(tokens[3]), Double.parseDouble(tokens[2])-Double.parseDouble(tokens[3]), Double.parseDouble(tokens[3])*2, Double.parseDouble(tokens[3])*2));
							
							impcirclecolors.add(new Color(Integer.parseInt(tokens[4]),Integer.parseInt(tokens[5]),Integer.parseInt(tokens[6])));
						}
						if (tokens[0].contentEquals("triangle")){
							Polygon newtri = new Polygon();
							newtri.addPoint(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
							newtri.addPoint(Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]));
							newtri.addPoint(Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]));
							imptriangles.add(newtri);
							imptricolors.add(new Color(Integer.parseInt(tokens[7]),Integer.parseInt(tokens[8]),Integer.parseInt(tokens[9])));
						}
						if (tokens[0].contentEquals("quadrilateral")){
							Polygon newquad = new Polygon();
							int count = 0;
							for (int i=1;i<Integer.parseInt(tokens[1])+1;i++) {
								newquad.addPoint(Integer.parseInt(tokens[i*2]), Integer.parseInt(tokens[i*2+1]));
								count++;
							}
							
							imptriangles.add(newquad);
							imptricolors.add(new Color(Integer.parseInt(tokens[count*2+2]),Integer.parseInt(tokens[count*2+3]),Integer.parseInt(tokens[count*2+4])));
						}
						
					}
					cv.setLines(implines);
					cv.setLinecolors(implinecolors);
					cv.setCircles(impcircles);
					cv.setCirclecolors(impcirclecolors);
					cv.setTriangles(imptriangles);
					cv.setTricolors(imptricolors);
					cv.setQuads(impquads);
					cv.setQuadcolors(impquadcolors);
					cv.paintdat();
					
					
					ois.close();
				}
				else {
					JOptionPane.showMessageDialog(null, "Error loading drawing file!");
				}
				
			}
		}

		

		/**
		 * Contains the actions to be performed for every button
		 * controls the program flow
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getActionCommand().contentEquals("Line") && LineButton.getBackground() == new JButton().getBackground()) {
				doe.setMode("line");
				doe.resetClicks();
				LineButton.setBackground(Color.GRAY);
				MoveButton.setBackground(Color.GRAY);
				DeleteButton.setBackground(Color.GRAY);
				CopyButton.setBackground(Color.GRAY);
				RanColButton.setBackground(Color.GRAY);
				CircleButton.setBackground(new JButton().getBackground());
				TriangleButton.setBackground(new JButton().getBackground());
				QuadButton.setBackground(new JButton().getBackground());
				SelectButton.setBackground(new JButton().getBackground());
			}
			if (e.getActionCommand().contentEquals("Circle") && CircleButton.getBackground() == new JButton().getBackground()) {
				doe.setMode("circle");
				doe.resetClicks();
				CircleButton.setBackground(Color.GRAY);
				MoveButton.setBackground(Color.GRAY);
				DeleteButton.setBackground(Color.GRAY);
				CopyButton.setBackground(Color.GRAY);
				RanColButton.setBackground(Color.GRAY);
				LineButton.setBackground(new JButton().getBackground());
				TriangleButton.setBackground(new JButton().getBackground());
				QuadButton.setBackground(new JButton().getBackground());
				SelectButton.setBackground(new JButton().getBackground());
			}
			if (e.getActionCommand().contentEquals("Triangle") && TriangleButton.getBackground() == new JButton().getBackground()) {
				doe.setMode("triangle");
				doe.resetClicks();
				TriangleButton.setBackground(Color.GRAY);
				MoveButton.setBackground(Color.GRAY);
				DeleteButton.setBackground(Color.GRAY);
				CopyButton.setBackground(Color.GRAY);
				RanColButton.setBackground(Color.GRAY);
				LineButton.setBackground(new JButton().getBackground());
				CircleButton.setBackground(new JButton().getBackground());
				QuadButton.setBackground(new JButton().getBackground());
				SelectButton.setBackground(new JButton().getBackground());
			}
			if (e.getActionCommand().contentEquals("Quadrilateral") && QuadButton.getBackground() == new JButton().getBackground()) {
				doe.setMode("quad");
				doe.resetClicks();
				QuadButton.setBackground(Color.GRAY);
				MoveButton.setBackground(Color.GRAY);
				DeleteButton.setBackground(Color.GRAY);
				CopyButton.setBackground(Color.GRAY);
				RanColButton.setBackground(Color.GRAY);
				LineButton.setBackground(new JButton().getBackground());
				CircleButton.setBackground(new JButton().getBackground());
				TriangleButton.setBackground(new JButton().getBackground());
				SelectButton.setBackground(new JButton().getBackground());
			}
			if (e.getActionCommand().contentEquals("Select") && SelectButton.getBackground() == new JButton().getBackground()) {
				doe.setMode("select");
				doe.resetClicks();
				SelectButton.setBackground(Color.GRAY);
				LineButton.setBackground(new JButton().getBackground());
				CircleButton.setBackground(new JButton().getBackground());
				TriangleButton.setBackground(new JButton().getBackground());
				QuadButton.setBackground(new JButton().getBackground());
				
			}
			if (e.getActionCommand().contentEquals("Move") && MoveButton.getBackground() == new JButton().getBackground()) {
				doe.move();
				SelectButton.setBackground(Color.GRAY);
				MoveButton.setBackground(Color.GRAY);
				DeleteButton.setBackground(Color.GRAY);
				CopyButton.setBackground(Color.GRAY);
				RanColButton.setBackground(Color.GRAY);
			}
			if (e.getActionCommand().contentEquals("Copy") && CopyButton.getBackground() == new JButton().getBackground()) {
				doe.copyshape();
			}
			if (e.getActionCommand().contentEquals("Delete") && DeleteButton.getBackground() == new JButton().getBackground()) {
				doe.delshape();
			}
			if (e.getActionCommand().contentEquals("Random Color") && RanColButton.getBackground() == new JButton().getBackground()) {
				doe.randomcolor();
			}
			if (e.getActionCommand().contentEquals("Save")) {
				try {
					saveDrawing();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			if (e.getActionCommand().contentEquals("Load")) {
				try {
					loadDrawing();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (e.getActionCommand().contentEquals("Export")) {
				try {
					exportDrawing();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (e.getActionCommand().contentEquals("Import")) {
				try {
					importDrawing(doe);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			

			
			
		}
		
	}
	
}

