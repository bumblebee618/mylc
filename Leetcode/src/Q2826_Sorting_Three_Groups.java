import java.util.ArrayList;
import java.util.List;

/***
 * 
 * @author jackie
 * You are given an integer array nums. Each element in nums is 1, 2 or 3. In each operation, you can remove an element from nums. Return the minimum number of operations to make nums non-decreasing.

 

Example 1:

Input: nums = [2,1,3,2,1]

Output: 3

Explanation:

One of the optimal solutions is to remove nums[0], nums[2] and nums[3].

Example 2:

Input: nums = [1,3,2,1,3,3]

Output: 2

Explanation:

One of the optimal solutions is to remove nums[1] and nums[2].

Example 3:

Input: nums = [2,2,2,2,3,3]

Output: 0

Explanation:

nums is already non-decreasing.

 

Constraints:

1 <= nums.length <= 100
1 <= nums[i] <= 3
 */
public class Q2826_Sorting_Three_Groups {
	public int minimumOperations(List<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return 0;
        }

        List<Integer> list = new ArrayList<>();

        for (int num : nums) {
            if (list.size() == 0 || list.get(list.size()-1) <= num) {
                list.add(num);
            } else {
                int pos = binarySearch(list, num);
                list.set(pos, num);
            }
        }

        return nums.size() - list.size();
    }

    private int binarySearch(List<Integer> list, int target) {
        int left = 0, right = list.size()-1;

        while (left+1 < right) {
            int mid = left + (right-left) / 2;

            if (list.get(mid) > target) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return list.get(left) > target ? left : right;
    }
}
