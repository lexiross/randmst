
public class AdjListNode implements Comparable<AdjListNode> {
	
	private int w;
	private double weight;

	
	public AdjListNode(int w, double edgeWeight) {
		this.w = w;
		this.weight = edgeWeight;
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
	
	public int getVertex() { return w; }
	
	public double getWeight() {return weight; }
}
