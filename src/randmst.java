
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
        		break;
        	case 1:
        		Graph g = new AdjListGraph(dimension, numpoints, 5, true);
        		g.print();
        		break;
        	default:
        		System.exit(1);
        }
	}
}
