
public class AdjListNode implements Comparable<AdjListNode> {
	
	private int w;
	private float weight;
	private AdjListNode next;
		
	public AdjListNode(int w, float weight) {
		this.w = w;
		this.weight = weight;
		this.next = null;
	}
	
	public AdjListNode() {
		this.w = 0;
		this.weight = 0;
		this.next = null;
	}
	
	public void setNext(AdjListNode next) {
		this.next = next;
	}
	
	public AdjListNode getNext() {
		return this.next;
	}

	public int compareTo(AdjListNode other) {
		if (this.weight < other.weight) {
			return -1;
		} else if (this.weight > other.weight) {
			return 1;
		} else {
			return 0;
		}
	}
}
