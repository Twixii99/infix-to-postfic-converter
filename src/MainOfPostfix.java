package Sheet4;

import java.util.Scanner;

public class MainOfPostfix {
	public static void main(String[] args) {
		postfix post = new postfix();
		Scanner token = new Scanner(System.in);
		
		String expression = token.nextLine();
		token.close();	
		String ans = post.infixToPostfix(expression);
		if(ans == null)
			System.out.println("WRONG ARTHIMATIC EXPRESSION");
		else { 
			System.out.println(ans);
			int x = post.evaluate(ans);
			if(x != Integer.MAX_VALUE)
				System.out.println(x);
		}
	}
}

