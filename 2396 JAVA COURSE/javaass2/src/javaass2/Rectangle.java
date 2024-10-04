package javaass2;

public class Rectangle extends Shape {
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
