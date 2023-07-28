// This file will hold the neccessary code to generate a graph for the maze 
import java.util.Stack; 
import java.util.Random; 
import java.util.HashSet;
import java.util.Set;
import java.util.LinkedList;
import java.util.ArrayList;

public class Graph {

    private int [][] mazeMatrix;
    private Stack<Vertex> stack = new Stack<>();     

    private int height; 
    private int width;
    private int mazeWidth; 
    private int mazeHeight;

    // These variables will be to print the graph using ASCII colors 
    private static final String ASCCI_YELLOW = "\u001B[43m";
    private static final String ASCCI_BLACK = "\u001b[40m";
    private static final String ASCCI_RESET = "\u001B[0m";
    private static final String ASCCI_WHITE = "\u001B[47m";
    private static final String ASCCI_GREEN = "\u001B[42m";
    private static final String ASCCI_PURPLE = "\u001B[45m";

    public Graph(int width, int height)
    {
        this.height = height;
        this.width = width;
        mazeHeight = 1 + 2 * ( height - 1 );
        mazeWidth = 1 + 2 * ( width - 1 );
        mazeMatrix = new int [ mazeHeight ][ mazeWidth ]; 
 
       
        generateGraphDFS(); 

        System.out.print( printMaze() );
    }

    public String printMaze()
    {
        // This function will print walls around the graph that represent the node. If the nodes are connected, then there is no wall 
        StringBuilder sb = new StringBuilder();
        sb.append( ASCCI_YELLOW + "  ".repeat(mazeWidth) + "    " + ASCCI_RESET + '\n');

        for ( int i = 0; i < mazeHeight; i++)
        {
            sb.append(ASCCI_YELLOW + "  " + ASCCI_RESET);
           for ( int j = 0; j < mazeWidth; j++)
           {
                if ( mazeMatrix[i][j] == 0)
                {
                    sb.append( ASCCI_BLACK + "//" + ASCCI_RESET);
                }
                else if ( mazeMatrix[i][j] == 1)
                {
                    sb.append( ASCCI_WHITE + "  " + ASCCI_RESET);

                }
                else if ( mazeMatrix[i][j] == 2)
                {
                    sb.append( ASCCI_PURPLE + "  " + ASCCI_RESET);
                } else if ( mazeMatrix[i][j] == 3)
                {
                    sb.append( ASCCI_GREEN + "  " + ASCCI_RESET);
                }
               
           }
           sb.append(ASCCI_YELLOW + "  " + ASCCI_RESET + '\n');
        }
        sb.append( ASCCI_YELLOW + "  ".repeat(mazeWidth) + "    " + ASCCI_RESET + '\n');
        
        return sb.toString();
    }

    private void generateGraphDFS()
    {
        /*
        Algorithm found here: https://www.algosome.com/articles/maze-generation-depth-first.html 

            Randomly select a node (or cell) N.
            Push the node N onto a queue .
            Mark the cell N as visited.
            Randomly select an adjacent cell A of node N that has not been visited. If all the neighbors of N have been visited:
            Continue to pop items off the queue Q until a node is encountered with at least one non-visited neighbor - assign this node to N and go to step 4.
            If no nodes exist: stop.
            Break the wall between N and A.
            Assign the value A to N.
            Go to step 2.

        */
        // 1 - Path, 0 - Wall, 2 - Start, 3 - Destination
        
        // Randomly select edge node as the start and find the longest path to be the end of the maze

        Set<String> visited = new HashSet<>(); 
        ArrayList<Vertex> neighbors = new ArrayList<>();
        
        Random random = new Random();
        int randomXEdge = random.nextBoolean() ? 0 : mazeWidth - 1; 
        int randomYEdge = random.nextBoolean() ? 0 : mazeHeight - 1; 

        Vertex startNode = new Vertex(randomXEdge , randomYEdge, null);
        mazeMatrix[ startNode.getY() ][ startNode.getX() ] = 2; // Mark startNode as the starting node in the maze matrix 
        visited.add( Integer.toString( startNode.getX()) + " " + Integer.toString( startNode.getY()));
        neighbors = getNeighbors( startNode, visited);
        randomlyPushNeighbors( neighbors );
        Vertex oldVertex = startNode;


        while ( !stack.isEmpty() ) 
        {
            Vertex cur = stack.pop(); 
            
        
            breakWall( cur.getParentNode() , cur);
            
            oldVertex = cur; 
            mazeMatrix[ cur.getY() ][ cur.getX()  ] = 1; 
            neighbors = getNeighbors(cur, visited);
            randomlyPushNeighbors( neighbors );
        }

    }  

    private void breakWall( Vertex oldVertex, Vertex newVertex)
    {
        int x = (oldVertex.getX() + newVertex.getX() ) / 2 ;
        int y = ( oldVertex.getY() + newVertex.getY()) / 2;

        mazeMatrix[y][x] = 1;

    }
    private ArrayList<Vertex> getNeighbors(Vertex cur, Set<String> visited) 
    {
        ArrayList<Vertex> neighbor = new ArrayList<>();
        // Use this directional array to help find the adjacent neighbors using the unit circle
        int [] dirc = { 0, 2, 0, -2, 0 };

        for ( int i = 0; i < dirc.length - 1; i++)
        {
            int x = cur.getX() + dirc[i];
            int y = cur.getY() + dirc[i + 1];
            
            
            if ( x >= 0 && x < mazeWidth && y >= 0 && y < mazeHeight)
            {
                if ( !visited.contains( Integer.toString(x) + " " +  Integer.toString( y ) ) )
                {
                    neighbor.add( new Vertex( x, y, cur ));
                    visited.add( Integer.toString(x) + " " + Integer.toString( y ) );
                }
            }
        }
        return neighbor;
    }
    private void randomlyPushNeighbors(ArrayList<Vertex> neighbor) 
    {
        Random rand = new Random(); 
        while ( !neighbor.isEmpty())
        {
            int randomIndex = rand.nextInt( neighbor.size() );
            stack.push( neighbor.remove(randomIndex) ); 
        }
    }



}
