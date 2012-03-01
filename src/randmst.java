import java.util.Random;


public class randmst {
	public static void main (String[] args) {
		String usage = "Usage: <program> <run version> <numpoints> <numtrials> <dimension>";
        if (args.length != 4) {
            System.out.println("Wrong number of arguments.");
            System.out.println(usage);
            return;
        }
        
        int version = Integer.parseInt(args[0]);
        int numpoints = Integer.parseInt(args[1]);
        int numtrials = Integer.parseInt(args[2]);
        int dimension = Integer.parseInt(args[3]);
        
        switch (version) {
        	case 0:
        		// grading mode
        		double totalWeights = 0;
        		for (int i = 0; i < numtrials; i++) {
        			Graph g = new AdjListGraph(dimension, numpoints);
        			double weight = g.prim();
        			totalWeights += weight;
        		}
        		double avg = totalWeights / numtrials;
    			System.out.println(avg + " " + numpoints + " " + numtrials + " " + dimension);
        		break;
        	case 1:
        		// testing
        		Graph g0;
        		totalWeights = 0;
        		for (int i = 0; i < numtrials; i++) {
        			g0 = new AdjListGraph(dimension, numpoints);
        			totalWeights += g0.prim();
        		}
        		double averageWeight = totalWeights / numtrials;
        		System.out.println(averageWeight + "  " + numpoints + "  " + numtrials + "  " + dimension);
        		break;
        	case 2:
        		// testing that prim halts
        		Graph g1 = new AdjListGraph(dimension, numpoints);
        		g1.prim();
        		//System.out.println("done");
        		break;
        	case 3:
        		// testing many values of n; timing
        		for (int i = numpoints; i <= 32768; i*=2) {
        			totalWeights = 0;
        			for (int j = 0; j < numtrials; j++) {	
        				Graph g2 = new AdjListGraph(dimension, i);
        				long start = System.nanoTime();
        				double weight = g2.prim();
        				long elapsed = System.nanoTime() - start;
        				double seconds = (double)elapsed / 1000000000.0;
        				System.out.println("Running time " + seconds);
        				totalWeights += weight;
        			}
        			avg = totalWeights / numtrials;
        			System.out.println(avg + "  " + numpoints + "  " + numtrials + "  " + dimension);
        		}
        		break;
        	case 4:
        		// testing heap
        		MinHeap h = new MinHeap();
        		
        		Random rand = new Random();
        		
        		for(int i = 0; i < 10000; i++) {
        			h.insert(new AdjListNode(i,rand.nextInt()%100));
        		}
        		
        		while (!h.isEmpty()) {
        			AdjListNode x = h.deleteMin();
        			System.out.println(x.toString() + "\n");
        		}
        		break;
        	case 5:
        		// testing small graph
        		int[] ns = {16, 32, 64, 128}; // 256, 512, 1024, 2048, 4096, 8192, 16384, 32678};
        		
        		for (int d = 0; d <= dimension; d++) {
        			for (int n : ns) {
        				if (d == 1)
        					continue;
	        			for (int i = 0; i < numtrials; i++) {
	        				Graph g4 = new AdjListGraph(d, n);
	        				g4.prim();
	        			}
        			}
        		}
        		
        	default:
        		System.exit(1);
        }
	}
}
