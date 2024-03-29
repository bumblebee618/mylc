import java.util.*;
/******
 * 
 * 
 * 4, 140*, 157, 158*, 163, 
 * 228*, 166**, 212*, 214*(快速方法), 218, 
 * 224, 224*, 230 (logn + n方法), 257 (iterator和recursive方法), 265, 269, 
 * 271, 272*, 274*, 275*, 282**, 
 * 284*, 286, 289*, 302**(binary search 方法), 305, 308**, 313**, 264, 294,
 * 317, 320, 324*, 327**, 330*, 
 * 331*, 332, 336, 341*, 348, 
 * 351**, 354*(binary search方法), 356* (follow up, Arrays.hashCode()), 358*, 361*, 363**, 
 * 368**, 369**(constant space方法), 370*, 375*, 379*, 381*, 
 * 391**, 393*, 394**, 353**, 397**(2种方法), 
 * 399**, 400*, 402*, 406*, 411 (?), 
 * 418**, 421**, 425**, 444*(细节), 447*, 
 * 459*, 460*, 474*(两种解法), 494
 * 
 * 
 * 22, 31*, 57*(O(n)方法), 146, 159, 
 * 162, 239, 252 (方法2), 281, 286*, 
 * 288*, 289, 295*, 310*, 315 (第二种方法)，
 * 318, 329*, 340, 346, 356, 
 * 360, 362, 370, 373, 377, 
 * 401*, 407, 408*, 417, 422*, 
 * 463*(第二种方法), 469*, 475, 480*, 482*, 
 * 
 * 
 * 
 * Design: 
 * 			Q146_LRU_Cache
 * 			Q284_Peeking_Iterator*
 * 			Q288_Unique_Word_Abbreviation*
 * 			Q295_Find_Median_from_Data_Stream
 * 			Q308_Range_Sum_Query_2D_Mutable*
 * 			Q341_Flatten_Nested_List_Iterator*
 * 			Q346_Moving_Average_from_Data_Stream
 * 			Q348_Design_Tic_Tac_Toe
 * 			Q351_Android_Unlock_Patterns*
 * 			Q353_Design_Snake_Game*,
 * 			Q362_Design_Hit_Counter
 * 			Q379_Design_Phone_Directory*
 * 			Q380_Insert_Delete_GetRandom_O_1
 * 			Q381_Insert_Delete_GetRandom_O_1_Duplicates_allowed*
 * 			Q382_Linked_List_Random_Node
 * 			Q460_LFU_Cache*
 * 
 * String Abbreviation:
 * 			320, 408, 
 * 
 * Segment Tree:
 * 			327, 
 * 
 * 
 * Q004_Median_of_Two_Sorted_Arrays*, Q022_Generate_Parentheses*, Q140_Word_Break_II*, Q157_Read_N_Characters_Given_Read4, Q158_Read_N_Characters_Given_Read4_II_Call_multiple_times*, 
 * Q163_Missing_Ranges*, Q228_Summary_Ranges*, Q166_Fraction_to_Recurring_Decimal*, Q212_Word_Search_II*, Q214_Shortest_Palindrome*, 
 * Q218_The_Skyline_Problem*, Q224_Basic_Calculator*, Q224_Basic_Calculator_III*, Q230_Kth_Smallest_Element_in_a_BST* (logn方法), Q257_Binary_Tree_Paths* (iterator和recursive方法), 
 * Q269_Alien_Dictionary*, Q271_Encode_and_Decode_Strings*, Q272_Closest_Binary_Search_Tree_Value_II*, Q274_H_Index*, Q275_H_Index_II*, 
 * Q282_Expression_Add_Operators*, Q284_Peeking_Iterator*, Q302_Smallest_Rectangle_Enclosing_Black_Pixels*, Q305_Number_of_Islands_II*, Q308_Range_Sum_Query_2D_Mutable*, 
 * Q313_Super_Ugly_Number*, Q320_Generalized_Abbreviation*, Q324_Wiggle_Sort_II*, Q327_Count_of_Range_Sum*, Q330_Patching_Array*, 
 * Q331_Verify_Preorder_Serialization_of_a_Binary_Tree*, Q332_Reconstruct_Itinerary*, Q336_Palindrome_Pairs*, Q341_Flatten_Nested_List_Iterator*, Q348_Design_Tic_Tac_Toe*, 
 * Q351_Android_Unlock_Patterns*, Q354_Russian_Doll_Envelopes*, Q361_Bomb_Enemy*, Q368_Largest_Divisible_Subset*, Q375_Guess_Number_Higher_or_Lower_II*, 
 * Q379_Design_Phone_Directory*, Q381_Insert_Delete_GetRandom_O_1_Duplicates_allowed*, Q391_Perfect_Rectangle*, Q393_UTF8_Validation*, Q394_Decode_String*, 
 * Q399_Evaluate_Division*, Q400_Nth_Digit*, 
 * 
 * 
 * Q031_Next_Permutation, Q057_Insert_Interval, Q146_LRU_Cache, Q159_Longest_Substring_with_At_Most_Two_Distinct_Characters, Q162_Find_Peak_Element，
 * Q239_Sliding_Window_Maximum, Q252_Meeting_Rooms (方法2), Q281_Zigzag_Iterator, Q286_Walls_and_Gates, Q288_Unique_Word_Abbreviation, 
 * Q289_Game_of_Life, Q295_Find_Median_from_Data_Stream, Q310_Minimum_Height_Trees, Q315_Count_of_Smaller_Numbers_After_Self(第二种方法)，Q318_Maximum_Product_of_Word_Lengths, 
 * Q329_Longest_Increasing_Path_in_a_Matrix, Q340_Longest_Substring_with_At_Most_K_Distinct_Characters, Q346_Moving_Average_from_Data_Stream, Q356_Line_Reflection, Q358_Rearrange_String_k_Distance_Apart, 
 * Q360_Sort_Transformed_Array, Q362_Design_Hit_Counter, Q369_Plus_One_Linked_List, Q370_Range_Addition, Q373_Find_K_Pairs_with_Smallest_Sums, 
 * Q377_Combination_Sum_IV, Q401_Binary_Watch, Q402_Remove_K_Digits, Q407_Trapping_Rain_Water_II, Q475_Heaters
 * 
 * 
 * 
 * */

public class A_Data_Structure {

}

class RandomListNode {
	int label;
	RandomListNode next, random;
	RandomListNode(int x) { this.label = x; }
};

class TreeNode {
	int val;
	TreeNode left, right;
	
	public TreeNode (int value) {
		this.val = value;
		left = right = null;
	}
}

class Point {
	int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class ListNode {
	int val;
	ListNode next;
	
	ListNode(int x) { val = x; }
}

class Connection{
	String city1;
	String city2;
	int cost;
	
	public Connection(String c1, String c2, int c){
		city1 = c1;
		city2 = c2;
		cost = c;
	}
	
	@Override
	public String toString() {
		return city1 + " " + city2 + " " + cost;
	}
}

class Interval {
	int start;
	int end;
	Interval() { start = 0; end = 0; }
	Interval(int s, int e) { start = s; end = e; }
}

class UndirectedGraphNode {
	int label;
	List<UndirectedGraphNode> neighbors;
	UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
};


