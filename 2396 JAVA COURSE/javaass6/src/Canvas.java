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
	private ArrayList<Polygon> triangles;
	private ArrayList<Polygon> quads;
	private ArrayList<Color> linecolors;
	private ArrayList<Color> circlecolors;
	private ArrayList<Color> tricolors;
	private ArrayList<Color> quadcolors;
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
		triangles = new ArrayList<Polygon>();
		quads = new ArrayList<Polygon>();
		linecolors = new ArrayList<Color>();
		circlecolors = new ArrayList<Color>();
		tricolors = new ArrayList<Color>();
		quadcolors = new ArrayList<Color>();
		selectedshape = "";
		selectedindex = -1;
		moving = false;
		hit = false;
		dotsx = new ArrayList<Integer>();
		dotsy = new ArrayList<Integer>();
		

		
		MouseAdapter ma = new MouseAdapter() {
			/**
			 * event handler for clicks, used in creating and selecting shapes
			 */
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
						triangles.add(triangle);
						tricolors.add(Color.WHITE);
						
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
						quadcolors.add(Color.WHITE);
						quads.add(quad);
						
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
					if (quads !=null) {
						for (int i=0;i<quads.size();i++) {
							if (quads.get(i).contains(e.getX(), e.getY())) {
								selectedshape = "quad";
								selectedindex = i;
								selected = true;
								fk.setbuttons(mode);
								
							}
						}
					}
					if (triangles !=null) {
						for (int i=0;i<triangles.size();i++) {
							if (triangles.get(i).contains(e.getX(), e.getY())) {
								selectedshape = "triangle";
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
			/**
			 * event handler for mousepressed, used for moving shapes
			 */
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
				if (selectedshape.contentEquals("triangle") && moving == true) {
					if (triangles.get(selectedindex).contains(e.getX(), e.getY())) {
						hit = true;
						startx=e.getX();
						starty=e.getY();
					}
				}
				if (selectedshape.contentEquals("quad") && moving == true) {
					if (quads.get(selectedindex).contains(e.getX(), e.getY())) {
						hit = true;
						startx=e.getX();
						starty=e.getY();
					}
				}
				


			}
			/**
			 *event handler for release, used in moving shapes
			 */
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
					if (selectedshape.contentEquals("triangle")) {
						Polygon copypoly = new Polygon();
						for (int i=0;i<triangles.get(selectedindex).npoints;i++) {
							copypoly.addPoint(triangles.get(selectedindex).xpoints[i]+e.getX()-startx,triangles.get(selectedindex).ypoints[i]+e.getY()-starty);
						}
						triangles.add(copypoly);
						tricolors.add(tricolors.get(selectedindex));
						triangles.remove(selectedindex);
						tricolors.remove(selectedindex);
					}
					if (selectedshape.contentEquals("quad")) {
						Polygon copypoly = new Polygon();
						for (int i=0;i<quads.get(selectedindex).npoints;i++) {
							copypoly.addPoint(quads.get(selectedindex).xpoints[i]+e.getX()-startx,quads.get(selectedindex).ypoints[i]+e.getY()-starty);
						}
						quads.add(copypoly);
						quadcolors.add(quadcolors.get(selectedindex));
						quads.remove(selectedindex);
						quadcolors.remove(selectedindex);
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
	 * @return the arraylist of lines
	 */
	public ArrayList<Line2D> getLines() {
		return lines;
	}
	/**
	 * @param lines new arraylist of lines to be updated
	 */
	public void setLines(ArrayList<Line2D> lines) {
		this.lines = lines;
	}
	/**
	 * @return the arraylist of circles
	 */
	public ArrayList<Ellipse2D> getCircles() {
		return circles;
	}
	/**
	 * @param circles new arraylist of circles to be updated
	 */
	public void setCircles(ArrayList<Ellipse2D> circles) {
		this.circles = circles;
	}
	
	/**
	 * @return arraylist of line colors
	 */
	public ArrayList<Color> getLinecolors() {
		return linecolors;
	}
	/**
	 * @param linecolors new arraylist of line colors
	 */
	public void setLinecolors(ArrayList<Color> linecolors) {
		this.linecolors = linecolors;
	}
	/**
	 * @return arraylist of circle colors
	 */
	public ArrayList<Color> getCirclecolors() {
		return circlecolors;
	}
	/**
	 * @param circlecolors new arraylist of circle colors
	 */
	public void setCirclecolors(ArrayList<Color> circlecolors) {
		this.circlecolors = circlecolors;
	}
	
	/**
	 * @return arraylist of triangles
	 */
	public ArrayList<Polygon> getTriangles() {
		return triangles;
	}

	/**
	 * @param triangles new arraylist of triangles to be set
	 */
	public void setTriangles(ArrayList<Polygon> triangles) {
		this.triangles = triangles;
	}

	/**
	 * @return arraylist of quadrilaterals
	 */
	public ArrayList<Polygon> getQuads() {
		return quads;
	}

	/**
	 * @param quads new arraylist of quadrilaterals to be set
	 */
	public void setQuads(ArrayList<Polygon> quads) {
		this.quads = quads;
	}

	/**
	 * @return arraylist of triangle colors
	 */
	public ArrayList<Color> getTricolors() {
		return tricolors;
	}

	/**
	 * @param tricolors new arraylist of triangle colors
	 */
	public void setTricolors(ArrayList<Color> tricolors) {
		this.tricolors = tricolors;
	}

	/**
	 * @return arraylist of quadrilateral colors
	 */
	public ArrayList<Color> getQuadcolors() {
		return quadcolors;
	}

	/**
	 * @param quadcolors new arraylist of quadrilateral colors
	 */
	public void setQuadcolors(ArrayList<Color> quadcolors) {
		this.quadcolors = quadcolors;
	}

	/**
	 * updates the canvas
	 */
	public void paintdat() {
		repaint();
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
		if (selectedshape.contentEquals("triangle")) {
			triangles.remove(selectedindex);
			tricolors.remove(selectedindex);
		}
		if (selectedshape.contentEquals("quad")) {
			quads.remove(selectedindex);
			quadcolors.remove(selectedindex);
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
		if (selectedshape.contentEquals("triangle")) {
			Polygon copypoly = new Polygon();
			for (int i=0;i<triangles.get(selectedindex).npoints;i++) {
				copypoly.addPoint(triangles.get(selectedindex).xpoints[i]+20, triangles.get(selectedindex).ypoints[i]);
			}
			triangles.add(copypoly);			
			tricolors.add(tricolors.get(selectedindex));
		}
		if (selectedshape.contentEquals("quad")) {
			Polygon copypoly = new Polygon();
			for (int i=0;i<quads.get(selectedindex).npoints;i++) {
				copypoly.addPoint(quads.get(selectedindex).xpoints[i]+20, quads.get(selectedindex).ypoints[i]);
			}
			quads.add(copypoly);			
			quadcolors.add(quadcolors.get(selectedindex));
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
		if (selectedshape.contentEquals("triangle")) {
			tricolors.set(selectedindex, new Color(r, g, b));
		}
		if (selectedshape.contentEquals("quad")) {
			quadcolors.set(selectedindex, new Color(r, g, b));
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
		if (triangles !=null) {
			for (int i=0;i<triangles.size();i++) {
				g2.setColor(tricolors.get(i));
				g2.fill(triangles.get(i));
				g2.setColor(Color.BLACK);
				if (selectedshape.contentEquals("triangle") && selectedindex == i) {
					g2.setColor(Color.GREEN);
				}
				g2.draw(triangles.get(i));
				
			}
		}
		if (quads !=null) {
			for (int i=0;i<quads.size();i++) {
				g2.setColor(quadcolors.get(i));
				g2.fill(quads.get(i));
				g2.setColor(Color.BLACK);
				if (selectedshape.contentEquals("quad") && selectedindex == i) {
					g2.setColor(Color.GREEN);
				}
				g2.draw(quads.get(i));
				
			}
		}
		g.setColor(Color.BLACK);
		
	}
	
}
