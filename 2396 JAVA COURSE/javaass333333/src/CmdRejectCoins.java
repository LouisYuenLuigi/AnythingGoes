
/**
 * @author Yuen Chun Hin Luigi
 * The reject coins class, which ejects all the coins from the coin slot
 */
public class CmdRejectCoins extends Command {
	/** the execute command, which takes in the parameters and rejects the coins from the coin slot
	 * return statement the string form of the output to be printed. Including list of rejected coins and total value
	 */
	public String execute(VendingMachine v, String cmdPart) {
		String statement = "Rejected ";
		int size = v.getcoinSlot().size();
		int total = 0;
		if (size == 0) {
			statement = statement + "no coin!";
			return statement;
		}
		else {
			for (int i=0;i<size-1;i++) {
				statement = statement + "$" + v.getcoinSlot().get(i) + ", ";
				total = total + v.getcoinSlot().get(i);
				
			}
			total = total + v.getcoinSlot().get(size-1);
			statement = statement + "$" + v.getcoinSlot().get(size-1) + ". $" + total + " in Total.";
			v.clearcoinSlot();
			return statement;
		}
		
	}

}
