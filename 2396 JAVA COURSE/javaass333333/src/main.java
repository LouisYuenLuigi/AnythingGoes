import java.util.ArrayList;
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("lolololgaygaygay");
		VendingMachine v = new VendingMachine();
		v.addCoinToCoinChanger(Integer.valueOf(2));
		v.addCoinToCoinChanger(Integer.valueOf(2));
		v.addCoinToCoinChanger(Integer.valueOf(1));
		System.out.println(v.getcoinChanger());
		System.out.println();
		System.out.println(v.getcoinChanger().get(1));
		System.out.println(((Command) new CmdInsertCoin()).execute(v, "10"));
		System.out.println(((Command) new CmdInsertCoin()).execute(v, "2"));
		System.out.println(((Command) new CmdInsertCoin()).execute(v, "5"));
		System.out.println(v.getcoinSlot());
		System.out.println(((Command) new CmdRejectCoins()).execute(v, ""));
		System.out.println(v.getcoinSlot());
		System.out.println(((Command) new CmdRejectCoins()).execute(v, ""));
		System.out.println();
		v.addSoftDrinkSlot(new SoftDrinkSlot("Cock", 5, 2));
		System.out.println(((Command) new CmdInsertCoin()).execute(v, "5"));
		System.out.println(v.getcoinSlot());
		System.out.println(((Command) new CmdPurchase()).execute(v, "Cock"));
		System.out.println(((Command) new CmdInsertCoin()).execute(v, "2"));
		System.out.println(((Command) new CmdPurchase()).execute(v, "Cock"));
		System.out.println(((Command) new CmdInsertCoin()).execute(v, "2"));
		System.out.println(((Command) new CmdInsertCoin()).execute(v, "1"));
		System.out.println(((Command) new CmdPurchase()).execute(v, "Cock"));
		System.out.println(((Command) new CmdPurchase()).execute(v, "Cock"));
		System.out.println(v.getcoinChanger());
		System.out.println();
		
		v.clearcoinChanger();
		v.addCoinToCoinChanger(Integer.valueOf(2));
		v.addCoinToCoinChanger(Integer.valueOf(2));
		v.addCoinToCoinChanger(Integer.valueOf(1));
		v.addSoftDrinkSlot(new SoftDrinkSlot("Cocacola", 4, 1));
		v.addSoftDrinkSlot(new SoftDrinkSlot("Pepsi", 5, 3));
		System.out.println(((Command) new CmdInsertCoin()).execute(v, "5"));
		System.out.println(((Command) new CmdPurchase()).execute(v, "Pepsi"));
		System.out.println();
		System.out.println(((Command) new CmdInsertCoin()).execute(v, "10"));
		System.out.println(((Command) new CmdInsertCoin()).execute(v, "5"));
		System.out.println(v.getcoinChanger());
		System.out.println(((Command) new CmdPurchase()).execute(v, "Pepsi"));
		System.out.println();
		System.out.println(((Command) new CmdInsertCoin()).execute(v, "5"));
		System.out.println(((Command) new CmdInsertCoin()).execute(v, "2"));
		System.out.println(((Command) new CmdPurchase()).execute(v, "Pepsi"));
		System.out.println();
		System.out.println(((Command) new CmdRejectCoins()).execute(v, ""));
		System.out.println();
		System.out.println(((Command) new CmdInsertCoin()).execute(v, "2"));
		System.out.println(((Command) new CmdPurchase()).execute(v, "Pepsi"));
		System.out.println();
		System.out.println(((Command) new CmdInsertCoin()).execute(v, "2"));
		System.out.println(((Command) new CmdPurchase()).execute(v, "Cocacola"));
		System.out.println();
		System.out.println(((Command) new CmdInsertCoin()).execute(v, "2"));
		System.out.println(((Command) new CmdInsertCoin()).execute(v, "2"));
		System.out.println(((Command) new CmdPurchase()).execute(v, "Cocacola"));
		System.out.println(((Command) new CmdRejectCoins()).execute(v, ""));



	}

}
