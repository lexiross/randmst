
public class AdjListNode {
	
	private int w;
	private float weight;
	private AdjListNode next;
	
	public AdjListNode(int w, double edgeWeight) {
		this.w = w;
		this.weight = edgeWeight;
		this.next = null;
	}
	
	public AdjListNode() {
		this.w = null
		this.weight = null;
		this.next = null;
	}
	
	public void setNext(AdjListNode next) {
		this.next = next;
	}
	
	public AdjListNode getNext() {
		return this.next;
	}
}
