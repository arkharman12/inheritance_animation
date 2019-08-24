//AsciiCanvas.java

import java.awt.Font;
import java.io.*;
import javax.swing.*;

public class AsciiCanvas extends JTextArea {
	//create a new linked list
	private Node currentNode;
	private Node head;

	//constructor
	public AsciiCanvas() {
		head = new Node(); //creates a new linked list
		currentNode = head; //set the current node to the head 
		this.setLineWrap(true);
		this.setFont(new Font("monospaced", Font.PLAIN, 16)); //set the font and other properties
	} // end constructor

	public void prevFrame(){
		this.currentNode.setText(this.getText()); //save the frame when the prev button got clicked
		if (this.currentNode == this.head) { //doesn't happen anything, if we are at the beginning of the linked list
			return;
		}
		else { //else load the previous node
			this.currentNode = this.currentNode.getPrev();
		}
		this.setText(this.currentNode.getText()); //load the text of the previous frame
	} // end prevFrame

	public void nextFrame() {
		this.currentNode.setText(this.getText()); //save the frame when we click the next button
		if (this.currentNode.getNext() == null) { //if getNext equals null, we are at the end of the linked list
			this.currentNode.setNext(new Node()); //create a new node
			this.currentNode.getNext().setPrev(this.currentNode); //set the previous of the new node to the last node
			this.currentNode = this.currentNode.getNext(); //access the next node of the linked list
		}
		else {
			this.currentNode = this.currentNode.getNext(); //access the next node of the linked list
		}
		this.setText(this.currentNode.getText()); //update the text area with the current node's text
	} //end nextFrame

	public void save() {
		this.currentNode.setText(this.getText()); //save the frame when we click the save button
		//try to save the file
		try {
			FileOutputStream fileOut = new FileOutputStream("asciianim.ser"); //saved file name will be "asciianim.ser"
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(head);
			out.close(); //close the stream
			fileOut.close(); //close the stream
			System.out.println("Serialized data is saved in asciianim.ser");
		}
		catch(IOException e) {
			e.printStackTrace(); // this will help us catch the error, if there is one
		}
	} //end save

	public void load(){
		//try to load the saved file
		try {
			FileInputStream fileIn = new FileInputStream("asciianim.ser"); //load file "asciianim.ser"
			ObjectInputStream in = new ObjectInputStream(fileIn);
			head = (Node) in.readObject(); //read the file as a Node object and assign it to the head of the linked list
			in.close(); //close the stream
			fileIn.close(); //close the stream
		}
		catch(IOException i) {
			System.out.println("Error: Can't find the file 'asciianim.ser', please try again");
			i.printStackTrace(); //catch the error while loading the file
			return;
		}
		catch(ClassNotFoundException c) {
			c.printStackTrace(); //print the error if the Node class doesn't exist for some reason
			return;
		}
		this.setText(head.getText()); //set the text from the head node
		this.currentNode = head; //set the current node pointer to the head
	} //end load

	public void anim() {
		this.currentNode.setText(this.getText()); //save the frame when user clicks the animate button
		if (this.currentNode.getNext() == null) { //if we are at the end of the list, start back at the beginning
			this.currentNode = head; //set the currentNode pointer to be the head
			this.setText(this.currentNode.getText()); //load the currentNodes text into the TextArea
		}
		else {
			this.currentNode = this.currentNode.getNext(); //else move the currentNode pointer to the next frame
			this.setText(this.currentNode.getText()); //update the text area with the new frame's text
		}
	} //end anim

} //end class AsciiCanvas
