
/**
 * @author Yuen Chun Hin Luigi
 * interface class Hash, contains abstract methods. Hash function can easily be changed here by changing the name of the algorithm
 *
 */
public interface Hash {
	String algorithm = "SHA-1";
	String hash(String p);
	String bytesToString(byte[] b);
		

}
