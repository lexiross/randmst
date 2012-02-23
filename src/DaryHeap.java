import java.util.ArrayList;


public class DaryHeap implements Heap {

	private int size;
	private ArrayList<AdjListNode> contents;
	private int d;
	
	public DaryHeap(int d) {
		this.size = 0;
		this.contents = new ArrayList<AdjListNode>();
		this.d = d;
	}
	
	/**
	 * Gets the index of the node's first child using array arithmetic
	 * 
	 * @param parent
	 * @return
	 */
	private int getFirstChildIndex(int parent) {
		return this.d*parent + 1;
	}
	
	/**
	 * Gets the index of the node's parent using array arithmetic
	 * 
	 * @param child
	 * @return
	 */
	private static int getParent(int child) {
		return (child - 1) / 2;
	}
	
	@Override
	public boolean isEmpty() {
		return (this.size == 0);
	}

	@Override
	/**
	 * Inserts a value into the heap.
	 */
	public void insert(AdjListNode value) {
		if (this.isEmpty()) {
			this.contents.set(0, value);
		}
		else {
			int position = this.size + 1;
			AdjListNode parent = contents.get(getParent(position));
			while (position > 0 && value.compareTo(parent) < 0) {
				contents.set(position, parent);
				parent = contents.get(getParent(position));
				position = getParent(position);
			}
			this.contents.set(position, value);
		}
	}

	@Override
	public AdjListNode deleteMin() {
		if (this.isEmpty())
			return null;
		AdjListNode min = contents.get(0);
			
		AdjListNode last = contents.get(this.size - 1);
		contents.set(0, last);
		// the node that will "trickle down" to the bottom
		// of the heap
		AdjListNode shiftingNode = last;
		int position = 0;
		int childIndex = getFirstChildIndex(position);
		
		while (childIndex <= this.size) {
			childIndex = getFirstChildIndex(position);
			int tempIndex = getFirstChildIndex(position);
			
			// iterate this node's children to
			// find its minimum child
			for (int i = 0; i < d; i++) {
				if (tempIndex >= this.size)
					break;
				tempIndex++;
				if (contents.get(tempIndex).compareTo(contents.get(childIndex)) < 0)
					childIndex = tempIndex;
			}
			
			// if shifting node is greater than the minimum child,
			// make the swap
			if (contents.get(childIndex).compareTo(shiftingNode) < 0)
				contents.set(position, contents.get(childIndex));
			else // we have already found the right place for the node
				break;
			
			position = childIndex;
		}
		
		// place shifting node into the correct position
		contents.set(position, shiftingNode);
		return min;
	}
	
	public void printHeap() {
		String str = "";
		for (AdjListNode node : this.contents) {
			str += node.getWeight() + "\t";
		}
		System.out.println(str);
	}

}
