import java.util.ArrayList;
import java.util.List;
/********
 *
Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], return its missing ranges.

For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].

 * 
 * */


public class Le_163_Missing_Ranges {
	 /******************************************************************
	  * lower -> nums[0],  nums[i] -> nums[i + 1], nums[n - 1] -> upper
	  * 
	  *******************************************************************/
	
	// test case: [0, Integer.MAX_VALUE]
	
	public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ans = new ArrayList<>();
        
        if(lower > upper){
            return ans;
        }
        
        int numNeed = lower;
        
        for (int num : nums) {
            if (num < numNeed) {
                continue;
            } else if (num == numNeed) {
            	if (num == Integer.MAX_VALUE) {  // test case: [Integer.MAX_VALUE], Integer.MAX_VALUE, Integer.MAX_VALUE
                    return ans;
                } else {
                	numNeed++;
                }
            } else {
                ans.add(getStr(numNeed, num - 1));
                
                if (num == Integer.MAX_VALUE) {    // 防止test case 为 [0, Integer.MAX_VALUE] ！！！
                    return ans;
                } else {
                    numNeed = num + 1;
                }
            }
        }
        
        if (numNeed <= upper) {                    // 注意扫尾 ！！！
            ans.add(getStr(numNeed, upper));
        }
        
        return ans;
    }
    
    public String getStr(int start, int end){
        StringBuilder builder = new StringBuilder();
        
        if(start == end){
            builder.append(start);
        } else {
            builder.append(start).append("->").append(end);
        }
        
        return builder.toString();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	
    
    /****************************************************************************/
    // by Jackie, too complex
	public List<String> findMissingRanges2(int[] nums, int lower, int upper) {
        List<String> ans = new ArrayList<String>();
        int startValue = 0, endValue = 0;
        int end = 0;
        int n = nums.length;
        
        if(nums == null || nums.length == 0 || lower > upper){     
            startValue = lower;
            endValue = upper;
            StringBuffer builder = new StringBuffer();
            if(startValue == endValue){
                builder.append(startValue);
            } else {
                builder.append(startValue).append("->").append(endValue);
            }
            ans.add(builder.toString());
            return ans;
        }

        if(lower < nums[0]){
            startValue = lower;
            endValue = nums[0] - 1;
            StringBuffer builder = new StringBuffer();
            if(startValue == endValue){
                builder.append(startValue);
            } else {
                builder.append(startValue).append("->").append(endValue);
            }
            ans.add(builder.toString());
        }
        
        while(end < n - 1){
            while(end < n - 1 && nums[end] + 1 == nums[end + 1]){
                end++;
            }
            
            if(end < n - 1){
                startValue = nums[end] + 1;
                endValue = nums[end + 1] - 1;
                StringBuffer builder = new StringBuffer();
                if(startValue == endValue){
                    builder.append(startValue);
                } else {
                    builder.append(startValue).append("->").append(endValue);
                }
                ans.add(builder.toString());
                end++;
            }
        }
        
        if(upper > nums[n - 1]){
            startValue = nums[n - 1] + 1;
            endValue = upper;
            StringBuffer builder = new StringBuffer();
            if(startValue == endValue){
                builder.append(startValue);
            } else {
                builder.append(startValue).append("->").append(endValue);
            }
            ans.add(builder.toString());
        }
        
        return ans;
    }
    
    public void addToList(List<String> ans, int sValue, int eValue){
        StringBuffer builder = new StringBuffer();
        if(sValue == eValue){
            builder.append(sValue);
        } else {
            builder.append(sValue).append("->").append(eValue);
        }
        ans.add(builder.toString());
    }
}
