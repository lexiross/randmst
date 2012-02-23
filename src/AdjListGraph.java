import java.util.Random;

public class AdjListGraph implements Graph {
	private AdjListNode[] vertices;

	public AdjListGraph(int d, int n) {
		double N = 0.1;
		Random rand = new Random(System.nanoTime());
		
		vertices = new AdjListNode[n];
		for (int i = 0; i < n; i++) {
			vertices[i] = new AdjListNode();
		}
		
		switch (d) {
			case 0: 
				for (int i = 0; i < n; i++) {
					for (int j = i; j < n; j++) {
						double edgeWeight = rand.nextDouble();
						
						if (edgeWeight <= N) {
							AdjListNode iNode = new AdjListNode(i,edgeWeight);
							iNode.setNext(vertices[j].getNext());
							vertices[j].setNext(iNode);
							
							AdjListNode jNode = new AdjListNode(j,edgeWeight);
							jNode.setNext(vertices[i].getNext());
							vertices[i].setNext(jNode);
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
							iNode.setNext(vertices[j].getNext());
							vertices[j].setNext(iNode);
							
							AdjListNode jNode = new AdjListNode(j,edgeWeight);
							jNode.setNext(vertices[i].getNext());
							vertices[i].setNext(jNode);
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

	@Override
	public double prim() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isTree() {
		// TODO Auto-generated method stub
		return false;
	}
}