
/** 
 * @author Yuen Chun Hin Luigi
 * The subclass Square, which inherits the superclass Rectangle
 */
public class Square extends Rectangle {
	
	/** constructor for the Square. It initializes and creates the square.
	 * @param size the size of square taken from input
	 */
	public Square(int size) {
		super(size,size);
		Rectangle squ = new Rectangle(size, size);
	}

}
