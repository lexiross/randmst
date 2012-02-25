import java.util.Random;

public class AdjMatrixGraph implements Graph {
	private float[][] matrix;
	
	public AdjMatrixGraph(int d, int n) {
		Random rand = new Random(System.nanoTime());
		switch (d) {
			case 0: 
				for (int i = 0; i < n; i++) {
					for (int j = i; j < n; j++) {
						float edgeWeight = rand.nextFloat();
						matrix[i][j] = edgeWeight;
						matrix[j][i] = edgeWeight;
					}
				}
				break;
			case 2:
			case 3:
			case 4:
				float[][] vertices = new float[n][d];
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < d; j++)
						vertices[i][j] = rand.nextFloat();
				}
				for (int i = 0; i < n; i++) {
					for (int j = i; j < n; j++) {
						float edgeWeight = dist(vertices[i],vertices[j],d);
						matrix[i][j] = edgeWeight;
						matrix[j][i] = edgeWeight;
					}
				}
				break;
			default: break;
		}
		
	}
	
	static float dist(float[] x, float[] y, int d) {
		float sumSquares = 0;
		for (int i = 0; i < d; i++) {
			sumSquares += Math.pow((x[i] - y[i]), 2);
		}
		
		return (float) Math.sqrt(sumSquares);
	}

	@Override
	public double prim() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isGraph() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		
	}
}
