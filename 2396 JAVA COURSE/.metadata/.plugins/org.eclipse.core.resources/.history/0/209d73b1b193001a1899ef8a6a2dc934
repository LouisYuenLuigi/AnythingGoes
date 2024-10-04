
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class clientHandler implements Runnable{
	private Socket Client;
	static int users = 0;
	
	public static void addClient() {
		users++;
	}
	
	public clientHandler(Socket clientSocket) {
		this.Client = clientSocket;
	}
	

	public void run() {
		
		try {
			PrintWriter pr = new PrintWriter(Client.getOutputStream(),true);
			pr.println(users);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
