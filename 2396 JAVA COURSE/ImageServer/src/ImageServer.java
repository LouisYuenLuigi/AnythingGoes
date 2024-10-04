import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import java.util.Base64;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



/**
 * @author Yuen Chun Hin Luigi
 * the server class
 *
 */
public class ImageServer extends JFrame{
	private BufferedImage bfg;
	private String imgAddress;
	private ServerSocket server;
	private Set<PrintWriter> writers = new HashSet<>();
	private static ExecutorService pool;
	private JPanel mainpanel;
	private ArrayList<Integer> order;
	private canvas pc;
	private Integer clients;
	private ArrayList<User> users;
	private Commands c;
	
	
	
	/**
	 * @throws IOException Input Output exception
	 * Constructor for the ImageServer 
	 */
	public ImageServer() throws IOException{
		super("Image Server");
		c = new Commands();
		users = new ArrayList<User>();
		mainpanel = new JPanel();
		mainpanel.setLayout(null);
		canvas cv = new canvas(this);
		this.pc = cv;
		cv.setLayout(null);
		cv.setBounds(0, 0, 700, 700);
		JButton load = new JButton("Load another image");
		load.setBounds(0, 700, 700, 30);
		mainpanel.add(load);
		mainpanel.add(cv);
		add(mainpanel);
		loadnew();
		getUsers();
		order = new ArrayList<Integer>();
		for (int i=0;i<25;i++) {
			order.add(i);
		}
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadnew();
				
			}	
		});
		
		
	}
	
	/**
	 * lets the user choose the user.txt file for logins
	 */
	public void getUsers() {
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new java.io.File("."));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File","txt");
		fc.setFileFilter(filter);
		fc.setDialogTitle("Browse the users file");
		if (fc.showOpenDialog(new JButton()) == JFileChooser.APPROVE_OPTION) {	
		}
		if (fc.getSelectedFile() != null) {
			String filename = fc.getSelectedFile().getName();
			
			if (filename.substring(filename.length()-4,filename.length()).contentEquals(".txt")) {
				File f = fc.getSelectedFile();
				FileInputStream fis;
				try {
					fis = new FileInputStream(f);
					Scanner ois = new Scanner(fis);
					
					while(ois.hasNext()) {
						
						String nextLine = ois.nextLine();
						String[] tokens = nextLine.split(";|\\:");
						User vip = new User();
						vip.setUsername(tokens[1]);
						vip.setHashedPass(tokens[3]);
						users.add(vip);
						
					}
					ois.close();
					
					//for (int j=0;j<users.size();j++) {
						//System.out.println(users.get(j).getUsername()+" and "+ users.get(j).getHashedPass());
					//}
					
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(new JButton(), "File users.txt not found!");
				}
				
			}
		}	
	}
	
	
	
	/**
	 * @param bi the buffered image to be converted into string
	 * @return the string form of the image
	 * @throws IOException Input Output Exception
	 */
	public String imgToString(BufferedImage bi) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bi, "jpg", baos );		
		byte[] imageInByte = baos.toByteArray();
		String encodedfile = new String(Base64.getEncoder().encode(imageInByte));
		baos.close();
		return encodedfile;
	}
		
	/**
	 * the function for the server to start receiving connections from clients
	 */
	public void takeConnections() {
		pool = Executors.newFixedThreadPool(200);
		clients = 0;
		try {
			server = new ServerSocket(9000);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(true) {
			try {
				//System.out.println("[SERVER] Waiting for client to connect...");
				Socket newman = server.accept();
				pool.execute(new Handler(newman));
				//System.out.println("[SERVER] Connected to client!");	
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @param newpic new image to be updated
	 * @param newlist new list of integer to be updated
	 * sends the image and new order of image blocks to the clients
	 */
	public void sendImage(String newpic, ArrayList<Integer> newlist) {
		
		for (PrintWriter writer : writers) {
			writer.println("newimg");
			writer.println(newpic);
			writer.println(newlist.size());
			for (int i=0;i<newlist.size();i++) {
				writer.println(newlist.get(i));
			}
		}
		
	}
	
	/**
	 * @param slam the new integer list to be updated
	 * sends the new block order to the clients
	 */
	public void sendOrder(ArrayList<Integer> slam) {
		for (PrintWriter writer : writers) {
			writer.println("neworder");
			writer.println(slam.size());
			for (int i=0;i<slam.size();i++) {
				writer.println(slam.get(i));
			}
		}
	}
		
	/**
	 * import an image
	 */
	public void loadnew() {
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new java.io.File("."));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG file", "jpg");
		fc.setFileFilter(filter);
		fc.setDialogTitle("Browse the image");
		if (fc.showOpenDialog(new JButton()) == JFileChooser.APPROVE_OPTION) {	
		}
		
		imgAddress = fc.getSelectedFile().getAbsolutePath();
		bfg = new BufferedImage(700,700,BufferedImage.TYPE_INT_ARGB);
		try {
			bfg = ImageIO.read(new File(imgAddress));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pc.resetSeq();
		bfg = resize(bfg,700,700);
		pc.paintit(bfg);
		try {
			sendImage(imgToString(bfg), pc.getOrder());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @param img image to be resized
	 * @param newW new width
	 * @param newH new height
	 * @return resized image
	 */
	public static BufferedImage resize(BufferedImage img, int newW, int newH) {  
	    int w = img.getWidth();  
	    int h = img.getHeight();  
	    BufferedImage newimg = new BufferedImage(newW, newH, img.getType());  
	    Graphics2D g = newimg.createGraphics();  
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	    RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
	    g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);  
	    g.dispose();  
	    return newimg;  
	}
	/**
	 * @param output the output connected to the client
	 * @throws IOException input output exception
	 * helps initialize the client imagepeer by giving it orders and sending image and block order
	 */
	public void newUser(PrintWriter output) throws IOException {
		addUser();
		output.println("new");
		output.println(clients);
		output.println(imgToString(bfg));
		output.println(pc.getOrder().size());
		for (int i=0;i<pc.getOrder().size();i++) {
			output.println(pc.getOrder().get(i));
		}
	}
	/**
	 * adds and counts the number of users, helps keep track of users and assign imagepeer number
	 */
	public synchronized void addUser() {
		clients++;
	}
	/**
	 * @param un username from client
	 * @param pw password from client
	 * @return bool value of whether login was successful
	 * checks if the user has inputted his credentials correctly
	 */
	public boolean checkLogin(String un, String pw) {
		for (int i=0;i<users.size();i++) {
			//System.out.println(c.hash(pw));
			//System.out.println(users.get(i).getHashedPass());
			if (un.contentEquals(users.get(i).getUsername())) {
				if (c.hash(pw).contentEquals(users.get(i).getHashedPass()) && users.get(i).getAccountLocked() == false) {
					users.get(i).resetLogins();
					return true;
				}
				else {
					users.get(i).loginfail();
					
				}
			}
		}
		return false;
	}
	
	/**
	 * @author Yuen Chun Hin Luigi
	 * Handler class for the threads, used for handling each client connection
	 *
	 */
	public class Handler implements Runnable{
		private Scanner input;
		private PrintWriter output;
		private Socket sock;
		
		/**
		 * @param socket the server/client socket
		 * constructor for the handler
		 */
		public Handler(Socket socket) {
			this.sock = socket;
		}

		/**
		 * the main flow for each serverside connection to every client
		 */
		@Override
		public void run() {
			//System.out.println("Connected "+sock);
			try {
				
				input = new Scanner(sock.getInputStream());
				output = new PrintWriter(sock.getOutputStream(), true);
				writers.add(output);
				
				while (input.hasNextLine()) {
					String command = input.nextLine();

					//System.out.println("Server Received: " + command);
					
					if (command.contentEquals("login")) {
						String cockk = input.nextLine();
						String psww = input.nextLine();
						if (checkLogin(cockk, psww) == true){
							output.println("result");
							output.println("allowed");
							newUser(output);
						}
						else {
							output.println("result");
							output.println("nah fam");
						}
						
						
						
					}
					
					if (command.contentEquals("neworder")){
						ArrayList<Integer> neworder = new ArrayList<Integer>();
						Integer turns = Integer.parseInt(input.nextLine());
						for (int i=0;i<turns;i++) {
							neworder.add(Integer.parseInt(input.nextLine()));
						}
						pc.change(neworder);
					}
					

					// broadcast updated number to all clients
					sendOrder(pc.getOrder());

				}


			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// client disconnected
				if (output != null) {
					writers.remove(output);
				}
			}

		}
		
	}
	
	/**
	 * @param args main function argument
	 * the main function for the server, starts the server.
	 */
	public static void main(String[] args) {
		try {
			ImageServer IS = new ImageServer();
			IS.setVisible(true);
			IS.setResizable(false);
			IS.setSize(700,759);
			IS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			IS.setLocationRelativeTo(null);
			IS.takeConnections();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}