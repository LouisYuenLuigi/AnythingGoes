import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class gui {
	public int gayx,gayy;



	public static void main(String[] args) {
		boolean nigga = true;
		gooi got = new gooi();
		got.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		got.setSize(400, 450);
		got.setVisible(true);
		while (nigga = false) {
			System.out.println(got.giveFucks().size() + "   " + gayx + " " + gayy);
		}
		
		
		JFrame window = new JFrame("fuck");
		window.setVisible(true);
		window.setResizable(false);
		window.setSize(400,450);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		window.add(panel);
		
		JButton button = new JButton("fuckys");
		button.setBounds(100, 100, 120, 40);
		panel.add(button);
		
		
		

	}




}
