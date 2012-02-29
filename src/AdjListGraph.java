import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Stack;
import java.util.Iterator;

public class AdjListGraph implements Graph {
	private ArrayList<AdjListNode>[] vertices;
	private int numVertices;
	private int dimension;
	
	public AdjListGraph() {
		this.numVertices = 8;
		this.dimension = 0;
		this.vertices = new ArrayList[8];
		
		for (int i = 0; i < 7; i++) {
			AdjListNode edge = new AdjListNode(i+1,i+1);
			vertices[i] = new ArrayList<AdjListNode>();
			vertices[i].add(edge);
		}
		
		AdjListNode edge = new AdjListNode(0,8);
		vertices[7] = new ArrayList<AdjListNode>();
		vertices[7].add(edge);
	}

	public AdjListGraph(int d, int n) {
		this.numVertices = n;
		this.dimension = d;
		this.vertices = new ArrayList[n];
		
		int edgeCount = 0;
		
		double N = k(n,d);
				
		Random rand = new Random(System.nanoTime());
		
		// SQUARING!!
		switch (d) {
			case 0: 
				for (int i = 0; i < n; i++) {
					for (int j = i + 1; j < n; j++) {
						double edgeWeight = rand.nextDouble();
								
						if (edgeWeight < N) {
							//System.out.println("(" + i + "," + j + ") : " + edgeWeight + "\n");
							
							AdjListNode iNode = new AdjListNode(i,edgeWeight);
							if (vertices[j] == null) 
								vertices[j] = new ArrayList<AdjListNode>();						
							vertices[j].add(iNode);
							
							AdjListNode jNode = new AdjListNode(j,edgeWeight);
							if (vertices[i] == null) 
								vertices[i] = new ArrayList<AdjListNode>();	
							vertices[i].add(jNode);
						}
					}
				}
				break;
			case 2:
			case 3:
			case 4:
				double[][] points = new double[n][d];
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < d; j++) {
						points[i][j] = rand.nextDouble();
					}
				}
								
				for (int i = 0; i < n; i++) {
					for (int j = i + 1; j < n; j++) {
						
						double testWeight = 0;
						
						for (int k = 0; k < d; k++) {
							if (Math.abs(points[i][k] - points[j][k]) > testWeight)
								testWeight = Math.abs(points[i][k] - points[j][k]);
						}						

						//System.out.println("t (" + i + "," + j + ") : " + testWeight + "\n");
										
						if (testWeight <= N) {
							edgeCount += 1;
							double edgeWeight = dist2(points[i],points[j],d);
							
							/*if (testing)
								System.out.println("e (" + i + "," + j + ") : " + edgeWeight + "\n");*/
							
							AdjListNode iNode = new AdjListNode(i,edgeWeight);
							if (vertices[j] == null) 
								vertices[j] = new ArrayList<AdjListNode>();						
							vertices[j].add(iNode);
							
							AdjListNode jNode = new AdjListNode(j,edgeWeight);
							if (vertices[i] == null) 
								vertices[i] = new ArrayList<AdjListNode>();	
							vertices[i].add(jNode);
						}
					}
				}
				//System.out.println("Edge count: " + edgeCount);

