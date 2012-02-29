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
        		Graph g0;
        		double totalWeights = 0;
        		for (int i = 0; i < numtrials; i++) {
        			g0 = new AdjListGraph(dimension, numpoints);
        			totalWeights += g0.prim();
        		}
        		double averageWeight = totalWeights / numtrials;
        		System.out.println(averageWeight + "  " + numpoints + "  " + numtrials + "  " + dimension);
        		break;
        	case 1:
        		Graph g1 = new AdjListGraph(dimension, numpoints);
        		g1.prim();
        		//System.out.println("done");
        		break;
        	case 2:
        		double lim = 0.13;
        		for (int i = numpoints; i <= numpoints; i+=1000) {
        			for (int j = 0; j < numtrials; j++) {
        				//Graph g2 = new AdjListGraph();
        				Graph g2 = new AdjListGraph(dimension, i);
        				//g2.print();
        				g2.prim();
        			}
        		}
        		break;
        	case 3:
        		//int[] array = {1,2,10,8,3,9,7,4,6,5};
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
        		
        	default:
        		System.exit(1);
        }
	}
}
