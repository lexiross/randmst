
/*
 * Encapsulates the current node's index as well as its
 * preceding edge weight.
 */
public class AdjListNode implements Comparable<AdjListNode> {
	
	private int w;
	private double weight;

	
	public AdjListNode(int w, double edgeWeight) {
		this.w = w;
		this.weight = edgeWeight;
	}

	/*
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(AdjListNode other) {
		if (this.getWeight() < other.getWeight()) {
			return -1;
		} else if (this.weight > other.weight) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public int getVertex() { return w; }
	
	public double getWeight() { return this.weight; }
	
	public String toString() { 
		return (w + ": " + weight);
	}
	
}
