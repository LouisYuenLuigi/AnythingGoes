import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class main {
	


	public static void main(String[] args) {
		
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String inputLine = "";
		while (!inputLine.contentEquals("0")) {
			try {
				inputLine = input.readLine();
				}
				catch (IOException e) {
				System.out.print("Input Error.");
				}
				System.out.println(inputLine);
		}
		
	}
		

}

