import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class gooi extends JFrame{
	
	private JPanel mousepanel;
	private JLabel statusbar;
private ArrayList<coor> fucks;
	public gooi() {
		super("title");
		fucks = new ArrayList<coor>();
		mousepanel = new JPanel();
		mousepanel.setBackground(Color.WHITE);
		add(mousepanel, BorderLayout.CENTER);
		
		statusbar = new JLabel("default");
		add (statusbar, BorderLayout.SOUTH);
		
		HandlerClass handler = new HandlerClass();
		mousepanel.addMouseListener(handler);
		mousepanel.addMouseMotionListener(handler);
		
	}
	public ArrayList<coor> giveFucks(){
		return fucks;
	}
	private class HandlerClass implements MouseListener, MouseMotionListener{
		public void mouseClicked(MouseEvent event) {
			statusbar.setText("Blacked at "+ event.getX() + " and "+ event.getY());
			coor fuck = new coor(event.getX(),event.getY());
			fucks.add(fuck);
		}

		
		public void mouseDragged(MouseEvent e) {
			statusbar.setText("Mouse dragging!");
			
			
		}

		
		public void mouseMoved(MouseEvent e) {
			statusbar.setText("you moved the mouse!");
			
		}

		
		public void mouseEntered(MouseEvent e) {
			statusbar.setText("Mouse entered area!");
			mousepanel.setBackground(Color.GREEN);
			
		}

		
		public void mouseExited(MouseEvent e) {
			statusbar.setText("Mouse exited area!");
			mousepanel.setBackground(Color.WHITE);
			
		}

		
		public void mousePressed(MouseEvent e) {
			statusbar.setText("Mouse pressed!");
			
		}

		
		public void mouseReleased(MouseEvent e) {
			statusbar.setText("Mouse released!");
			
		}
	}


}
