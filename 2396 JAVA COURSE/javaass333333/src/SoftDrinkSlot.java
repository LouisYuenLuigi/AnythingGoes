/**
 * @author Yuen Chun Hin Luigi
 * The class object softdrinkslot, including all its method and data
 */
public class SoftDrinkSlot {
	private String name;
	private int price;
	private int quantity;
	/** Constructor of the softdrinkslot
	 * @param name name of soft drink
	 * @param price price of soft drink
	 * @param quantity quantity of soft drink available
	 */
	public SoftDrinkSlot(String name, int price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	/** gives the name of the soft drink
	 * @return name name of soft drink
	 */
	public String getname(){
		return this.name;
	}
	/** gives the price of this soft drink
	 * @return price price of this soft drink
	 */
	public int getprice() {
		return this.price;
	}
	/** gives the quantity available of this soft drink
	 * @return quantity the quantity available of this soft drink
	 */
	public int getquantity() {
		return this.quantity;
	}
	/**
	 * decreases quantity of this soft drink by one when purchased
	 */
	public void buy() {
		if (this.quantity > 0) {
			quantity--;
		}
	}
/* You may add other non-static properties and methods */
}
