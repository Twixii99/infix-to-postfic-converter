package Sheet4;

import java.util.Scanner;

public class MainOfPostfix {
	public static void main(String[] args) {
		postfix post = new postfix();
		postfixAotherAlgo algo = new postfixAotherAlgo();
		Scanner token = new Scanner(System.in);
		
		String expression = token.nextLine();
		token.close();	
		String ans = post.infixToPostfix(expression);
		if(ans == null)
			System.out.println("WRONG ARTHIMATIC EXPRESSION");
		else { 
			System.out.println(ans);
			System.out.println(post.evaluate(ans));
		}
	}
}
//(4 + 8) * (6 - 5) / ((3 - 2) * (2 + 2 ))
//(63 + (24 * 2) / 16) + ((55 - 2) / 12 * (33 / 3))

