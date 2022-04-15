import java.util.*;

/***
 * 
 * @author jackie
 * 
 * Given two sparse vectors, compute their dot product.

Implement class SparseVector:

SparseVector(nums) Initializes the object with the vector nums
dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and compute the dot product between two SparseVector.

Follow up: What if only one of the vectors is sparse?

 

Example 1:

Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
Output: 8
Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
Example 2:

Input: nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
Output: 0
Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0
Example 3:

Input: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
Output: 6
 

Constraints:

n == nums1.length == nums2.length
1 <= n <= 10^5
0 <= nums1[i], nums2[i] <= 100
 */
public class Q1570_Dot_Product_of_Two_Sparse_Vectors {
	// solution 1: using List and binary search
	public List<int[]> pairs;
	   
	Q1570_Dot_Product_of_Two_Sparse_Vectors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        
        pairs = new ArrayList<>();
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                pairs.add(new int[] {i, nums[i]});
            }
        }
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(Q1570_Dot_Product_of_Two_Sparse_Vectors vec) {
        if (vec == null) {
            return 0;
        } else if (vec.pairs.size() < pairs.size()) {
            return vec.dotProduct(this);
        }
        
        int result = 0;
        
        for (int[] pair : pairs) {
            int pos = binarySearch(vec.pairs, pair[0]);
            
            if (pos != -1) {
                result += pair[1] * vec.pairs.get(pos)[1];
            }
        }
        
        return result;
    }
    
    private int binarySearch(List<int[]> pairs, int target) {
        int left = 0, right = pairs.size()-1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            int[] pair = pairs.get(mid);
            
            if (pair[0] > target) {
                right = mid-1;
            } else if (pair[0] < target) {
                left = mid+1;
            } else {
                return mid;
            }
        }
        
        return pairs.get(left)[0] == target ? left : -1;
    }

	
	
    
    // solution 2: using List and two pointers
    /*** 
    private int getResult_two_pointers(SparseVector vec)
    {
        int result = 0;
        int index1 = 0, index2 = 0;
        
        while (index1 < pairs.size() && index2 < vec.pairs.size())
        {
            int[] pair1 = pairs.get(index1);
            int[] pair2 = vec.pairs.get(index2);
            
            if (pair1[0] == pair2[0])
            {
                result += pair1[1] * pair2[1];
                index1++;
                index2++;
            }
            else if (pair1[0] < pair2[0])
            {
                index1++;
            }
            else
            {
                index2++;
            }
        }
        
        return result;
    }
	***/
	
	
    
    // solution 3: using hashmap
	/***
	public Map<Integer, Integer> map;
    
	Q1570_Dot_Product_of_Two_Sparse_Vectors(int[] nums) 
	{
		map = new HashMap<>();
		
        if (nums == null || nums.length == 0)
        {
            return;
        }
        
        for (int i = 0; i < nums.length; i++)
        {
            if (nums[i] != 0)
            {
                map.put(i, nums[i]);
            }
        }
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(Q1570_Dot_Product_of_Two_Sparse_Vectors vec) 
    {
        if (vec == null)
        {
            return 0;
        }
        else if (vec.getSize() < map.size()) // 防止current map过大，优化运算时间
        {
            return vec.dotProduct(this);
        }
        
        int sum = 0;
        
        for (int index : map.keySet())
        {
        	sum += getValue(index) * vec.getValue(index); 
        }
        
        return sum;
    }
    
    public int getValue(int index)
    {
    	return map.getOrDefault(index, 0);
    }
    
    public int getSize()
    {
    	return map.size();
    }
    ***/
}
