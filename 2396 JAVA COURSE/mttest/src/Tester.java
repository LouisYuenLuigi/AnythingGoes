import java.util.ArrayList;

public class Tester {
	private ArrayList<Fruit> fruits;
	
	public Tester() {
		fruits = new ArrayList<Fruit>();
	}

	public static void main(String[] args) {
		
		Tester t = new Tester();
		t.add(new Fruit());
		Fruit.addFruit();
		t.add(new Berry(3.7));
		Fruit.addFruit();
		t.add(new Vitis(5.2));
		Fruit.addFruit();
		t.add(new Fragaria());
		Fruit.addFruit();
		t.start();
		
		System.out.println(Fruit.getCount()+" fruits are juiced");

	}
	public void add(Fruit fru) {
		this.fruits.add(fru);
	}

	public void start() {
		for (int i=0;i<fruits.size();i++){
			fruits.get(i).prepare();
		}

	}

}
