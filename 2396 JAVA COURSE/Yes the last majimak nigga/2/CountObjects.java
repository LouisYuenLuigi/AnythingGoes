import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountObjects {
	
	public void CountObjects() { }


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if ( args.length < 1 ) {
			System.err.println("Usage: java CountObjects <s>");
				return;
		}
		
		CountObjects cobj = new CountObjects();
		cobj.doCountObjects(args[0]);

	}
	
	public void doCountObjects(String filename) {
		
		int obj_count = 0;
		String checker = "\\s*\\w{1,20}\\s*\\w{1,20}\\s+\\=\\s+";
		String checker2 = "\\s+\\w{1,20}\\s+\\w{1,20}\\s+\\w{1,20}\\;";
		
		
		System.out.println("Esimtating number of objects creating in file " + filename);
		
		try {
	        File file = new File(filename);
	        Scanner scanner = new Scanner(file);
	        while(scanner.hasNextLine()) {
	        	String man = scanner.nextLine();
	        	//System.out.println(man);
	        	obj_count = obj_count + regexChecker(checker, man);
	        	obj_count = obj_count + regexChecker(checker2, man);
	        }
	        
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
		
		// estimating the number of newly created in the filename
		// store the count in obj_count
		
		System.out.println("Estimated number of object in file " + filename + " is "
				+ obj_count);
		
	}
	
	public int regexChecker(String regex, String checkthis) {
		Pattern checkRegex = Pattern.compile(regex);
		Matcher regexMatcher = checkRegex.matcher(checkthis);
		int count = 0;
		while(regexMatcher.find()) {
			count++;
		}
		//System.out.println(count);
		return count;
	}

}