				break;
			default: break;
		}
	}
	
	static double dist2(double[] x, double[] y, int d) {
		double sumSquares = 0;
		for (int i = 0; i < d; i++) {
			sumSquares += Math.pow((x[i] - y[i]), 2);
		}
		
		return sumSquares;
	}
	
	static double k(int n, int d) {
		switch(d) {
			case 0:
				if (n < 500)
					return 0.5;
				if (n < 2000)
					return 0.05;
				else if (n < 5000)
					return 0.015;
				else if (n < 10000)
					return 0.002;
				else
					return 0.001;
			case 2:
				if (n < 500)
					return 0.5;
				else if (n < 2000)
					return 0.25;
				else if (n < 5000)
					return 0.05;
				else if (n < 10000)
					return 0.028;
				else
					return 0.025;
			case 3:
				if (n < 500)
					return 1;
				else if (n < 2000)
					return 0.5;
				else if (n < 5000)
					return 0.15;
				else if (n < 10000)
					return 0.1;
				else
					return 0.08;
			case 4:
				if (n < 500)
					return 1;
				else if (n < 2000)
					return 0.5;
				else if (n < 5000)
					return 0.25;
				else if (n < 10000)
					return 0.2;
				else
					return 0.12;
			default:
				return 1;
		}
	}
	
	public ArrayList<AdjListNode> getNeighbors(int v) {
		return vertices[v];
	}

	public double priorityPrim() {
		
		double[] dist = new double[numVertices];
		int[] prev = new int[numVertices];
		boolean[] set = new boolean[numVertices];
		
		for (int i = 0; i < numVertices; i++) {
			dist[i] = Double.POSITIVE_INFINITY;
			prev[i] = i;
			set[i] = false;
		}
		
		PriorityQueue<AdjListNode> Q = new PriorityQueue<AdjListNode>();
		Q.add(new AdjListNode(0,0));
		
		dist[0] = 0;
		
		while (!Q.isEmpty()) {
			AdjListNode currVertex = Q.poll();
			int v = currVertex.getVertex();
			set[v] = true;
			
			
			ArrayList<AdjListNode> neighbors = this.getNeighbors(v);
			
			Iterator<AdjListNode> iterator = neighbors.iterator();
			 
			while(iterator.hasNext()) {
				AdjListNode node = iterator.next();
				int w = node.getVertex();
				if (!(set[w])) {
					if (dist[w] > node.getWeight()) {
						dist[w] = node.getWeight();
						prev[w] = v;
						Q.add(new AdjListNode(w,dist[w]));
					}
				}
			}
		}
		
		//for (int i = 0; i < numVertices; i++) {
			//System.out.println(i + ": " + "dist=" + dist[i] + " prev=" + prev[i] + "\n");
		//}
		
		double finalWeight = 0.0;
		double maxEdge = 0.0;
		for (double w : dist) {
			if (dimension != 0)
				w = Math.sqrt(w);
			finalWeight += w;
			if (w > maxEdge)
				maxEdge = w;
		}
		
		System.out.println("Dimension: " + this.dimension + 
		", n: " + this.numVertices + 
		", maxEdge: " + maxEdge + ", treeWeight: " +
		finalWeight);
		
		return finalWeight;
	}

	public double prim() {
		
		double[] dist = new double[numVertices];
		int[] prev = new int[numVertices];
		boolean[] set = new boolean[numVertices];
		
		for (int i = 0; i < numVertices; i++) {
			dist[i] = Double.POSITIVE_INFINITY;
			prev[i] = i;
			set[i] = false;
		}
		
		Heap H = new MinHeap();
		H.insert(new AdjListNode(0,0));
				
		dist[0] = 0;
		
		while (!H.isEmpty()) {
			AdjListNode currVertex = H.deleteMin();
			int v = currVertex.getVertex();
			
			if (set[v])
				continue;

			//System.out.println(v + " " + dist[v]);
			
			set[v] = true;
			
			ArrayList<AdjListNode> neighbors = this.getNeighbors(v);
			
			Iterator<AdjListNode> iterator = neighbors.iterator();
			
			while(iterator.hasNext()) {
				AdjListNode node = iterator.next();
				int w = node.getVertex();
				//System.out.println("w " + w);
				if (!(set[w])) {
					if (dist[w] > node.getWeight()) {
						dist[w] = node.getWeight();
						prev[w] = v;
						H.insert(new AdjListNode(w,dist[w]));
					}
				}
			}
		}
		
		/*for (int i = 0; i < numVertices; i++) {
			System.out.println(i + ": " + "dist=" + dist[i] + " prev=" + prev[i] + "\n");
		}*/
		
		double finalWeight = 0.0;
		double maxEdge = 0.0;
		for (double w : dist) {
			if (dimension != 0)
				w = Math.sqrt(w);
			finalWeight += w;
			if (w > maxEdge)
				maxEdge = w;
		}
		
		System.out.println("Dimension: " + this.dimension + 
		", n: " + this.numVertices + 
		", maxEdge: " + maxEdge + ", treeWeight: " +
		finalWeight);
		
		return finalWeight;

	}

	public boolean isGraph() {
		Stack<AdjListNode> stack = new Stack<AdjListNode>();
		AdjListNode root = new AdjListNode(0, 0.0);
		stack.push(root);
		int count = 0;
		boolean[] explored = new boolean[this.numVertices];
		while (!stack.empty()) {
			AdjListNode v = stack.pop();
			int vNum = v.getVertex();
			count++;
			ArrayList<AdjListNode> neighbors = this.getNeighbors(vNum);
			for (AdjListNode w : neighbors) {
				int wNum = w.getVertex();
				if (!explored[wNum]) {
					explored[wNum] = true;
					stack.push(w);
				}
			}
		}
		return (count == this.numVertices);
	}
	
	public void print() {
		for (int i = 0; i < numVertices; i++) {
			System.out.println(i + "-> ");
			
			ArrayList<AdjListNode> neighbors = this.getNeighbors(i);
			
			if (neighbors == null)
				return;
			
			Iterator<AdjListNode> iterator = neighbors.iterator();
			 
			while(iterator.hasNext())
			    System.out.println(iterator.next().toString() + ", ");
			
			System.out.println("\n");
		}
	}
}
