public class staticBinding {
	
	//the superclass
	public static class big {
		//prints out big as a subclass
		static void print() 
		{ 
			System.out.println("big"); 
		} 
	} 
	//the subclass
	public static class small extends big {
		//prints out small as a subclass
		static void print() 
		{ 
			System.out.println("small"); 
		} 
	} 

	public static void main(String[] args) 
	{ 
		//an object of superclass and an object of subclass was created
		//Since the print method of superclass is static,
		//the print method of the superclass will not be overridden in subclasses
		//so the compiler knows during compile time which print method to call
		
		big A = new big(); 
		big B = new small(); 
		A.print(); 
		B.print(); 
	} 
}
