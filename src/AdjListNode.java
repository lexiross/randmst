
public class AdjListNode {
	
	private int w;
	private float weight;
	private AdjListNode next;
	
	public AdjListNode(int w, float weight) {
		this.w = w;
		this.weight = weight;
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
