import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.ArrayList;

/**
 * @author Yuen Chun Hin Luigi
 * The commands class, which implements the Hash interface, containing all the assisting functions used in main
 *
 */
public class Commands implements Hash {
	private ArrayList<User> users;
	private ArrayList<String> inputs;
	private int inputIndex;

	/**
	 * Constructor for the commands class, initializes a few of the needed variables
	 */
	public Commands() {
		users = new ArrayList<User>();
		inputs = new ArrayList<String>();
		inputIndex = -1;
	}
	/**
	 * Prints the first message of the output
	 */
	public void firstmessage() {
		System.out.println("Welcome to the COMP2396 Authentication System!\n1. Authenticate user\n2. Add user record\n3. Edit user record\nWhat would you like to perform?\nPlease enter your command (1-3, or 0 to terminate the system):");
	}
	
	/**
	 * @param u User to be added
	 * adds the User object to an arraylist to be accessed later
	 */
	/**
	 * gets all the input and appends each line into the list of inputs
	 */
	public void getInput() {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String inputLine = "";
		while (!inputLine.contentEquals("0")) {
			try {
				inputLine = input.readLine();
				}
				catch (IOException e) {
				System.out.print("Input Error.");
				}
				this.inputs.add(inputLine);
		}
	}
	/**
	 * @return The input of that line in string form
	 */
	public String readInput() {
		this.inputIndex++;
		return this.inputs.get(inputIndex);
		
	}
	
	/**
	 * @param the password to be hashed
	 * @return the hashed string of the password
	 */
	public String hash(String message) {
		try {
			MessageDigest p = MessageDigest.getInstance(algorithm);
			p.update(message.getBytes());
			byte[] hash = p.digest();

			return bytesToString(hash);

		}
		catch(Exception e) {	
		}
		return "Error";
	}
	private final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();	
	/**
	 * @param the byte array after hashing
	 *@return the string of the hashed password, converted from byte array
	 */
	public String bytesToString(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for (int j = 0; j < bytes.length; j++) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = HEX_ARRAY[v >>> 4];
	        hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
	    }
	    return new String(hexChars);
	    
	}
	public void addUser(User u) {
		this.users.add(u);
	}
	
	/**
	 * @return list of users
	 * accesses the list of users
	 */
	public ArrayList<User> getUsers(){
		return users;
	}
	
	/**
	 * @param index index of username to search
	 * @return username of the User to be searched
	 */
	public String getUsername(Integer index) {
		return getUsers().get(index).getUsername();
	}
	/**
	 * @param username to be checked if duplicated
	 * @return boolean value of whether username is taken
	 */
	public boolean usernameTaken(String username) {
		for (int i=0;i<getUsers().size();i++) {
			if (username.contentEquals(getUsername(i))) {
				return true;
			}
			
		}
		return false;
	}
	/**
	 * @param index index of password to get
	 * @return the hashed password
	 */
	public String getHashedPass(Integer index) {
		return getUsers().get(index).getHashedPass();
	}
	

	/**
	 * @param u users account that was attempted but failed
	 *  adds to the number of failed logins to the user
	 */
	public void loginFail(User u) {
		u.loginfail();
	}
	/**
	 * @param u the user to be examined
	 * @return the boolean value of whether the account is locked
	 */
	public boolean getLocked(User u) {
		return u.getAccountLocked();
	}
	/**
	 * @param u user to be reset
	 * resets the logincount of the user
	 */
	public void resetLogins(User u) {
		u.resetLogins();
	}
	

	/**
	 * @param p password to be checked
	 * @return boolean value of whether password meets requirements
	 */
	public boolean checkPass(String p) {
		if (p.length() >= 6) {
			if (checkfor(p)) {
				return true;
			}
			else {
				
				return false;
			}
			
		}
		else {
			
			return false;
		}
	}
	private String numberList = "1234567890";
	private String upperList = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private String lowerList = "abcdefghijklmnopqrstuvwxyz";
	/**
	 * @param pass password to be checked
	 * @return boolean value of whether password has lower, upper and number character. An assistant function for checkpass
	 */
	private boolean checkfor(String pass) {
		boolean hasnum = false;
		boolean hasup = false;
		boolean haslow = false;
		char ind;
		for (int i=0;i<pass.length();i++) {
			ind = pass.charAt(i);
			if (numberList.indexOf(ind) != -1) {
				hasnum = true;
			}
			else if(upperList.indexOf(ind) != -1) {
				hasup = true;
			}
			else if(lowerList.indexOf(ind) != -1) {
				haslow = true;
			}
			if (hasnum && hasup && haslow) {
				return true;
			}
		}
		return false;
	}
	

}
