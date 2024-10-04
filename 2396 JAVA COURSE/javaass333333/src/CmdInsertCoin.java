/**
 * @author Yuen Chun Hin Luigi
 * The insert coin class, which controls the coin insert into the coin slot
 */
public class CmdInsertCoin extends Command {
	/**
	 *The execute command, which takes in the parameters and inserts coins.
	 *return statement the string form of the output to be printed. Including coin inserted and total value in coin slot
	 */
	@Override
	public String execute(VendingMachine v, String cmdPart) {
		Integer coin = Integer.valueOf(cmdPart);
		// Add the coin to Coin Slot
		v.addcoinSlot(coin);
		int size = v.getcoinSlot().size();
		boolean sorted = false;
		int temp;
		while (sorted != true) {
			sorted = true;
			for (int j=0;j<size-1;j++) {
				if (v.getcoinSlot().get(j) > v.getcoinSlot().get(j+1)) {
					temp = v.getcoinSlot().get(j);
					v.setcoinSlot(j, v.getcoinSlot().get(j+1));
					v.setcoinSlot(j+1,  temp);
					sorted = false;
				}
			}
		}
		int total = 0;
		for (int i=0;i<size;i++) {
			total = total + v.getcoinSlot().get(i);
		}
		String statement = "Inserted a $" + coin +" coin. $" + total + " in Total.";
		return statement;
	}
}