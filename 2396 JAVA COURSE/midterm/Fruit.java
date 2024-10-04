
/**
 * @author Yuen Chun Hin Luigi
 * The superclass fruit
 * contains the fruit type, weight and total number of fruits
 *
 */
public class Fruit {
	protected String type;
	protected double weight;
	static int count=0;
	
	/**
	 * @return total number of fruits
	 */
	public static int getCount() {
		return count;
	}

	/**
	 * Adds 1 to the total number of fruits
	 */
	public static void addFruit() {
		count++;
	}
	
	/**
	 * Constructor for a fruit
	 */
	public Fruit() {
		this.type = "a fruit";
	}

	/**
	 *The toString method for printing itself
	 */
	public String toString() {
		return this.type;
	}
	/**
	 * prepares the fruit, prints out a statement
	 */
	public void prepare() {
		System.out.print("Cleaned ");
		System.out.println(this);
	}

}
