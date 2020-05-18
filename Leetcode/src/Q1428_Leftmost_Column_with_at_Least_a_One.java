import java.util.LinkedList;
import java.util.List;

/***
 * 
 * @author jackie
 * 
 * 
 */
public class Q1428_Leftmost_Column_with_at_Least_a_One {
	public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        if (binaryMatrix == null)
        {
            return -1;
        }
        
        List<Integer> dimensions = binaryMatrix.dimensions();
        int row = dimensions.get(0);
        int col = dimensions.get(1);
        int x = 0, y = col-1;
        
        while (y >= 0 && x < row)
        {
            int value = binaryMatrix.get(x, y);
            
            if (value == 1)
            {
                y--;
            }
            else
            {
                x++;
            }
        }
        
        return (x >= row && y == col-1) ? -1 : y+1;
    }
}

class BinaryMatrix 
{
	public int get(int x, int y)
	{
		return 1;
	}
	
	public List<Integer> dimensions()
	{
		return new LinkedList<Integer>();
	}
}
