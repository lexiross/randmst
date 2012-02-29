import java.util.ArrayList;

public class MinHeap implements Heap {
    private ArrayList<AdjListNode> Heap;
    //private int maxsize;
    private int size;

    public MinHeap() {
		//maxsize = max;
		Heap = new ArrayList<AdjListNode>();
		Heap.add(new AdjListNode(0,0));
		size = 0 ;
		//Heap[0] = new AdjListNode(0,0);
    }

    private int leftchild(int pos) {
    	return 2*pos;
    }
    private int rightchild(int pos) {
    	return 2*pos + 1;
    }

    private int parent(int pos) {
    	return  pos / 2;
    }
    
    private boolean isleaf(int pos) {
    	return ((pos > size/2) && (pos <= size));
    }

    private void swap(int pos1, int pos2) {
		AdjListNode tmp;
	
		tmp = Heap.get(pos1);
		Heap.set(pos1, Heap.get(pos2));
		Heap.set(pos2, tmp);
    }

    public void insert(AdjListNode elem) {
    	size++;
		Heap.add(elem);
		int current = size;
		int parent = parent(current);
		
		while (Heap.get(current).compareTo(Heap.get(parent)) < 0) {
		    swap(current, parent);
		    current = parent;
		    parent = parent(current);
		}	
    }

    public AdjListNode deleteMin() {
		swap(1,size);
		size--;
		if (size != 0)
		    pushdown(1);
		if (Heap.get(rightchild(1)).getWeight() < Heap.get(1).getWeight() || Heap.get(leftchild(1)).getWeight() < Heap.get(1).getWeight())
			System.out.println("error in heap\n");
		return Heap.get(size+1);
    }

    private void pushdown(int position) {
		int smallestchild;
		while (!isleaf(position)) {
		    smallestchild = leftchild(position);
		    if ((smallestchild < size) && (Heap.get(smallestchild).compareTo(Heap.get(smallestchild+1))) > 0)
		    	smallestchild = smallestchild + 1;
		    if (Heap.get(position).compareTo(Heap.get(smallestchild)) <= 0) return;
		    swap(position,smallestchild);
		    position = smallestchild;
		}
    }

	@Override
	public boolean isEmpty() {
		return (size < 1);
	}

}