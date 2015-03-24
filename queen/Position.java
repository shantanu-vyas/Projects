//make easier by returning position with x and y values 
public class Position
{
    private int x,y;
    public Position(int _x, int _y)
    {
        x = _x;
        y = _y;
    }
    public int getx()
    {
        return x;
    }
    public int gety()
    {
        return y;
    }
    public String toString()
    {
        return ("x: " + x +  " y: " + y);
    }
}
