/**
 * @author Yuen Chun Hin Luigi
 *	The subclass Rectangle which inherits the superclass Shape
 */
public class Rectangle extends Shape {
	
	/**	Constructor of the rectangle, it initializes and creates the rectangle
	 * @param width width of the rectangle, taken from input
	 * @param height height of the rectangle, taken from input
	 */
	public Rectangle(int width, int height) {
		Shape rect = new Shape();
		this.body = new String[height][width];
		this.area = width * height;
		this.w = width;
		this.h = height;
		for (int i=0;i<height;i++) {
			for (int j=0;j<width;j++) {
				
				this.body[i][j] = "*";
			}
		}
	}

}
