import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
	
	/**
	 * @param args
	 * main function, creates and initializes the main drawobjecteditor
	 */
	public static void main(String[] args) {
		
		DrawObjectEditor doe = new DrawObjectEditor();
		doe.setVisible(true);
		doe.setResizable(false);
		doe.setSize(400,450);
		doe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		doe.setLocationRelativeTo(null);

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
		mainpanel.setLayout(null);
		Canvas canvas = new Canvas(this);
		canvas.setBounds(0, 91, 400, 360);
		buttonpanel.setLayout(null);
		buttonpanel.setBounds(0, 0, 400, 90);
		
		mainpanel.add(canvas);
		mainpanel.add(buttonpanel);
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
		
		clickHandler handler = new clickHandler(canvas);
		
		LineButton.addActionListener(handler);
		
		CircleButton.addActionListener(handler);
		TriangleButton.addActionListener(handler);
		QuadButton.addActionListener(handler);
		SelectButton.addActionListener(handler);
		MoveButton.addActionListener(handler);
		DeleteButton.addActionListener(handler);
		CopyButton.addActionListener(handler);
		RanColButton.addActionListener(handler);
		
		
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
		/**
		 * @param ddd the canvas to be edited
		 * initializes the button handler
		 */
		public clickHandler(Canvas ddd) {
			doe = ddd;
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
			
			

			
			
		}
		
	}
	
}

