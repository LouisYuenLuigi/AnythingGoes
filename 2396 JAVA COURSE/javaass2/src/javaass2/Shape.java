package javaass2;

public class Shape {
	String[][] body;
	int w;
	int h;
	int area;
	
	public Shape() {
		
	}
	public int getArea() {
		return area;
	}
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
	public Shape intersect(Shape s) {
		Shape inter = new Shape();
		inter.h = s.h<this.h?s.h:this.h;
		inter.w = s.w<this.w?s.w:this.w;
		inter.body = new String[inter.h][inter.w];
		for (int i=0;i<inter.h;i++) {
			for (int j=0;j<inter.w;j++) {
				if (this.body[i][j].contentEquals("*") && s.body[i][j].contentEquals("*")) {
					inter.body[i][j] = "*";
				}
				else {
					inter.body[i][j] = " ";
				}
			}
		}
		return inter;
	}
	
	public Shape union(Shape s) {
		
		Shape uni = new Shape();
		uni.h = s.h>this.h?s.h:this.h;
		uni.w = s.w>this.w?s.w:this.w;
		
		uni.body = new String[uni.h][uni.w];
		for (int i=0;i<uni.h;i++) {
			for (int j=0;j<uni.w;j++) {
				
				if (i >= s.h && i < this.h) {
					
					if (j<this.w && j<s.w) {
						uni.body[i][j] = this.body[i][j];
					}
					else if (j>=this.w && j<s.w) {
						uni.body[i][j] = " ";
					}
					else if (j >= s.w && j < this.w) {
						uni.body[i][j] = this.body[i][j];
					}
				}
				else if (i >= this.h && i < s.h) {
					if (j<this.w && j<s.w) {
						
						uni.body[i][j] = s.body[i][j];
					}
					else if (j>=s.w && j<this.w) {
						uni.body[i][j] = " ";
					}
					else if (j >= this.w && j < s.w) {
						uni.body[i][j] = s.body[i][j];
					}
				}
				else if (i<this.h && i<s.h) {
					if (j<this.w && j<s.w) {
						
						if (this.body[i][j].contentEquals("*") || s.body[i][j].contentEquals("*")) {
							uni.body[i][j] = "*";
						}
						else {
							uni.body[i][j] = " ";
						}
					}
					if (j>=this.w && j<s.w) {
						uni.body[i][j] = s.body[i][j];
					}
					if (j>=s.w && j<this.w) {
						uni.body[i][j] = this.body[i][j];
					}
				}
				
			}
			
		}
		return uni;
	}
	
}
