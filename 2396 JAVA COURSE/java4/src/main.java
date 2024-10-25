
/**
 * @author Yuen Chun Hin Luigi
 * The main function, directs the flow of the program
 *
 */
public class main {

	/**
	 * @param args main function arg
	 * main function, contains the instructions for the 1,2,3,0 commands. Executes commands by calling the Commands class.
	 */
	public static void main(String[] args) {
		Commands c = new Commands();
		c.firstmessage();
		c.getInput();
		
		
		boolean ended = false;
		while (ended == false) {
			String cmd = new String();
			cmd = c.readInput();
			
			if (cmd.contentEquals("0")){
				ended = true;
			}
			else if (cmd.contentEquals("1")||cmd.contentEquals("3")) {
				
				boolean foundUser = false;
				User modify = new User();
				System.out.println("Please enter your username:");
				String authname = new String();
				authname = c.readInput();
				System.out.println("Please enter your password:");
				String authpass = new String();
				authpass = c.readInput();
				for (int j=0;j<c.getUsers().size();j++) {
					if (authname.contentEquals(c.getUsername(j))){
						foundUser = true;

						if (c.hash(authpass).contentEquals(c.getHashedPass(j))) {
							if (c.getLocked(c.getUsers().get(j))) {
								System.out.println("Login failed! Your account has been locked!");
								break;
							}
							else {
								System.out.println("Login success! Hello " + c.getUsername(j)+"!");
								modify = c.getUsers().get(j);
								c.resetLogins(c.getUsers().get(j));
								if (cmd.contentEquals("3")) {
									boolean passGood = false;
									while (passGood != true) {
										System.out.println("Please enter your new password:");
										String pw = new String();
										pw = c.readInput();
										if (c.checkPass(pw)) {
											modify.setHashedPass(c.hash(pw));
											System.out.println("Please re-enter your new password:");
											if (c.hash(c.readInput()).contentEquals(modify.getHashedPass())) {
												System.out.println("Please enter your new full name:");
												modify.setFullname(c.readInput());
												System.out.println("Please enter your new email address:");
												modify.setEmail(c.readInput());
												
												System.out.println("Record update successfully!");
												
												passGood = true;
											}
											else {
												System.out.println("New passwords do not match, user record not edited!");
												break;
											}
										}
									}
									
									
								}
								break;
							}
							
						}
						else {
							String locked = new String();
							locked = "";
							if (c.getLocked(c.getUsers().get(j))) {
								locked = " Your account has been locked!";
							}
							System.out.println("Login failed!" + locked);
							c.loginFail(c.getUsers().get(j));
							break;
						}
					}
				}
				if (!foundUser) {
					System.out.println("User not found!");
				}
				
				
				System.out.println("Please enter your command (1-3, or 0 to terminate the system):");
			}
			else if (cmd.contentEquals("2")) {
				User newguy = new User();
				System.out.println("Please enter your username:");
				String uname = new String();
				uname = c.readInput();
				if (!c.usernameTaken(uname)) {
					newguy.setUsername(uname);
					boolean passOK = false;
					while (passOK != true) {
						System.out.println("Please enter your password:");
						String pass = new String();
						pass = c.readInput();
						if (c.checkPass(pass)) {
							newguy.setHashedPass(c.hash(pass));
							System.out.println("Please re-enter your password:");
							if (c.hash(c.readInput()).contentEquals(newguy.getHashedPass())) {
								System.out.println("Please enter your full Name:");
								newguy.setFullname(c.readInput());
								System.out.println("Please enter your email address:");
								newguy.setEmail(c.readInput());
								System.out.println("Please enter your phone number:");
								newguy.setPhone(c.readInput());
								System.out.println("Record added successfully!");
								c.addUser(newguy);
								passOK = true;
							}
							else {
								System.out.println("Passwords do not match, no user added!");
								newguy = null;
								break;
							}
						}
						else {
							System.out.println("Your password has to fulfil: at least 6 characters, 1 small letter, 1 capital letter, 1 digit!");
						}
					}
				}
				else {
					System.out.println("The username is already taken!");
					newguy = null;
				}
				
				
				
				System.out.println("Please enter your command (1-3, or 0 to terminate the system):");
				
			}
			else if (cmd.contentEquals("4")) {
				
			}

			
			else if (cmd.contentEquals("showusers")) {
				for (int i=0;i<c.getUsers().size();i++) {
					System.out.println(c.getUsername(i));
				}
			}
			
			
		}

	}
	


}
