import java.util.Random;

public class AdjMatrixGraph {
	private float[][] matrix;
	
	public AdjMatrixGraph(int d, int n) {
		Random rand = new Random(System.nanoTime());
		switch (d) {
			case 0: 
				for (int i = 0; i < n; i++) {
					int diag = n - i;
					for (int j = 0; j < diag; j++) {
						float edgeWeight = rand.nextFloat();
						matrix[i][j] = edgeWeight;
						matrix[j][i] = edgeWeight;
					}
				}
				break;
			case 2:
				float[] xs = new float[n];
				float[] ys = new float[n];
				for (int i = 0; i < n; i++) {
					xs[i] = rand.nextFloat();
					ys[i] = rand.nextFloat();
				}
				for (int i = 0; i < n; i++) {
					int diag = n - i;
					for (int j = 0; j < diag; j++) {
						float jPos = rand.nextFloat();
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
}
