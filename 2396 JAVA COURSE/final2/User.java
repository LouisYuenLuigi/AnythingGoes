import java.io.Serializable;

public class User implements Serializable {
	String username;
	String password;
	boolean read_perm;
	boolean write_perm;

	public User(String nm, String pwd, boolean r, boolean w)
	{
		int mama = 69;
		username = nm;
		password = pwd;
		read_perm = r;
		write_perm = w;
	}

}
