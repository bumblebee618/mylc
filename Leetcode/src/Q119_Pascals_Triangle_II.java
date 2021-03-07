import java.util.ArrayList;
import java.util.List;

/******
 * 
Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?

 * 
 * */

public class Q119_Pascals_Triangle_II {
	// solution 1: time is O(n^2), space is O(k)
	public List<Integer> getRow(int rowIndex) 
    {
        if (rowIndex < 0)
        {
            return new ArrayList<Integer>();
        }
        
        List<Integer> prevList;
        List<Integer> curList = new ArrayList<>();
        int curRow = 0;
        
        while (curRow <= rowIndex)
        {
            prevList = new ArrayList<>(curList); 
            curList.clear();
            
            for (int i = 0; i <= curRow; i++)
            {
                if (i == 0 || i == curRow)
                {
                    curList.add(1);
                }
                else
                {
                    curList.add(prevList.get(i-1) + prevList.get(i));
                }
            }
            
            curRow++;
        }
        
        return curList;
    }
	
	
	
	
	
	
	
	
	/*************************** main function ******************************/
	
	public static void main(String[] args)
	{
		Q119_Pascals_Triangle_II t = new Q119_Pascals_Triangle_II();
		List<Integer> res = new ArrayList<Integer>();
		res = t.getRow(1);
		for(int i = 0; i < res.size(); ++i){
			System.out.print(res.get(i) + ", ");
		}
	}
}
