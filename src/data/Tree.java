package data;

import java.util.LinkedList;

/**
 * Tree containing an entry point Node, and a few operations needed to create the full tree and finalize it.
 * @author DoubLL
 */
public class Tree {

	private Node _first;
	
	public Tree() {}
	
	public Tree(String word) {
		this._first = new Node(word);
	}
	
	public Node getEntry() {
		return _first;
	}
	
	/**
	 * Recursively calculates the percentages for word-links in every node starting with the entry point.
	 * @param node Entry point.
	 */
	public void calculatePercentage(Node node) {
		int sum = 0;
		for (Triple t:node.getLinks()) {
			sum += t.getNumber();
		}
		for (Triple t:node.getLinks()) {
			t.setPercent((double)(t.getNumber())/sum);
		}
		if (node.getLeftChild() != null) calculatePercentage(node.getLeftChild());
		if (node.getRightChild() != null) calculatePercentage(node.getRightChild());
	}
	
	/**
	 * Searches for a Node in the Tree. If the Node can not be found, returns null.
	 * @param word The word that is used for the search.
	 * @return The Node containing the searched word, or null.
	 */
	public Node search(String word) {
		if (_first == null) return null; //return null if Tree is empty
		Node tmp = _first;
		while (tmp != null && !(tmp.getWord().equals(word))) {
			if (word.compareTo(tmp.getWord()) < 0) //go left if the Node's word is
				tmp = tmp.getLeftChild(); //lexicographically higher than the search term.
			else
				tmp = tmp.getRightChild(); //otherwise go right.
		}
		return tmp; //return tmp, this will be null if word does not exist.
	}

	/**
	 * Insert a word into the tree. Does nothing if the word already exists in the tree.
	 * @param word The word.
	 * @return The created Node.
	 */
	public Node insertNode(String word) {
		if (_first == null) return _first = new Node(word);
		Node tmp = _first;
		while (!(tmp.getWord().equals(word))) {
			if (word.compareTo(tmp.getWord()) < 0) {
				if (tmp.getLeftChild() != null) {
					tmp = tmp.getLeftChild();
				} else {
					tmp.setLeftChild(new Node(word));
					tmp = tmp.getLeftChild();
				}
			}	
			else
				if (tmp.getRightChild() != null) {
					tmp = tmp.getRightChild();
				} else {
					tmp.setRightChild(new Node(word));
					tmp = tmp.getRightChild();
				}
		}
		return tmp;
	}
	
	/**
	 * Finds a Node in the Tree. If the Node does not exist, it will be created and returned.
	 * @param word The word that is used for the search.
	 * @return The Node containing the searched word.
	 */
	public Node find(String word) {
		Node tmp = search(word);
		return (tmp != null) ? tmp : insertNode(word); 
	}
	
	/**
	 * Given two words, will add or increment the link between these.
	 * @param word1 First word.
	 * @param word2 The word following the first.
	 */
	public void addLink(String word1, String word2) {
		Node word1Node = find(word1);
		addLink(word1Node, word2);
	}
	
	/**
	 * Given a Node and a word, will add or increment the link between these.
	 * @param word1 Node containing the first word.
	 * @param word2 The word following the first.
	 */
	public void addLink(Node word1, String word2) {
		for (Triple t:word1.getLinks())
			if (t.getNode().getWord().equals(word2)) {
				t.incNumber();
				return;
			}
		Node word2Node = find(word2);
		word1.getLinks().add(new Triple(word2Node));
	}
	
	public String toString() {
		return this._first.toString();
	}

	public Node getRandomEntry() {
		int random = (int) (Math.random() * this.getSize());
		LinkedList<Node> queue = new LinkedList<Node>();
		queue.add(_first);
		Node tmp = _first;
		for (int i = 0; i < random; i++) {
			tmp = queue.pop();
			if (tmp.getLeftChild() != null) queue.push(tmp.getLeftChild());
			if (tmp.getRightChild() != null) queue.push(tmp.getRightChild());
		}
		return tmp;
	}
	
	private int getSize() {
		return this.getEntry().getSize();
	}
	
}
