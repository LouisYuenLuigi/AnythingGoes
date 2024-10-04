import java.util.ArrayList;
/**
 * @author Yuen Chun Hin Luigi
 * The vending machine class, containing all methods in the vending machine
 */
public class VendingMachine {
	private ArrayList<Integer> coinChanger;
	private ArrayList<Integer> coinSlot;
	private ArrayList<SoftDrinkSlot> softDrinkSlots;
	/**
	 * constructor for the vending machine. Initializes the arraylists needed to operate
	 */
	public VendingMachine() {
		coinChanger = new ArrayList<Integer>();
		coinSlot = new ArrayList<Integer>();
		softDrinkSlots = new ArrayList<SoftDrinkSlot>();
	}
	/** adds coin into coin changer and sorts it
	 * @param c the coin to be added into the coin changer
	 */
	public void addCoinToCoinChanger(Integer c) {
		coinChanger.add(c);
		int temp;
		boolean sorted = false;
		while (sorted != true) {
			sorted = true;
			for (int j=0;j<coinChanger.size()-1;j++) {
				if (coinChanger.get(j) > coinChanger.get(j+1)) {
					temp = coinChanger.get(j);
					coinChanger.set(j, coinChanger.get(j+1));
					coinChanger.set(j+1,  temp);
					sorted = false;
				}
			}
		}
	}
	/** adds new soft drink into soft drink slot
	 * @param s new soft drink to be added into the softdrink slots
	 */
	public void addSoftDrinkSlot(SoftDrinkSlot s) {
		softDrinkSlots.add(s);
	}
	/** gives the list of soft drinks
	 * @return the arraylist of soft drink slots
	 */
	public ArrayList<SoftDrinkSlot> getsoftDrinkSlots(){
		return softDrinkSlots;
	}
	/** gives the name of the soft drink in the requested position of the list
	 * @param pos the requested position of which softdrink slot to access in the list
	 * @return name of the requested soft drink
	 */
	public String getsoftdrinkname(Integer pos) {
		return softDrinkSlots.get(pos).getname();
	}
	/**gives the price of the soft drink in the requested position of the list
	 * @param pos the requested position of which softdrink slot to access in the list
	 * @return price of the requested soft drink
	 */
	public int getsoftdrinkprice(Integer pos) {
		return softDrinkSlots.get(pos).getprice();
	}
	/** gives the quantity of the soft drink in the requested position of the list
	 * @param pos the requested position of which softdrink slot to access in the list
	 * @return quantity of the requested soft drink
	 */
	public int getsoftdrinkquantity(Integer pos) {
		return softDrinkSlots.get(pos).getquantity();
	}
	/** acts out the purchase and subtracts the soft drink quantity by 1 by calling a function in SoftDrinkSlot
	 * @param pos the requested position of which softdrink slot to purchase in the list
	 */
	public void purchasesoftdrink(Integer pos) {
		softDrinkSlots.get(pos).buy();
	}
	/** gives the list of coins in the coin changer
	 * @return the arraylist of coins in the coin changer
	 */
	public ArrayList<Integer> getcoinChanger(){
		return coinChanger;
	}
	/** removes a coin from the coin changer
	 * @param ind value of the coin to be removed
	 */
	public void removecoinChanger(Integer ind) {
		this.coinChanger.remove(ind);
	}
	/** 
	 * Clears the coin changer of all coins
	 */
	public void clearcoinChanger() {
		this.coinChanger.clear();
	}
	/** adds coin to the coin slot
	 * @param k coin to be added into the coin slot
	 */
	public void addcoinSlot(Integer k) {
		coinSlot.add(k);
	}
	
	/** gives the list of coins in the coin slot
	 * @return the arraylist of coins in the coin slot
	 */
	public ArrayList<Integer> getcoinSlot(){
		return coinSlot;
	}
	
	/** sets a coin in the coin slot as another coin, used for sorting
	 * @param pos position of the coin to be set
	 * @param newcoin new value of the coin
	 */
	public void setcoinSlot(Integer pos, Integer newcoin){
		this.coinSlot.set(pos,  newcoin);
	}
	
	/** removes a requested coin from the coin slot
	 * @param ind the value of the coin to be removed
	 */
	public void removecoinSlot(Integer ind) {
		System.out.println("Removed" + coinSlot.get(ind));
		this.coinSlot.remove(ind);	
	}
	
	/** clears the coin slot of all coins
	 * 
	 */
	public void clearcoinSlot() {
		this.coinSlot.clear();
	}
	/* You may add other non-static properties and methods */
}