package Sheet4;

import java.util.Stack;
import java.util.Vector;
import java.util.Arrays;

public class postfixAotherAlgo implements IExpressionEvaluator{
MyStack st = new MyStack();
	
	public final String use = "+-?*/?^? ?(";

	@Override
	public String infixToPostfix(String expression) {
		if(!count(expression)) {
			System.out.println("Mismatched parenthesis.");
			return null;
		}
		String ans = "";
		for(char ch: expression.toCharArray()) {
			if(Character.isDigit(ch) | Character.isLetter(ch))
				ans += ch;
			else {
				if(st.isEmpty()) 
					st.push(ch);
				else {
					// when ch == ')' it won't get in "if" cause of the MHS with be -ve  -->>
					if(use.indexOf(ch) - use.indexOf((Character)st.peek()) > 1 | (Character)st.peek() == '(') 
						st.push(ch);
					else {
						if(ch == ')') {
							while(st.peek() != (Character)'(') 
								ans += st.pop();
							st.pop();
						}
						else {
							while(!st.isEmpty() && use.indexOf(ch) - use.indexOf((Character)st.peek()) <= 1 && (Character)st.peek() != '(')
								ans += st.pop();
							st.push(ch);
						}
					}
				}
			}
		}
		while(!st.isEmpty())
			ans += st.pop();
		// return all multiple sopaces to only one space
		return change(ans.replaceAll("( )+", " "));
	}

	@Override
	public int evaluate(String expression) {
		Vector<String> vec = new Vector<String>(Arrays.asList(expression.split("\\s")));
		final String notations = "+-/*";
		for(int i = 0; i < vec.size() && vec.size() > 1; i++) {
			if(notations.contains(vec.get(i))) {
				String you = sum(vec.get(i-2), vec.get(i-1), vec.get(i));
				vec.remove(i); 
				vec.remove(i-1); 
				vec.remove(i-2);
				vec.add(i-2, you);
				i = 0;
			}
		}
		return (int)Double.parseDouble(vec.get(0));
	}
	
	public String sum(String a, String b, String c) {
		switch(c) {
		case "+":
			return String.valueOf(Double.parseDouble(a) + Double.parseDouble(b));
		case "-":
			return String.valueOf(Double.parseDouble(a) - Double.parseDouble(b));
		case "*":
			return String.valueOf(Double.parseDouble(a) * Double.parseDouble(b));
		case "/":
			return String.valueOf(Double.parseDouble(a) / Double.parseDouble(b));
		}
		return null;
	}
	
	public static boolean count(String a) {
		final String open = "({[";
		final String close = ")}]";
		Stack<Character> buffer = new Stack<Character>();
		for(char ch: a.toCharArray()) {
			if(open.indexOf(ch) != -1)
				buffer.push(ch);
			else if(close.indexOf(ch) != -1) {
				if(buffer.isEmpty())
					return false;
				else if(close.indexOf(ch) != open.indexOf(buffer.pop()))
					return false;
			}
		}
		return buffer.isEmpty();	
	}

	public static String change(String args) {
		String ans = "";
		final String nodes = "+-^/*";
		for(int i = 0; i < args.length()-1; i++) {
			if(Character.isLetter(args.charAt(i)) && nodes.indexOf(args.charAt(i+1)) != -1) {
				ans += args.charAt(i);
				ans += ' ';
			}
			else if(Character.isLetter(args.charAt(i+1)) && nodes.indexOf(args.charAt(i)) != -1){
				ans += args.charAt(i);
				ans += ' ';
			}
			else if(Character.isDigit(args.charAt(i)) && nodes.indexOf(args.charAt(i+1)) != -1) {
				ans += args.charAt(i);
				ans += ' ';
			}
			else if(Character.isDigit(args.charAt(i+1)) && nodes.indexOf(args.charAt(i)) != -1){
				ans += args.charAt(i);
				ans += ' ';
			}
			else if(nodes.indexOf(args.charAt(i+1)) != -1 && nodes.indexOf(args.charAt(i)) != -1){
				ans += args.charAt(i);
				ans += ' ';
			}
			else
				ans += args.charAt(i);	
		}
		ans += args.charAt(args.length()-1);
		return ans;
	}
}
