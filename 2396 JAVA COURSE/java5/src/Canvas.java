import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.Polygon;
import java.util.ArrayList;

import javax.swing.*;
/**
 * @author Yuen Chun Hin Luigi
 * The canvas for drawing the shapes on
 *
 */
public class Canvas extends JPanel{
	private DrawObjectEditor fk;
	private ArrayList<Line2D> lines;
	private ArrayList<Ellipse2D> circles;
	private ArrayList<Polygon> shapes;
	private ArrayList<Color> linecolors;
	private ArrayList<Color> circlecolors;
	private ArrayList<Color> polycolors;
	private int clicks;
	private String mode;
	private int tempx,tempy;
	private int tempx1,tempy1;
	private int tempx2,tempy2;
	private String selectedshape;
	private int selectedindex;
	private boolean moving;
	private boolean hit;
	private int startx, starty;
	private ArrayList<Integer> dotsx;
	private ArrayList<Integer> dotsy;

	
	
	/**
	 * @param the original draw object editor, used to update the buttons availability
	 * initializes the canvas and its variables. Also contains mouse events and corresponding actions to its modes
	 */
	public Canvas(DrawObjectEditor doe){
		fk = doe;
		clicks = 0;
		mode = "no";		
		lines = new ArrayList<Line2D>();
		circles = new ArrayList<Ellipse2D>();
		shapes = new ArrayList<Polygon>();
		linecolors = new ArrayList<Color>();
		circlecolors = new ArrayList<Color>();
		polycolors = new ArrayList<Color>();
		selectedshape = "";
		selectedindex = -1;
		moving = false;
		hit = false;
		dotsx = new ArrayList<Integer>();
		dotsy = new ArrayList<Integer>();
		

		
		MouseAdapter ma = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				

				clicks++;
				if (mode.contentEquals("line")){
					
					if (clicks ==1) {						
						tempx = (e.getX());
						tempy = (e.getY());
						dotsx.add(e.getX());
						dotsy.add(e.getY());
						repaint();
					}
					if (clicks ==2) {						
						lines.add(new Line2D.Double(tempx, tempy, e.getX(), e.getY()));
						linecolors.add(Color.BLACK);
						
						clicks = 0;
						
						fk.setbuttons(mode);
						mode = "";
						dotsx.clear();
						dotsy.clear();
						repaint();
						
					}
				}
				if (mode.contentEquals("circle")) {
					if (clicks ==1) {
						tempx=e.getX();
						tempy=e.getY();
						dotsx.add(e.getX());
						dotsy.add(e.getY());
						repaint();
					}
					if (clicks ==2) {
						double dis = Math.sqrt((tempx-e.getX())*(tempx-e.getX()) + (tempy-e.getY())*(tempy-e.getY()));
						circles.add(new Ellipse2D.Double(tempx-dis, tempy-dis, dis*2, dis*2));
						circlecolors.add(Color.WHITE);
						
						clicks = 0;
						
						fk.setbuttons(mode);
						mode = "";
						dotsx.clear();
						dotsy.clear();
						repaint();
					}
				}
				if (mode.contentEquals("triangle")){
					
					if (clicks ==1) {
						tempx = e.getX();
						tempy = e.getY();
						dotsx.add(e.getX());
						dotsy.add(e.getY());
						repaint();
					}
					if (clicks ==2) {
						tempx1 = e.getX();
						tempy1 = e.getY();	
						dotsx.add(e.getX());
						dotsy.add(e.getY());
						repaint();
					}
					if (clicks ==3) {
						Polygon triangle = new Polygon();
						triangle.addPoint(tempx, tempy);
						triangle.addPoint(tempx1, tempy1);
						triangle.addPoint(e.getX(), e.getY());						
						shapes.add(triangle);
						polycolors.add(Color.WHITE);
						
						clicks = 0;
						
						fk.setbuttons(mode);
						mode = "";
						dotsx.clear();
						dotsy.clear();
						repaint();
					}
				}
				if (mode.contentEquals("quad")){
					
					if (clicks ==1) {
						tempx = e.getX();
						tempy = e.getY();
						dotsx.add(e.getX());
						dotsy.add(e.getY());
						repaint();
					}
					if (clicks ==2) {
						tempx1 = e.getX();
						tempy1 = e.getY();
						dotsx.add(e.getX());
						dotsy.add(e.getY());
						repaint();
					}
					if (clicks ==3) {
						tempx2 = e.getX();
						tempy2 = e.getY();
						dotsx.add(e.getX());
						dotsy.add(e.getY());
						repaint();
					}
					if (clicks ==4) {
						Polygon quad = new Polygon();
						quad.addPoint(tempx, tempy);
						quad.addPoint(tempx1, tempy1);
						quad.addPoint(tempx2, tempy2);
						quad.addPoint(e.getX(), e.getY());
						polycolors.add(Color.WHITE);
						shapes.add(quad);
						
						clicks = 0;
						fk.setbuttons(mode);
						mode = "";
						dotsx.clear();
						dotsy.clear();
						repaint();
					}
				
				}
				if (mode.contentEquals("select")) {
					boolean selected = false;
					if (lines != null) {
						for (int i=0;i<lines.size();i++) {
							if (lines.get(i).intersects(e.getX()+1, e.getY()+1, 2, 2)) {
								selectedshape = "line";
								selectedindex = i;
								selected = true;
								fk.setbuttons(mode);
								
							}
							
						}
					}
					if (circles != null) {
						for (int i=0;i<circles.size();i++) {
							if (circles.get(i).contains(e.getX(), e.getY())) {
								selectedshape = "circle";
								selectedindex = i;
								selected = true;
								fk.setbuttons(mode);
								
							}
						}
					}
					if (shapes !=null) {
						for (int i=0;i<shapes.size();i++) {
							if (shapes.get(i).contains(e.getX(), e.getY())) {
								selectedshape = "poly";
								selectedindex = i;
								selected = true;
								fk.setbuttons(mode);
								
							}
						}
					}
					if (selected != true) {
						selectedshape = "";
						selectedindex = -1;
					}
					repaint();
				}
				
			}
			public void mousePressed(MouseEvent e) {
				
				if (selectedshape.contentEquals("line") && moving == true) {
					if (lines.get(selectedindex).intersects(e.getX()+1, e.getY()+1, 2, 2)) {
						hit = true;
						startx=e.getX();
						starty=e.getY();
					}
				}
				if (selectedshape.contentEquals("circle") && moving == true) {
					if (circles.get(selectedindex).contains(e.getX(), e.getY())) {
						hit = true;
						startx=e.getX();
						starty=e.getY();
					}
				}
				if (selectedshape.contentEquals("poly") && moving == true) {
					if (shapes.get(selectedindex).contains(e.getX(), e.getY())) {
						hit = true;
						startx=e.getX();
						starty=e.getY();
					}
				}
				


			}
			public void mouseReleased(MouseEvent e) {
				
				if (hit == true && moving == true) {
					
					
					if (selectedshape.contentEquals("line")) {
						lines.add(new Line2D.Double(lines.get(selectedindex).getX1()+e.getX()-startx, lines.get(selectedindex).getY1()+e.getY()-starty,lines.get(selectedindex).getX2()+e.getX()-startx, lines.get(selectedindex).getY2()+e.getY()-starty));
						
						linecolors.add(linecolors.get(selectedindex));
						lines.remove(selectedindex);
						linecolors.remove(selectedindex);
						
					}
					if (selectedshape.contentEquals("circle")) {
						circles.add(new Ellipse2D.Double(circles.get(selectedindex).getX()+e.getX()-startx, circles.get(selectedindex).getY()+e.getY()-starty, circles.get(selectedindex).getWidth(), circles.get(selectedindex).getWidth()));
						
						circlecolors.add(circlecolors.get(selectedindex));
						circles.remove(selectedindex);
						circlecolors.remove(selectedindex);
						
					}
					if (selectedshape.contentEquals("poly")) {
						Polygon copypoly = new Polygon();
						for (int i=0;i<shapes.get(selectedindex).npoints;i++) {
							copypoly.addPoint(shapes.get(selectedindex).xpoints[i]+e.getX()-startx, shapes.get(selectedindex).ypoints[i]+e.getY()-starty);
						}
						shapes.add(copypoly);
						polycolors.add(polycolors.get(selectedindex));
						shapes.remove(selectedindex);
						polycolors.remove(selectedindex);
					}
					selectedindex = -1;
					selectedshape = "";
					repaint();
					fk.setbuttons("other");
					mode = "";
					moving = false;
				}
			}
			
		};
		this.addMouseListener(ma);
	}
	/**
	 * @param MODE mode that the mouseadapter will be operating on. Changed by clicking the buttons on the draw object editor
	 */
	public void setMode(String MODE) {
		mode = MODE;
		selectedshape = "";
		selectedindex = -1;
		repaint();
	}
	/**
	 * sets the click counter to 0, in order to create new shapes
	 */
	public void resetClicks() {
		clicks = 0;
	}
	/**
	 * deletes the selected shape
	 */
	public void delshape() {
		if (selectedshape.contentEquals("line")) {
			lines.remove(selectedindex);
			linecolors.remove(selectedindex);
		}
		if (selectedshape.contentEquals("circle")) {
			circles.remove(selectedindex);
			circlecolors.remove(selectedindex);
		}
		if (selectedshape.contentEquals("poly")) {
			shapes.remove(selectedindex);
			polycolors.remove(selectedindex);
		}
		selectedindex = -1;
		selectedshape = "";
		repaint();
		mode = "";
		fk.setbuttons("other");
	}
	/**
	 * clones and creates a new version of the selected shape
	 */
	public void copyshape() {
		if (selectedshape.contentEquals("line")) {
			lines.add(new Line2D.Double(lines.get(selectedindex).getX1()+20, lines.get(selectedindex).getY1(), lines.get(selectedindex).getX2()+20, lines.get(selectedindex).getY2()));
			linecolors.add(linecolors.get(selectedindex));
		}
		if (selectedshape.contentEquals("circle")) {
			circles.add(new Ellipse2D.Double(circles.get(selectedindex).getX()+20, circles.get(selectedindex).getY(), circles.get(selectedindex).getWidth(), circles.get(selectedindex).getWidth()));
			circlecolors.add(circlecolors.get(selectedindex));
			
			
		}
		if (selectedshape.contentEquals("poly")) {
			Polygon copypoly = new Polygon();
			for (int i=0;i<shapes.get(selectedindex).npoints;i++) {
				copypoly.addPoint(shapes.get(selectedindex).xpoints[i]+20, shapes.get(selectedindex).ypoints[i]);
			}
			shapes.add(copypoly);			
			polycolors.add(polycolors.get(selectedindex));
		}
		selectedindex = -1;
		selectedshape = "";
		repaint();
		mode = "";
		fk.setbuttons("other");
	}
	/**
	 * randomizes the color of the selected shape
	 */
	public void randomcolor() {
		int r = (int) (Math.random()*255);
		int g = (int) (Math.random()*255);
		int b = (int) (Math.random()*255);
		if (selectedshape.contentEquals("line")) {
			linecolors.set(selectedindex, new Color(r, g, b));
			
		}
		if (selectedshape.contentEquals("circle")) {
			circlecolors.set(selectedindex, new Color(r, g, b));
					
		}
		if (selectedshape.contentEquals("poly")) {
			polycolors.set(selectedindex, new Color(r, g, b));
		}
		selectedshape ="";
		selectedindex = -1;
		
		
		selectedindex = -1;
		selectedshape = "";
		repaint();
		mode = "";
		fk.setbuttons("other");
		
		
	}

	/**
	 * sets the state of the moving variable, triggered by clicking the move button.
	 * tells the mouseadapter whether the program is moving a shape
	 */
	public void move() {
		moving = true;
	}
	
	/**
	 * the repaint function. repaints the canvas every time there is a change according to the stored shapes
	 */
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		
		this.setBackground(Color.WHITE);
		g.setColor(Color.BLACK);
		if (dotsx != null) {
			
			for (int j=0;j<dotsx.size();j++) {
				g.drawOval(dotsx.get(j), dotsy.get(j),1, 1);
				
			}
		}
		
		if (lines != null) {
			for (int i=0;i<lines.size();i++) {
				
				g2.setColor(linecolors.get(i));
				if (selectedshape.contentEquals("line") && selectedindex == i) {
					g2.setColor(Color.GREEN);					
				}
				g2.draw(lines.get(i));
				
				
			}
		}
		if (circles != null) {
			for (int i=0;i<circles.size();i++) {
				g2.setColor(circlecolors.get(i));
				g2.fill(circles.get(i));
				g2.setColor(Color.BLACK);
				if (selectedshape.contentEquals("circle") && selectedindex == i) {
					g2.setColor(Color.GREEN);
				}
				g2.draw(circles.get(i));
			}
		}
		if (shapes !=null) {
			for (int i=0;i<shapes.size();i++) {
				g2.setColor(polycolors.get(i));
				g2.fill(shapes.get(i));
				g2.setColor(Color.BLACK);
				if (selectedshape.contentEquals("poly") && selectedindex == i) {
					g2.setColor(Color.GREEN);
				}
				g2.draw(shapes.get(i));
				
			}
		}
		g.setColor(Color.BLACK);
		
	}
	
}
