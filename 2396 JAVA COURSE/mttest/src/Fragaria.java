
/**
 * @author Yuen Chun Hin Luigi
 * The fragaria class, which extends the fruit superclass
 *
 */
public class Fragaria extends Fruit{
	
	/**
	 * Constructor for the fragaria
	 */
	public Fragaria() {
		this.type = "fragaria";
	}
	/**
	 * Clearcap method, which prints some statements
	 */
	private void clearCap() {
		System.out.print("Caps and stems removed from a ");
		System.out.println(this);
	}
	/**
	 *the prepare method, calls the clearcap method
	 */
	public void prepare() {
		clearCap();
	}
	
}
