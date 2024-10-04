
/**
 * @author Yuen Chun Hin Luigi
 * The User object
 *
 */
public class User {
	private String username;
	private String hashedPass;
	private String fullname;
	private String email;
	private String phone;
	private Integer failedLogins;
	private boolean accountLocked;
	
	/**
	 * constructor for the user object, initializes the User
	 */
	public User() {
		username = new String();
		hashedPass = new String();
		fullname = new String();
		email = new String();
		phone = new String();
		failedLogins = 0;
		accountLocked = false;
	}
	/**
	 * @return the username of the User
	 */
	public String getUsername() {
		return this.username;
	}
	/**
	 * @param newname the username to be set
	 */
	public void setUsername(String newname) {
		this.username = newname;
	}
	/**
	 * @return the hashed password
	 */
	public String getHashedPass() {
		return this.hashedPass;
	}
	/**
	 * @param newpass the hashed password to be set
	 */
	public void setHashedPass(String newpass) {
		this.hashedPass = newpass;
	}
	/**
	 * @return the full name of the user
	 */
	public String getFullname() {
		return this.fullname;
	}
	/**
	 * @param newname the full name to be set
	 */
	public void setFullname(String newname) {
		this.fullname = newname;
	}
	/**
	 * @return the user's email
	 */
	public String getEmail() {
		return this.email;
	}
	/**
	 * @param newemail the email to be set
	 */
	public void setEmail(String newemail) {
		this.email = newemail;
	}
	/**
	 * @return the user's phone number
	 */
	public String getPhone() {
		return this.phone;
	}
	/**
	 * @param newphone the user's phone number to be set
	 */
	public void setPhone(String newphone) {
		this.phone = newphone;
	}

	/**
	 * adds to the failed login count. Locks the account if there are 3+ fails
	 */
	public void loginfail() {
		this.failedLogins++;
		if (failedLogins >= 3) {
			this.accountLocked = true;
		}
	}
	/**
	 * sets the failed logins to 0
	 */
	public void resetLogins() {
		this.failedLogins = 0;
	}
	/**
	 * @return the boolean value of whether the account is locked
	 */
	public boolean getAccountLocked() {
		return this.accountLocked;
	}

}
