
/**
 * @author Yuen Chun Hin Luigi
 *	The subclass Diamond, which inherits the superclass Shape
 */
public class Diamond extends Shape {
	
	/** Constructor of the diamond, it initializes and creates the diamond
	 * @param size the size of the diamond, taken from input
	 */
	public Diamond(int size) {
		this.body = new String[size*2-1][size*2-1];
		this.w = size*2-1;
		this.h = size*2-1;
		this.area = 0;
		for (int i=1;i<w;i = i+2) {
			area = area + i;
		}
		area = area * 2 + w;
		int space =  size-1;
		int star = 1;
		
		for (int i=0;i<size;i++) {
			
			for (int j=0;j<w;j++) {
				if (j<space) {
					this.body[i][j] = " ";
	
				}
				else if (j >=space && j<space+star) {
					this.body[i][j] = "*";

					
				}
				else if (j>=space + star) {
					this.body[i][j] = " ";

					
				}
			}

			star = star + 2;
			space--;
		}
		
		space = space + 2;
		star = star - 4;
		
		for (int i=0;i<size-1;i++) {
			for (int j=0;j<w;j++) {
				if (j<space) {
					this.body[i+size][j] = " ";

				}
				else if (j >=space && j<space+star) {
					this.body[i+size][j] = "*";

				}
				else if (j>=space + star) {
					this.body[i+size][j] = " ";

				}
			}

			star = star - 2;
			space++;
		}
		
	}

}
