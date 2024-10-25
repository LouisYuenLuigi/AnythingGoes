import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;


/**
 * @author Yuen Chun Hin Luigi
 *The ImagePeer class
 */
public class ImagePeer{
	private String serverIP;
	private String username;
	private String password;
	private boolean loggedin;
	private Socket connection;
	private Scanner in;
	private PrintWriter out;
	private clientCanvas cc;
	private String state;
	private JFrame cock;
	private JLabel labor;
	
	
	/**
	 * Constructor for the imagepeer
	 */
	public ImagePeer() {		
		loggedin = false;
		cock = new JFrame("Connect and Login");
		JTextField df = new JTextField("localhost",20);
		JButton next1 = new JButton("Enter");
		JButton cancer = new JButton("Cancel");
		labor = new JLabel("Connect to Server");
		JPanel upanel = new JPanel();
		cock.setVisible(true);
		cock.setResizable(false);
		cock.setSize(300,150);
		cock.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cock.setLocationRelativeTo(null);
		state = "connect";
		
		
		
		next1.addActionListener(new ActionListener() {

			/**
			 * Action handler for the enter button, handles the input for IP, username , and password
			 */
			public void actionPerformed(ActionEvent e) {
				if (state.contentEquals("connect")) {
					serverIP = df.getText();
					try {
						runshit(serverIP);
						state = "username";
						labor.setText("Enter Username");
						
						
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(cock, "Connection Failed");
						df.setText("");
					}
				}
				else if(state.contentEquals("username")) {
					username = df.getText();
					labor.setText("Enter Password");
					state = "password";
				}
				else if(state.contentEquals("password")) {
					password = df.getText();
					try {
						login();
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				df.setText("");
			}
			
		});
		cancer.addActionListener(new ActionListener() {
			/**
			 * For closing the window and cancelling operations
			 */
			public void actionPerformed(ActionEvent e) {
				cock.dispose();
			}
		});
		upanel.add(labor);
		upanel.add(df);
		upanel.add(next1);
		upanel.add(cancer);
		cock.add(upanel);
		
	}
	
	
	/**
	 * @param numm the assigned peer number
	 * creates a new imagepeer canvas that displays the image
	 */
	public void newCanvas(String numm) {
		JFrame cock = new JFrame("Image Peer #"+numm);
		clientCanvas man = new clientCanvas(this);
		this.cc = man;
		cock.add(man);
		cock.setVisible(true);
		cock.setResizable(false);
		cock.setSize(707,728);
		cock.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cock.setLocationRelativeTo(null);
		
	}
	
	/**
	 * @param serverIP the server IP
	 * @throws IOException Input Output Exception
	 * runs the thread that communicates with the server
	 */
	private void runshit(String serverIP) throws IOException {
		this.connection = new Socket(serverIP,9000);
		this.in = new Scanner(connection.getInputStream());
		this.out = new PrintWriter(connection.getOutputStream(), true);
		
		handler clithread = new handler(connection);
		Thread t = new Thread(clithread);
		t.start();		
	}
	/**
	 * @throws IOException Input Output Exception
	 * tries to login to the server with the inputted username and password
	 */
	public void login() throws IOException{
		out.println("login");
		out.println(username);
		out.println(password);
		
	}

	
	/**
	 * @param encodedfile the string converted from an image
	 * @return the image form of the string
	 * @throws IOException Input Output Exception
	 */
	public BufferedImage stringToImg(String encodedfile) throws IOException {
		byte[] decodedString = Base64.getDecoder().decode(new String(encodedfile).getBytes("UTF-8"));
		ByteArrayInputStream bis = new ByteArrayInputStream(decodedString);
	    BufferedImage bImage2 = ImageIO.read(bis);
	    bis.close();
	    return bImage2;
	}
	
	/**
	 * @param order the order of the image blocks, stored in an integer arraylist
	 * updates the server about the new image block positions
	 */
	public void sendOrder(ArrayList<Integer> order) {
		out.println("neworder");
		out.println(order.size());
		for (int i=0;i<order.size();i++) {
			out.println(order.get(i));
		}
		
	}
	
	
	/**
	 * @author Yuen Chun Hin Luigi
	 * the handler class for handling client/server interaction
	 *
	 */
	class handler implements Runnable{
		private Socket socket;
		/**
		 * @param client the concerned socket
		 * the constructor for the handler
		 */
		public handler(Socket client) {
			this.socket = client;
		}
		/**
		 * the main flow for the thread
		 */
		@Override
		public void run() {
			try {
				readFromServer();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		public void readFromServer() throws Exception{
			try {
				while (in.hasNextLine()) {
					
					String command = in.nextLine();
					
					
					if (command.contentEquals("result")) {
						String status = in.nextLine();
							if (status.contentEquals("allowed")) {
								JOptionPane.showMessageDialog(new JButton(), "Welcome, "+username);
								loggedin = true;
								cock.dispose();
								
							}
							else if(status.contentEquals("nah fam")) {
								JOptionPane.showMessageDialog(new JButton(), "Login Failed!");
								state = "username";
								labor.setText("Enter Username");
							}
					}
					
					if (command.contentEquals("new")) {
						newCanvas(in.nextLine());						
						BufferedImage booty = stringToImg(in.nextLine());
						ArrayList<Integer> ben = new ArrayList<Integer>();
						Integer turns = Integer.parseInt(in.nextLine());						
						for (int i=0;i<turns;i++) {
							ben.add(Integer.parseInt(in.nextLine()));
						}
						cc.paintit(booty, ben);
					}
					if (command.contentEquals("newimg")) {
						BufferedImage bbg = stringToImg(in.nextLine());
						ArrayList<Integer> newans = new ArrayList<Integer>();
						Integer turns = Integer.parseInt(in.nextLine());
						for (int i=0;i<turns;i++) {
							newans.add(Integer.parseInt(in.nextLine()));
						}
						cc.paintit(bbg, newans);
					}
					if (command.contentEquals("neworder") && loggedin == true){
						ArrayList<Integer> neworder = new ArrayList<Integer>();
						Integer turns = Integer.parseInt(in.nextLine());
						for (int i=0;i<turns;i++) {
							neworder.add(Integer.parseInt(in.nextLine()));
						}
						cc.change(neworder);
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				socket.close();
			}
		}

	}

	
	/**
	 * @param args main function argument
	 * the main function, starts the imagepeer program
	 */
	public static void main(String[] args) {
		ImagePeer IP = new ImagePeer();
		
		

	}



}
