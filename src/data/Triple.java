package data;

/**
 * Contains a Node at position 0 and an integer at position 1
 * @author DoubLL
 */
public class Triple {

	private Node _node;
	private int _number;
	private double _percent;
	
	public double getPercent() {
		return _percent;
	}

	void setPercent(double percentage) {
		this._percent = percentage;
	}

	Triple(Node node) {
		this._node = node;
		this._number = 1;
		this._percent = 0;
	}
	
	public Node getNode() {
		return _node;
	}
	void setNode(Node node) {
		this._node = node;
	}
	public int getNumber() {
		return _number;
	}
	void incNumber() {
		_number++;
	}
	void setNumber(int number) {
		this._number = number;
	}
	
	public String toString() {
		return "-> " + this._node.getWord() + ", " + this._number + " times. (" + String.valueOf(this._percent*100) + "%)";
	}
}
