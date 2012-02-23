import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class AdjListGraph implements Graph {
	private ArrayList<AdjListNode>[] vertices;

	public AdjListGraph(int d, int n) {
		double N = 0.1;
		Random rand = new Random(System.nanoTime());
		
		
		switch (d) {
			case 0: 
				for (int i = 0; i < n; i++) {
					for (int j = i; j < n; j++) {
						double edgeWeight = rand.nextDouble();
						
						if (edgeWeight <= N) {
							AdjListNode iNode = new AdjListNode(i,edgeWeight);
							vertices[j].add(iNode);
							
							AdjListNode jNode = new AdjListNode(j,edgeWeight);
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
					for (int j = 0; j < d; j++)
						points[i][j] = rand.nextDouble();
				}
				for (int i = 0; i < n; i++) {
					for (int j = i; j < n; j++) {
						double edgeWeight = dist(points[i],points[j],d);
						
						if (edgeWeight <= N) {
							AdjListNode iNode = new AdjListNode(i,edgeWeight);
							vertices[j].add(iNode);
							
							AdjListNode jNode = new AdjListNode(j,edgeWeight);
							vertices[i].add(jNode);
						}
					}
				}
				break;
			default: break;
		}
	}
	
	static double dist(double[] x, double[] y, int d) {
		double sumSquares = 0;
		for (int i = 0; i < d; i++) {
			sumSquares += Math.pow((x[i] - y[i]), 2);
		}
		
		return Math.sqrt(sumSquares);
	}
	
	public ArrayList<AdjListNode> getNeighbors(int v) {
		return this.vertices[v];
	}

	@Override
	public double prim() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
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
}