/**
 * @author Yuen Chun Hin Luigi
 *	The Shape class, containing all methods of every shape
 */
public class Shape {
	String[][] body;
	int w;
	int h;
	int area;
	
	/**
	 * A default constructor, initializes an empty shape
	 */
	public Shape() {
		
	}
	/**
	 * @return the area of the shape
	 */
	public int getArea() {
		return area;
	}
	/**	converts the 2d array into a string
	 * @return the string that is converted from the 2d array that store the shape
	 */ 
	public String toString() {
		String str = "";
		for (int i=0;i<h;i++) {
			
			for (int j=0;j<w;j++){
				str = str + this.body[i][j];
			}
			if (i != h-1) {
				str = str + "\n";
			}
		}
		return str;
	}
	/**
	 * @param s the shape to be compared with
	 * @return the intersecting part between the 2 shapes
	 */
	public Shape intersect(Shape s) {
		Shape inter = new Shape();
		inter.h = s.h<this.h?s.h:this.h;
		inter.w = s.w<this.w?s.w:this.w;
		inter.body = new String[inter.h][inter.w];
		inter.area = 0;
		for (int i=0;i<inter.h;i++) {
			for (int j=0;j<inter.w;j++) {
				if (this.body[i][j].contentEquals("*") && s.body[i][j].contentEquals("*")) {
					inter.body[i][j] = "*";
					inter.area++;
				}
				else {
					inter.body[i][j] = " ";
				}
			}
		}
		return inter;
	}
	
	/**
	 * @param s the shape to be compared
	 * @return the total union shape of the 2 compared shapes
	 */
	public Shape union(Shape s) {
		
		Shape uni = new Shape();
		uni.h = s.h>this.h?s.h:this.h;
		uni.w = s.w>this.w?s.w:this.w;
		uni.area = 0;
		uni.body = new String[uni.h][uni.w];
		for (int i=0;i<uni.h;i++) {
			for (int j=0;j<uni.w;j++) {
				
				if (i >= s.h && i < this.h) {
					
					if (j<this.w && j<s.w) {
						uni.body[i][j] = this.body[i][j];
						if (uni.body[i][j].contentEquals("*")) {
							uni.area++;
						}
					}
					else if (j>=this.w && j<s.w) {
						uni.body[i][j] = " ";
					}
					else if (j >= s.w && j < this.w) {
						uni.body[i][j] = this.body[i][j];
						if (uni.body[i][j].contentEquals("*")) {
							uni.area++;
						}
					}
				}
				else if (i >= this.h && i < s.h) {
					if (j<this.w && j<s.w) {
						
						uni.body[i][j] = s.body[i][j];
						if (uni.body[i][j].contentEquals("*")) {
							uni.area++;
						}
					}
					else if (j>=s.w && j<this.w) {
						uni.body[i][j] = " ";
					}
					else if (j >= this.w && j < s.w) {
						uni.body[i][j] = s.body[i][j];
						if (uni.body[i][j].contentEquals("*")) {
							uni.area++;
						}
					}
				}
				else if (i<this.h && i<s.h) {
					if (j<this.w && j<s.w) {
						
						if (this.body[i][j].contentEquals("*") || s.body[i][j].contentEquals("*")) {
							uni.body[i][j] = "*";
							uni.area++;
						}
						else {
							uni.body[i][j] = " ";
						}
					}
					if (j>=this.w && j<s.w) {
						uni.body[i][j] = s.body[i][j];
						if (uni.body[i][j].contentEquals("*")) {
							uni.area++;
						}
					}
					if (j>=s.w && j<this.w) {
						uni.body[i][j] = this.body[i][j];
						if (uni.body[i][j].contentEquals("*")) {
							uni.area++;
						}
					}
				}
				
			}
			
		}
		return uni;
	}
	
}
