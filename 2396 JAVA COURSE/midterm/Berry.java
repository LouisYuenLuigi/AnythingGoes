
/**
 * @author Yuen Chun Hin Luigi
 * The subclass Berry, which extends Fruit
 *
 */
public class Berry extends Fruit{
	
	/**
	 * Constructor for the Berry, initializes the type and weight
	 * @param weight weight of the berry
	 */
	public Berry(double weight) {
		this.weight = weight;
		this.type = "berry";
	}

	/**
	 * The deseed method, prints some statements after deseeding
	 */
	public void deseed() {
		System.out.print("Seed from one "+weight+"g ");
		System.out.print(this);
		System.out.println(" removed");
	}
	/**
	 *the prepare method, calls the deseed method.
	 */
	public void prepare() {
		deseed();
	}
		
}
