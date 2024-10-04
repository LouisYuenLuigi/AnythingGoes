import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class gooey implements ActionListener {
	JFrame jf;
	public static void main(String[] args) {
		gooey gui = new gooey();
		gui.go();
	}
	public void go() {
		jf = new JFrame();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton jb = new JButton("Change colors");
		jb.addActionListener(this);
		MyDrawPanel dp = new MyDrawPanel();
		jf.getContentPane().add(BorderLayout.SOUTH,jb);
		jf.getContentPane().add(BorderLayout.CENTER,dp);
		jf.setSize(30,300);
		jf.setVisible(true);
	}
	public void actionPerform(ActionEvent ae) {
		jf.repaint();
	}
	public void paintComponent(Graphics g) {
		// generate 2 random colors start & end
		Graphics2D g2d = (Graphics2D) g;
		int red = (int) (Math.random() * 255);
		int green = (int) (Math.random() * 255);
		int blue = (int) (Math.random() * 255);
		Color start = new Color(red,green,blue);
	 	red = (int) (Math.random() * 255);
		green = (int) (Math.random() * 255);
		blue = (int) (Math.random() * 255);
		Color end = new Color(red,green,blue);

		// create a gradient paint from
		//  	(70,70) to (100,100)
		GradientPaint gp = new GradientPaint(
							70,70,start,150,150,end); 
		g2d.setPaint(gp);
		g2d.fillOval(70,70,100,100);
	}


	class MyDrawPanel extends JPanel {
		public void paintComponent(Graphics g) {
			g.setColor(Color.orange);
			g.fillRect(20,50,100,100);
		}
	}

}
