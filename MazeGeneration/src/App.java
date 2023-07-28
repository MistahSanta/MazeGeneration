public class App {
    public static void main(String[] args) throws Exception {
        // Use Krusall Algorithm to use a maze. 
        // Genernal idea is image each spot as a node and create edges then check if they are valid. 
        // Hardset part will be implement the krusall background information

        System.out.println("Hello! Welcome to Maze Generation. We will use a maze of size 3x3 for testing");
        Graph g = new Graph(30, 30); 
        System.out.println("Success");
    }
}


/*
 * Goals
 * - Make the Graph that is dependent on the user's input (worry later)
 * Create a graph generation 
 * Create a depth search search 
 */