//Node.java

import java.io.Serializable;

public class Node implements Serializable {

	private Node prev; //previous element
	private Node next; //next element
	private String text; //ascii frame text


	//previous linked list node getter
	public Node getPrev() {
		return prev;
	}

	//previous linked list node setter
	public void setPrev(Node prev) {
		this.prev = prev;
	}

	//next linked list node getter
	public Node getNext() {
		return next;
	}

	//next linked list node setter
	public void setNext(Node next) {
		this.next = next;
	}

	//text getter
	public String getText() {
		return text;
	}

	//text setter
	public void setText(String text) {
		this.text = text;
	}

} //end class Node
