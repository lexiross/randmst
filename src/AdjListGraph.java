import java.util.Random;

public class AdjListGraph implements Graph {
	private AdjListNode[] vertices;

	public AdjMatrixGraph(int d, int n) {
		float N = 0.1;
		Random rand = new Random(System.nanoTime());
		
		vertices = new AdjListNode[n];
		for (int i = 0; i < n; i++) {
			vertices[i] = new AdjListNode();
		}
		
		switch (d) {
			case 0: 
				for (int i = 0; i < n; i++) {
					for (int j = i; j < n; j++) {
						float edgeWeight = rand.nextFloat();
						
						if (edgeWeight <= N) {
							AdjListNode iNode = new AdjListNode(i,edgeWeight);
							iNode.setNext(vertices[j].getNext);
							vertices[j].setNext(iNode);
							
							AdjListNode jNode = new AdjListNode(j,edgeWeight);
							jNode.setNest(vertices[i].getNext);
							vertices[i].setNext(jNode);
						}
					}
				}
				break;
			case 2:
			case 3:
			case 4:
				float[] points = new float[n][d];
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < d; j++)
						points[i][j] = rand.nextFloat();
				}
				for (int i = 0; i < n; i++) {
					for (int j = i; j < n; j++) {
						float edgeWeight = dist(points[i],points[j],d);
						
						if (edgeWeight <= N) {
							AdjListNode iNode = new AdjListNode(i,edgeWeight);
							iNode.setNext(vertices[j].getNext);
							vertices[j].setNext(iNode);
							
							AdjListNode jNode = new AdjListNode(j,edgeWeight);
							jNode.setNest(vertices[i].getNext);
							vertices[i].setNext(jNode);
						}
					}
				}
				break;
			default: break;
		}
	}
}