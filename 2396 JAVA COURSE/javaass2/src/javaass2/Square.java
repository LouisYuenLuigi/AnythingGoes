package javaass2;

public class Square extends Rectangle {
	public Square(int size) {
		super(size,size);
		Rectangle squ = new Rectangle(size, size);
	}

}
