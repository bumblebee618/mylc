/***
 * 
 * @author jackie
 * (This problem is an interactive problem.)

You may recall that an array arr is a mountain array if and only if:

arr.length >= 3
There exists some i with 0 < i < arr.length - 1 such that:
arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target. If such an index does not exist, return -1.

You cannot access the mountain array directly. You may only access the array using a MountainArray interface:

MountainArray.get(k) returns the element of the array at index k (0-indexed).
MountainArray.length() returns the length of the array.
Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer. Also, any solutions that attempt to circumvent the judge will result in disqualification.

 

Example 1:

Input: array = [1,2,3,4,5,3,1], target = 3
Output: 2
Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.
Example 2:

Input: array = [0,1,2,4,2,1], target = 3
Output: -1
Explanation: 3 does not exist in the array, so we return -1.
 

Constraints:

3 <= mountain_arr.length() <= 104
0 <= target <= 109
0 <= mountain_arr.get(index) <= 109
*
 */
public class Q1095_Find_in_Mountain_Array {
	public int findInMountainArray(int target, MountainArray mountainArr) {
        if (mountainArr == null) {
            return -1;
        }

        int size = mountainArr.length();
        int peek = findPeek(0, size-1, mountainArr);

        int result = searchNum(0, peek, target, mountainArr, true);
        return result != -1 ? result : searchNum(peek, size-1, target, mountainArr, false);
    }

    private int findPeek(int left, int right, MountainArray mountainArr) {
        while (left+1 < right) {
            int mid = left + (right - left) / 2;

            if (mountainArr.get(mid) < mountainArr.get(mid-1)) {
                right = mid;
            } else if (mountainArr.get(mid) < mountainArr.get(mid+1))  {
                left = mid;
            } else {
                return mid;
            }
        }

        return mountainArr.get(left) > mountainArr.get(right) ? left : right;
    }

    private int searchNum(int left, int right, int target, MountainArray mountainArr, boolean isIncreased) {
        while (left < right) {
            int mid = left + (right - left) / 2;
            int midValue = mountainArr.get(mid);

            if (midValue < target) {
                if (isIncreased) {
                    left = mid+1;
                } else {
                    right = mid-1;
                }
            } else if (midValue > target) {
                if (isIncreased) {
                    right = mid-1;
                } else {
                    left = mid+1;
                }
            } else {
                return mid;
            }
        }

        return mountainArr.get(left) == target ? left : -1;
    }
    
    
    class MountainArray {
    	public int get(int index) {
    		return index;
    	}
    	
    	public int length() {
    		return 1;
    	}
    }
}
