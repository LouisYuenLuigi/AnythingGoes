import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class connector implements Runnable {
	private static ArrayList<clientHandler> clients;
	private static ExecutorService pool;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;

	private void runit() throws IOException{
		clients = new ArrayList<clientHandler>();
		pool = Executors.newFixedThreadPool(5);
		
		server = new ServerSocket(9000);			
		while(true) {
			System.out.println("[SERVER] Waiting for client to connect...");
			connection = server.accept();
			System.out.println("[SERVER] Connected to client!");
			clientHandler clientThread = new clientHandler(connection);
			clients.add(clientThread);
			clientHandler.addClient();
			pool.execute(clientThread);	
		}
	}
	public void run() {
		try {
			runit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
