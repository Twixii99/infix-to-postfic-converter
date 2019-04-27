package Sheet4;

/**
 * 
 * @author kamal
 * 
 * this stack is implemented using Singly Linked List with A Dummy Node
 * and a tail pointer so it maybe looked weired :)
 *
 */

public class MyStack implements IStack{
	Node head  = new Node(null);
	Node tail = head;
	int size = 0;
	
	@Override
	public Object pop() {
		// TODO Auto-generated method stub
		Object element  = tail.getData();
		if(!isEmpty()) { 
			Node pointer = head;
			while(pointer.next != tail)
				pointer = pointer.next;
			pointer.next = null;
			tail = pointer;
			size--;
			return element;
		}else {
			return null;
		}
	}

	@Override
	public Object peek() {
		// TODO Auto-generated method stub
		return tail.getData();
	}

	@Override
	public void push(Object element) {
		// TODO Auto-generated method stub
		Node newNode = new Node(element);
		tail.next = newNode;
		tail = newNode;
		size++;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(head.next == null)
			return true;
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
}
