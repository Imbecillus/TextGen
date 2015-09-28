package data;

import java.util.LinkedList;

/**
 * Class for the graph and search tree.
 * @author DoubLL
 */
public class Node {

	private Node _left;
	private Node _right;
	private String _word;
	private LinkedList<Triple> _links;
	
	Node(String word) {
		this._word = word;
		this._links = new LinkedList<Triple>();
	}
	
	public Node getRandomNext() {
		double r = Math.random();
		for (Triple t:this.getLinks()) {
			if (r <= t.getPercent()) return t.getNode();
			else r-=t.getPercent();
		}
		return null; //this will happen if it's the last word in the text and has no links.
		
	}
		
	public String getWord() {
		return _word;
	}
	void setWord(String word) {
		this._word = word;
	}
	public Node getLeftChild() {
		return _left;
	}
	void setLeftChild(Node leftChild) {
		this._left = leftChild;
	}
	public Node getRightChild() {
		return _right;
	}
	void setRightChild(Node rightChild) {
		this._right = rightChild;
	}
	public LinkedList<Triple> getLinks() {
		return _links;
	}
	
	public String toString() {
		String res = "Word: " + this.getWord() + ".";
		if (this.getLeftChild() != null) res += " Has left Child.";
		if (this.getRightChild() != null) res += " Has right Child.";
		res += " Contains " + this.getLinks().size() + " links to other Nodes";
		return res;
	}

	public int getSize() {
		int size = 1;
		if (this.getLeftChild() != null) size += this.getLeftChild().getSize();
		if (this.getRightChild() != null) size += this.getRightChild().getSize();
		return size;
	}
	
}
