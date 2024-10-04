import java.io.*;
import java.util.*;

/** PostfixReader will be reading input postfix and output an infix and calculate the final result
 * @author Yuen Chun Hin Luigi
 * 
 */
public class PostfixReader {

	public static void main(String[] args) {
		PostfixReader myAnswer = new PostfixReader();
		myAnswer.doConversion();
	}


	/**
	 * Reads postfix from readPostfix(), puts it into a stack and converts to infix
	 */
	public void doConversion() {
		// TODO: read Postfix from input using readPostfix(), then convert it to infix and
		// print it out
		String[] Line = readPostfix();
		Stack stax = new Stack();
		int numcount = 0;
		int opcount = 0;
		for (int i=0;i<Line.length;i++) {
			
			if (Line[i].contentEquals("*") || Line[i].contentEquals("+") || Line[i].contentEquals("-") || Line[i].contentEquals("^") || Line[i].contentEquals("/")) {
				opcount++;
				if (stax.size() > 1) {
					String a = (String) stax.pop();
					String b = (String) stax.pop();
					String c = "( " + b + " " + Line[i] + " " + a + " )";
					
					stax.push(c);
				}
				else {
					numcount = opcount + 12;
					break;
				}
				
			}
			else {
				numcount++;
				stax.push(Line[i]);
			}
		}
		
		if (numcount - opcount == 1) {
			String last = (String)stax.pop();
			System.out.println("Infix: " + last);
			evalInfix(last);
		}
		else {
			System.out.println("Error: Invalid postfix");
		}
		
	}

	/**
	 * @param infix converts the infix into a stack, then calculates the result by identifying equations within brackets and then prints it.
	 */
	public void evalInfix(String infix) {
		// TODO: evaluate the infix representation of the input arithmetic expression, 
		// and then print the result of the evaluation of the expression on the next 
		// line.
		String[] list = infix.split(" ");
		Stack evstax = new Stack();
		for (int j = 0; j<list.length ;j++) {
			;
			if ( !list[j].contentEquals(")")){
				
				evstax.push(list[j]);
			}
			else {
				String q = (String) evstax.pop();
				String w = (String) evstax.pop();
				String e = (String) evstax.pop();
				String r = (String) evstax.pop();
				
				int x = Integer.parseInt(e);
				int y = Integer.parseInt(q);
				int t = 0;
				String p = "";
				
				if (w.contentEquals("*")) {
					t = x * y;
					
					p = String.valueOf(t);
					evstax.push(p);
				}
				if (w.contentEquals("+")) {
					t = x + y;
					
					p = String.valueOf(t);
					evstax.push(p);
				}
				if (w.contentEquals("-")) {
					t = x - y;
					
					p = String.valueOf(t);
					evstax.push(p);
				}
				if (w.contentEquals("/")) {
					t = x / y;
					
					p = String.valueOf(t);
					evstax.push(p);
				}
				if (w.contentEquals("^")) {
					t = (int) Math.pow(x,y);
					
					p = String.valueOf(t);
					evstax.push(p);
				}
			}
			
		}
		String ans = (String)evstax.pop();
		System.out.println("Result = "+ans);
		
	}

	/**
	 * @return an array of strings representing the postfix equation
	 */
	public String[] readPostfix() {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String inputLine;
		try {
			System.out.print("Input Postfix: ");
			inputLine = input.readLine();
			return inputLine.split(" ");
		} catch (IOException e) {
			System.err.println("Input ERROR.");
		}

		// return empty array if error occurs
		return new String[] {};
	}

}

class Stack {
    private ArrayList<String> contents;
    
    public Stack() {
        this.contents = new ArrayList<String>();
    }
    
    public void push(String content) {
        this.contents.add(content);
    }
    
    public String pop() {
        String content = this.contents.get(this.contents.size());
        this.contents.remove(this.contents.size());
        return content;
    }
    
    public int size() {
    	return this.contents.size();
    }
}
