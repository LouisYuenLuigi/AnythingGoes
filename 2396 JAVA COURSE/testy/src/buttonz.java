import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
public class buttonz extends JFrame {
	private JButton butt1;
	private JButton butt2;
	private JButton butt3;
	private JButton butt4;
	private JButton butt5;
	private JButton butt6;
	private JButton butt7;
	private JButton butt8;
	private JButton butt9;
	
	public buttonz() {
		super("BUTTONZ NIGGA");
		setLayout(new FlowLayout());
		JPanel p = new JPanel();
		p.setLayout(null);
		super.add(p);
		
		
		butt1 = new JButton("Line");
		butt1.setBounds(10, 0, 120, 40);
		p.add(butt1);
		butt3 = new JButton("Circle");
		butt3.setBounds(140, 0, 120, 40);
		p.add(butt3);
		butt4 = new JButton("Triangle");
		butt4.setBounds(270, 0, 120, 40);
		p.add(butt4);
		butt5 = new JButton("Quadrilateral");
		butt5.setBounds(10, 50, 120, 40);
		p.add(butt5);
		butt6 = new JButton("Select");
		butt6.setBounds(140, 50, 120, 40);
		p.add(butt6);
		butt7 = new JButton("Move");
		butt7.setBounds(270, 50, 120, 40);
		p.add(butt7);
		butt9 = new JButton("Delete");
		butt9.setBounds(10, 100, 120, 40);
		p.add(butt9);
		butt8 = new JButton("Copy");
		butt8.setBounds(140, 100, 120, 40);
		p.add(butt8);		
		butt2 = new JButton ("Random Color");
		butt2.setBounds(270, 100, 120, 40);
		p.add(butt2);
		
		Handler handler = new Handler();
		butt1.addActionListener(handler);
		butt2.addActionListener(handler);
		butt3.addActionListener(handler);
		butt4.addActionListener(handler);
		butt5.addActionListener(handler);
		butt6.addActionListener(handler);
		butt7.addActionListener(handler);
		butt8.addActionListener(handler);
		butt9.addActionListener(handler);
	}
	
	private class Handler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "jojo yes "+ e.getActionCommand());
			
		}
		
	}

}
