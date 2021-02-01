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
	public Map<Integer, Integer> map;
    
	Q1570_Dot_Product_of_Two_Sparse_Vectors(int[] nums) {
        if (nums == null || nums.length == 0)
        {
            return;
        }
        
        map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++)
        {
            if (nums[i] != 0)
            {
                map.put(i, nums[i]);
            }
        }
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(Q1570_Dot_Product_of_Two_Sparse_Vectors vec) {
        if (map.size() == 0 || vec.map.size() == 0)
        {
            return 0;
        }
        else if (map.size() > vec.map.size()) // 防止current map过大，优化运算时间
        {
            return vec.dotProduct(this);
        }
        
        int sum = 0;
        
        for (Map.Entry<Integer, Integer> entry : map.entrySet())
        {
            if (vec.map.containsKey(entry.getKey()))
            {
                sum += entry.getValue() * vec.map.get(entry.getKey());
            }
        }
        
        return sum;
    }
}
