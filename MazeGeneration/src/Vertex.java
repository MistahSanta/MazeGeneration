
public class Vertex
{
    private int x; private int y;
    private Vertex parent;
    public Vertex(int x, int y, Vertex parent)
    {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    public int getX() { return x;}
    public int getY() { return y;}
    public Vertex getParentNode() { return parent;}
}