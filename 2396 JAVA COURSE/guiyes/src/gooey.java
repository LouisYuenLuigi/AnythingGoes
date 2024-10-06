import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class gooey implements ActionListener {
	
	private JFrame jf;
	int R1;
	int G1;
	int B1;
	int R2;
	int G2;
	int B2;
	JLabel jlR1;
	JLabel jlG1;
	JLabel jlB1;
	JLabel jlR2;
	JLabel jlG2;
	JLabel jlB2;
	
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
		dp.setLayout(new BoxLayout(dp, BoxLayout.Y_AXIS));
		
		
		R1 = 255;
		G1 = 215;
		B1 = 0;
		R2 = 255;
		G2 = 215;
		B2 = 0;
		
		jlR1 = new JLabel("RED 1: " + R1);
		jlG1 = new JLabel("GREEN 1: " + G1);
		jlB1 = new JLabel("BLUE 1: " + B1);
		
		jlR2 = new JLabel("RED 2: " + R2);
		jlG2 = new JLabel("GREEN 2: " + G2);
		jlB2 = new JLabel("BLUE 2: " + B2);
		
		dp.add(jlR1);
		dp.add(jlG1);
		dp.add(jlB1);
		
		dp.add(jlR2);
		dp.add(jlG2);
		dp.add(jlB2);
		
		jf.getContentPane().add(BorderLayout.SOUTH,jb);
		jf.getContentPane().add(BorderLayout.CENTER,dp);
		jf.setSize(300,300);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}
	public void actionPerformed(ActionEvent ae) {
		R1 = (int) (Math.random() * 255);
		G1 = (int) (Math.random() * 255);
		B1 = (int) (Math.random() * 255);
		R2 = (int) (Math.random() * 255);
		G2 = (int) (Math.random() * 255);
		B2 = (int) (Math.random() * 255);
		jf.repaint();
		jlR1.setText("RED 1: " + R1);
		jlG1.setText("GREEN 1: " + G1);
		jlB1.setText("BLUE 1: " + B1);
		jlR2.setText("RED 2: " + R2);
		jlG2.setText("GREEN 2: " + G2);
		jlB2.setText("BLUE 2: " + B2);
	}


	class MyDrawPanel extends JPanel {
		
		public MyDrawPanel() {
			System.out.println("GY");
			

			
		}
		

		
		public void paintComponent(Graphics g) {
			System.out.println("GAfuckY");
			// generate 2 random colors start & end
			Graphics2D g2d = (Graphics2D) g;

			Color start = new Color(R1,G1,B1);

			Color end = new Color(R2,G2,B2);

			GradientPaint gp = new GradientPaint(getWidth() / 2 - 100, getHeight() / 2 - 100,start, getWidth() / 2, getHeight() / 2,end);
			
			g2d.setPaint(gp);
			g2d.fillOval(getWidth() / 2 - 50,getHeight() / 2 - 50,100,100);
			System.out.println("G 2");
		}
	}


}
