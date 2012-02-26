public class MinHeap implements Heap {
    private AdjListNode[] Heap;
    private int maxsize;
    private int size;

    public MinHeap(int max) {
	maxsize = max;
	Heap = new AdjListNode[maxsize];
	size = 0 ;
	Heap[0] = new AdjListNode(0,0);
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

	tmp = Heap[pos1];
	Heap[pos1] = Heap[pos2];
	Heap[pos2] = tmp;
    }

    public void insert(AdjListNode elem) {
	size++;
	Heap[size] = elem;
	int current = size;
	
	while (Heap[current].compareTo(Heap[parent(current)]) < 0) {
	    swap(current, parent(current));
	    current = parent(current);
	}	
    }

    public void print() {
	int i;
	for (i=1; i<=size;i++)
	    System.out.print(Heap[i] + " ");
	System.out.println();
    }

    public AdjListNode deleteMin() {
	swap(1,size);
	size--;
	if (size != 0)
	    pushdown(1);
	return Heap[size+1];
    }

    private void pushdown(int position) {
	int smallestchild;
	while (!isleaf(position)) {
	    smallestchild = leftchild(position);
	    if ((smallestchild < size) && (Heap[smallestchild].compareTo(Heap[smallestchild+1])) > 0)
		smallestchild = smallestchild + 1;
	    if (Heap[position].compareTo(Heap[smallestchild]) <= 0) return;
	    swap(position,smallestchild);
	    position = smallestchild;
	}
    }

	@Override
	public boolean isEmpty() {
		return (size < 1);
	}

}