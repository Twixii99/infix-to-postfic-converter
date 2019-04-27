package Sheet4;

public class Node {
	private Object data;
	public Node next = null;
	
	public Node(Object data) {
		this.data = data;
	}
	
	public Object getData() {
		return data;
	}
}
