import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        
        System.out.println("Hello! Welcome to Maze Generation. Please enter the size of the maze (width and height)");
        Scanner scan = new Scanner(System.in);

        int width = scan.nextInt(); 
        int height = scan.nextInt();

        scan.close();

        Graph g = new Graph(width, height); 

        System.out.print( g.printMaze() );
    }
}

