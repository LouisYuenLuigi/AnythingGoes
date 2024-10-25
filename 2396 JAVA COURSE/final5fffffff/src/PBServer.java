import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import java.io.*;

public class PBServer {
	private ArrayList<ObjectOutputStream> ooses;
	private static ExecutorService pool;
	
	AccessControlManager ac_store;
	Phonebook pb;
	boolean autosave = true;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PBServer cs = new PBServer();
		cs.go();
	}
	
	public void go() {		
		pool = Executors.newFixedThreadPool(200);
		ac_store = new AccessControlManager();
		pb = new Phonebook(autosave);
		if (autosave) {
			pool.execute(new AutoSaver());
		}
		
			
		try {
			
			System.out.println("Server listening");
			ServerSocket ss = new ServerSocket(5000);
			ooses = new ArrayList<ObjectOutputStream>();

			while (true) {
				Socket s = ss.accept();
				
				System.out.println("Accepting connection");
				pool.execute(new ClientHandler(s));
				
				
			}
		} catch (Exception e) { e.printStackTrace(); }
		
	}
	
	class AutoSaver implements Runnable{

		@Override
		public void run() {
			while(true) {
				try {
					Thread.sleep(5000);
					pb.autoSave();
					System.out.println("Autosaved!");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
	
	class ClientHandler implements Runnable {
		
		OutputStream os;
		ObjectOutputStream oos;
		InputStream is;
		ObjectInputStream ois;
		
		public ClientHandler(Socket s) {		
			try {
				os = s.getOutputStream();
				oos = new ObjectOutputStream(os);
				is = s.getInputStream();
				ois = new ObjectInputStream(is);
				ooses.add(oos);
			} catch (IOException e) {
				System.out.println("ClientHandler error");
				e.printStackTrace();
			}
		}
			


		@Override
		public void run() {
			
			boolean more_msg = true;
			
			while ( more_msg ) {
				
				try {
					
					SPBMessage m = (SPBMessage) ois.readObject();
					System.out.println("Received msg:" + m);
					System.out.println("Recieved command: " + m.command);
					
					if ( m.command.equals("LOGIN") ) {
						if ( ac_store.verifyPassword(m.username, m.password) ) {
							m.reply_msg = "LOGIN success";
						}
						else 
							m.reply_msg = "LOGIN fail";
						
						oos.writeObject(m);
						continue;
					}
				
					String entry_name = m.entry.name;
					String entry_recno = m.reply_msg;

					if ( ! ac_store.verifyPassword(m.username,m.password) )
					{
						m.reply_msg = "Incorrect password";
					}
					else if ( m.command.equals("GETBYNAME") )
					{
						System.out.println("GETBYNAME: " + entry_name);
						m.entry = null;
						if ( ac_store.checkReadPerm(m.username) )
						{
							int i = pb.entryExist(entry_name);
							ArrayList<Integer> cock = pb.entriesFound(entry_name);
							if ( cock != null ) 
							{
								if (cock.size() >1) {
									m.entry = pb.getEntry(i);
									m.recno = i;
									String manny = "GETBYNAME success: ";
									for (int j=0;j<cock.size()-1;j++) {
										manny = manny + cock.get(j) + ", ";
									}
									manny = manny + cock.get(cock.size()-1)+".";
									m.reply_msg = manny;
									System.out.println(m.entry.name);
								}
								if (cock.size() ==1) {
									m.entry = pb.getEntry(i);
									m.recno = i;
									m.reply_msg = "GETBYNAME success";
								}
								
							}
							else
								m.reply_msg = "Phonebook entry does not exist";
						} else {
							m.reply_msg = "Invalid permission";
						}
					}
					else if ( m.command.equals("GETBYRECNO") )
					{
						System.out.println("GETBYRECNO: " + entry_name);
						m.entry = null;
						if ( ac_store.checkReadPerm(m.username) )
						{
							int i = Integer.parseInt(entry_recno);
							if ( i >=0 ) 
							{
								m.entry = pb.getEntry(i);
								m.recno = i;
								m.reply_msg = "GETBYRECNO success";
							}
							else
								m.reply_msg = "Phonebook entry does not exist";
						} else {
							m.reply_msg = "Invalid permission";
						}
					}				
					else  if ( m.command.equals("ADD") )
					{
						if ( ac_store.checkWritePerm(m.username) )
						{
							int i = pb.entryExist(m.entry.name);
							if ( i < 0 ) 
							{
								pb.addEntry(m.entry.name, m.entry);
								int recno = pb.entryExist(m.entry.name);
								m.reply_msg = "ADD success";
								m.recno = recno;
							}
						else
								m.reply_msg = "Phonebook entry exists";
						} else {
							m.reply_msg = "Invalid permission";
						}
					}
					else if ( m.command.equals("UPDATE") )
					{
						System.out.println("UPDATE: " + entry_name);
						
						if ( ac_store.checkReadPerm(m.username) )
						{
							int i = pb.entryExist(entry_name);
							if ( i >=0 ) 
							{
								pb.UpdateEntry(i, m.entry);
								m.entry = pb.getEntry(i);
								m.recno = i;
								m.reply_msg = "UPDATE success";
							}
							else
								m.reply_msg = "Phonebook entry does not exist";
						} else {
							m.reply_msg = "Invalid permission";
						}
					}
					else if ( m.command.equals("DELETE") )
					{
						System.out.println("DELETE: " + entry_name);
						
						if ( ac_store.checkReadPerm(m.username) )
						{
							int i = pb.entryExist(entry_name);
							if ( i >=0 ) 
							{
								pb.DeletEntry(i);
								m.recno = i;
								m.reply_msg = "DELETE success";
							}
							else
								m.reply_msg = "Phonebook entry does not exist";
						} else {
							m.reply_msg = "Invalid permission";
						}
					}
					else if ( m.command.equals("LOGOFF") )
					{
						more_msg = false;
						oos.close();
						ois.close();
						return;
					}
					else if (m.command.equals("PREV")){
						System.out.println("PREV: " + entry_name);
						m.entry = null;
						if ( ac_store.checkReadPerm(m.username) )
						{
							int i = Integer.parseInt(entry_recno);
							
							if ( i >0 ) 
							{
								
								m.entry = pb.getEntry(i-1);
								m.recno = i-1;
								m.reply_msg = "PREV success";
								
							}
							else
								m.reply_msg = "Phonebook entry does not exist";
						} else {
							m.reply_msg = "Invalid permission";
						}
					}
					else if (m.command.equals("NEXT")){
						System.out.println("NEXT: " + entry_name);
						m.entry = null;
						if ( ac_store.checkReadPerm(m.username) )
						{
							int i = Integer.parseInt(entry_recno);
							
							if ( i < pb.entry_count-1 ) 
							{
								m.entry = pb.getEntry(i+1);
								m.recno = i+1;
								m.reply_msg = "NEXT success";
							}
							else
								m.reply_msg = "Phonebook entry does not exist";
						} else {
							m.reply_msg = "Invalid permission";
						}
					}

					oos.writeObject(m);

					
				
				} catch ( Exception e ) {
					System.out.println("Exception in command processing");
					e.printStackTrace();
					return;
				}
					
			}
			
		}

	}

}





