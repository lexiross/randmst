import java.util.ArrayList;


public class DaryHeap implements Heap {

	private int size;
	private ArrayList<Float> contents;
	private int d;
	
	public DaryHeap(int d) {
		this.size = 0;
		this.contents = new ArrayList<Float>();
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
	public void insert(float value) {
		if (this.isEmpty()) {
			this.contents.set(0, value);
		}
		else {
			int position = this.size + 1;
			float parent = contents.get(getParent(position));
			while (position > 0 && value < parent) {
				contents.set(position, parent);
				parent = contents.get(getParent(position));
				position = getParent(position);
			}
			this.contents.set(position, value);
		}
	}

	@Override
	public float deleteMin() {
		// TODO Auto-generated method stub
		return 0;
	}

}
