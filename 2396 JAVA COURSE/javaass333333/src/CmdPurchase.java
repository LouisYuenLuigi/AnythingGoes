import java.util.ArrayList;
/**
 * @author Yuen Chun Hin Luigi
 * The purchase class, which controls whether the soft drink can be bought, and calculates change accordingly
 */
public class CmdPurchase extends Command {
	/** The execute command, takes in parameters and conducts the purchase (if possible)
	 * return statement The string form of the output to be printed. Includes whether purchase is successful, amount of change, list of coins given
	 */
	public String execute(VendingMachine v, String cmdPart) {
		
		String statement = "Purchasing "+ cmdPart + "... ";
		int pos = 0;
		for (int i=0;i<v.getsoftDrinkSlots().size();i++) {
			if (cmdPart.contentEquals(v.getsoftdrinkname(i))) {
				pos = i;
			}
		}
		if (v.getsoftdrinkquantity(pos) == 0) {
			statement = statement + "Out of stock!";
			return statement;
		}
		else if (v.getsoftdrinkquantity(pos) > 0) {
			int total=0;
			for (int i=0;i<v.getcoinSlot().size();i++) {
				total = total + v.getcoinSlot().get(i);
			}
			if (total<v.getsoftdrinkprice(pos)) {
				statement = statement + "Insufficient amount! Inserted $" + total +" but needs $" + v.getsoftdrinkprice(pos)+".";
				return statement;
			}
			else if (total == v.getsoftdrinkprice(pos)) {
				statement = statement + "Success! Paid $"+total+". No change.";
				v.purchasesoftdrink(pos);
				for (int i=0;i<v.getcoinSlot().size();i++) {
					v.addCoinToCoinChanger(v.getcoinSlot().get(i));
				}
				v.clearcoinSlot();
				return statement;
			}
			else if (total > v.getsoftdrinkprice(pos)) {
				int change = total - v.getsoftdrinkprice(pos);
				int changertotal = 0;
				for (int i=0;i<v.getcoinChanger().size();i++) {
					changertotal = changertotal + v.getcoinChanger().get(i);
				}
				if (change > changertotal) {
					return statement + "Insufficient change!";
				}
				else if (change <= changertotal) {
					int changeattempt=0;
					ArrayList<Integer> changelist = new ArrayList<Integer>();
					for (int i=v.getcoinChanger().size()-1;i>-1;i--) {
						if (v.getcoinChanger().get(i) <= change - changeattempt) {
							changeattempt= changeattempt + v.getcoinChanger().get(i);
							changelist.add(v.getcoinChanger().get(i));
							if (changeattempt == change) {
								int temp;
								boolean sorted = false;
								while (sorted != true) {
									sorted = true;
									for (int j=0;j<changelist.size()-1;j++) {
										if (changelist.get(j) > changelist.get(j+1)) {
											temp = changelist.get(j);
											changelist.set(j, changelist.get(j+1));
											changelist.set(j+1,  temp);
											sorted = false;
										}
									}
								}
								statement = statement + "Success! Paid $"+total+". Change: ";
								for (int p=0;p<changelist.size()-1;p++) {
									v.removecoinChanger(changelist.get(p));
									statement = statement + "$" + changelist.get(p) +", ";
								}
								v.removecoinChanger(changelist.get(changelist.size()-1));
								statement = statement +"$"+changelist.get(changelist.size()-1)+".";

								return statement;
							}
						}
					}
					changelist.clear();
					return statement + "Insufficient change!";
				}
			}
			
		}
		return null;
	}

}
