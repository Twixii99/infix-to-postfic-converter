package Sheet4;

import static java.lang.System.out;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		out.println("Enter the operation that tou whant: ");
		out.println("1: Push\n2: Pop\n3: Peek\n4: Get size\n5: Check if empty\n");
		
		Scanner take = new Scanner(System.in);
		MyStack st = new MyStack(); 
		
		for(;;) {
			out.print("Enter proper input or \"-1\" to exit: ");
			int operate = take.nextInt();
			switch(operate) {
				case(1):
					out.print("Enter your push item: ");
					int input =  take.nextInt();
					st.push(input);
					break;
				case(2):
					Object ans = st.pop();
					if(ans == null)
						out.println("EmptyStack");
					else
						out.println(ans);
					break;
				case(3):
					out.println(st.peek());
					break;
				case(4):
					out.println(st.size());
					break;
				case(5):
					out.println(st.isEmpty());
					break;
			}
			if(operate == -1)
				break;
		}
	}

}
