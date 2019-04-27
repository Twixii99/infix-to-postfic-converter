package Sheet4;

import java.util.Stack;
/**
 * 
 * @author kamal
 * @version Thursday, 24/4/2019 12:00 AM
 * 
*/
public class postfix implements IExpressionEvaluator{
	MyStack st = new MyStack();
	
	public final String use = "+-?*/?^? ?(";
	
	/**
	 * @param the original Mathematical Expression in infix 
	 * @return the Expression in postfix 
	 */
	@Override
	public String infixToPostfix(String expression) {
		expression = standard(expression);
		if(!count(expression) | !check3(expression)) {
			System.out.println("Wrong expression or Mismatching");
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

	/**
	 * @param expression: postfix Expression
	 * @return the value of the Expression in Integer
	 */
	@Override
	public int evaluate(String expression) {
		if(!check2(expression)) {
			System.out.println("HAS A LETTER.");
			return Integer.MAX_VALUE;
		}
		final String notations = "+-/*^";
		String you;
		String ans[] = expression.split("\\s");
		MyStack finalAns = new MyStack();
		for(String str: ans) {
			if(!notations.contains(str))
				finalAns.push(str);
			else {
				you = sum((String)finalAns.pop(), (String)finalAns.pop(), str);	
				finalAns.push(you);
			}
		}
		return (int)Double.parseDouble((String)finalAns.pop());
	}
	
	/**
	 * @param b the first Operand
	 * 
	 * @param a the second Operand
	 * 
	 * @param c the notation of the operator
	 * 
	 * @return the value of the arthmitic Operation
	 */
	public String sum(String b, String a, String c) {
		switch(c) {
			case "+":
				return String.valueOf(Double.parseDouble(a) + Double.parseDouble(b));
			case "-":
				return String.valueOf(Double.parseDouble(a) - Double.parseDouble(b));
			case "*":
				return String.valueOf(Double.parseDouble(a) * Double.parseDouble(b));
			case "/":
				return String.valueOf(Double.parseDouble(a) / Double.parseDouble(b));
			case "^":
				return String.valueOf(Math.pow(Double.parseDouble(a), Double.parseDouble(b)));
		}
		return null;
	}
	
	/**
	 * Checking for the mismatching of parentheses
	 * 
	 * @param a -->> the infix Expression
	 * @return true if it's matched and false O.W
	 */
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

	/**
	 * make the postfix Expression in the the Standard form 
	 * with only one Space between components
	 * 
	 * @param args postfix Expression
	 * @return the Standard postfix Expression
	 */
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
	/**
	 * the if the postfix expression has a Letter
	 * @param a postfix expression
	 * @return true if not has a letter
	 */
	public boolean check2(String a) {
		for(char ch: a.toCharArray()) {
			if(Character.isLetter(ch))
				return false;
		}
		return true;
	}

	/**
	 *@param infix expression
	 *@return true iff direct exp 
	 *
	 */
	public boolean check3(String a) {
		String note = "+-*/^";
		if(note.indexOf(a.charAt(0)) != -1 | note.indexOf(a.charAt(a.length()-1)) != -1)
			return false;
		for(int i = 0; i < a.length()-1; i++) {
			if(note.indexOf(a.charAt(i)) != -1 && note.indexOf(a.charAt(i+2)) != -1)
				return false;
		}
		return true;
	}
	/**
	 * @param a infix exp
	 * @return standard edition
	 */
	public static String standard(String a) {
		String stand = "", note = "+-*/^";
		for(int i = 0; i < a.length()-1; i++) {
			if(((Character.isLetter(a.charAt(i))||Character.isDigit(a.charAt(i)))&&note.indexOf(a.charAt(i+1)) != -1)|
				(note.indexOf(a.charAt(i)) != -1 && (Character.isDigit(a.charAt(i+1)) || Character.isLetter(a.charAt(i+1))))
				|(note.indexOf(a.charAt(i)) != -1 && note.indexOf(a.charAt(i+1)) != -1))
			{	stand += a.charAt(i); stand += ' ';}
			else
			stand += a.charAt(i);		
		}
		return stand += a.charAt(a.length()-1);
	}
}
