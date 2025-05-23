import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;
import java.util.List;
/*******************************************************
 * (1). 括号类： *22， 
 * (2). 字符串切割: *131, *132, **139, **140,  
 * (3). 回文数: 
 * (4). Permutation 序列: **31, 46, *47, **60,  
 * 
 *******************************************************/





public class Daily_Report {	
	public static void main(String[] args){		
		int[] finish_by_company = {
				// linkin
				34, 46, 47, 104, 150, 152, 156, 170, 187, 198, 213, 337, 205, 243, 244, 245, 256, 339,
				// google
				4, 22, 31, 54, 66, 140, 162, 163, 212, 214, 228, 231, 246, 247, 249, 251, 259, 266, 270, 276, 279, 280, 281, 284, 288, 
				293, 294, 295, 298, 302, 305, 309, 310, 312, 315, 318, 323, 324, 326, 329, 330, 331, 332, 336, 340, 341, 345, 346, 348, 354, 
				// uber
				36, 39, 254, 290, 
				// airbnb
				108, 136, 137, 260, 190, 217, 219, 220, 
				// facebook
				1, 10, 13, 15, 17, 20, 23, 25, 26, 28, 33, 38, 44, 49, 50, 56, 57, 67, 69, 71, 75, 76, 78, 
				79, 80, 85, 88, 90, 91, 98, 102, 117, 121, 125, 127, 128, 133, 139, 146, 157, 158, 161, 168, 173, 
				200, 206, 208, 209, 210, 211, 215, 218, 221, 234, 235, 236, 238, 252, 253, 257, 261, 269, 274, 275, 
				277, 278, 283, 285, 286, 297, 301, 311, 314, 325, 334,
				// twitter
				12, 43, 118, 119, 202, 296,
				// zenefits
				51, 52, 109, 169, 229, 207, 255,
				// amazon
				2, 3, 5, 8, 21, 42, 48, 89, 138, 141, 142, 155, 160, 167, 186, 199, 204, 239, 240, 242, 
				// microsoft
				73, 94, 112, 114, 116, 124, 153, 154, 165, 171, 191, 237, 258, 333,
				// snapchat
				96, 95, 289,
				// apple
				70, 149,
				//yahoo
				
				//dropbox
				
				//Epic Systems
				306,
				// bloomberg
				7, 11, 16, 24, 53, 62, 63, 100, 101, 103, 105, 106, 110, 113, 122, 123, 131, 151, 172, 189, 225, 230, 232, 268, 287,
				// palantir
				303, 
				// Two Sigma
				342, 
				// yelp
				14, 347, 
				// pocket gems
				
				// adobe
				292, 				
				// other
				6, 9, 18, 19, 27, 29, 30, 32, 35, 40, 41, 45, 55, 58, 59, 61, 64, 72, 74, 77, 81, 82, 83, 84, 86, 87, 92, 93, 97, 99, 
				107, 111, 115, 120, 129, 130, 132, 134, 143, 144, 145, 147, 148, 159, 164, 179, 
				203, 216, 222, 223, 226, 227, 250, 263, 264, 267, 299, 300, 304, 307, 319, 322, 328, 338, 343, 344, 349, 350, 352, 				
		};
		
		
		
		int[] finish_by_session = {
			// search
			98,99,100,101,104,  105,106,108,109,110,  111,112,113,114,116,  117,124,129,133,199,  200,207,210,257,261, 
			301,323,329,332,337,  339,364,366, 
			102,103,107,126,130,  279,286,310,
			// Tree
			94,95,96,144,145,  156,173,222,226,230,  235,236,250,255,270,  272,285,297,298,333,
			// Graph
			269,
			// DP
			10,32,44,53,62,  63,64,70,72,85,  87,91,97,115,120,  121,123,132,139,140,  152,174,188,198,213,
			221,256,264,265,276,  300,303,304,309,312,  321,322,338,343,351,  354,357,361,363,368,  375,376,377,  
			// LinkedList
			2,19,21,23,24,   25,61,82,83,86,  92,138,141,142,143,  147,148,160,203,206,  234,237,328,369,
			// Binary Search
			4,29,33,34,35,  50,69,74,81,153,  154,162,167,209,240,  275,278,287,302,315,  349,350,367,374,
			// sort
			56,57,164,179,242,  280,296,324,
			// Backtracking
			17,22,39,40,46,  47,51,52,60,77,  78,79,89,90,93,  131,211,212,216,254,  267,291,320,
			// Two Points
			3,11,15,16,18,  26,27,28,30,42,  75,76,80,88,125,  159,259,283,344,345,  360,
			// Stack, Heap, Queue, Deque
			20,71,84,150,155,  225,232,331,341,
			215,218,239,252,253,  295,347,358,373,378,
			346,			
			// Hashtable
			1,36,49,136,149,  170,187,202,204,205,  217,219,220,243,244,  245,246,247,248,249,  266,288,290,299,311,
			314,325,336,340,356,  359,
			// String
			5,6,8,12,13,  14,38,43,58,67,  68,151,157,158,161,  165,186,214,227,271,  293,
			// Array
			31,41,45,48,54,  55,59,66,73,118,  119,122,128,163,169,  189,228,229,238,268,  277,289,370,
			// Bit Manipulation
			137,190,191,201,231,  260,318,342,371,
			// Math
			7,9,168,171,172,  223,258,263,319,326,  365,
			// Design
			146,208,251,281,284,  348,379,380,
		};
		

		int[] finish_by_session_2 = {
				// Two Points
				3,11,15,16,18,  26,27,28,30,42,  75,76,80,88,125,  159,259,283,344,345,  360,
		};
		
		// by company
		/**************************************************************************************************
		 *  Bloomberg:
		 *  *5(两种方法), *13(注意顺序), 15, *24, *26, 50(考虑细节问题), 56, 69(需考虑越界问题), 79(考虑test case), 
		 *  101(注意对称的概念), **117, *123, 138, 141(faster 从 head.next开始), **146, 151, **157, **158, *172,
		 *  **215, **225, 274, **275, 283, 297
		 * 
		 **************************************************************************************************/
		
		
		
		/**************************************************************************************************************************
		 * Round 3:
		 * 4, 5(两种方法), 10, 22(注意加括号添加的顺序), 25, 29, 31(细节), 32(look up), 43(理解具体的步骤), 45(细节), 48, 50(细节),  
		 * 60, 71(注意细节), 72, 79(注意细节), 80(注意细节), 87, 89(DP方法), 91, 93, 95, 96(注意具体细节), 99(细节), 
		 * 115(细节), 117, 124, 126, 132, 133, 134, 138(细节), 139(细节), 140, 143(细节), 146, 147(细节), 148, 149, 
		 * 154(细节), 156(解释), 157(细节), 158, 162(细节), 163, 170， 188, 
		 * 201, 212, 214(解释), 215, 218, 220(细节), 222(细节), 244, 245, 250,
		 * 251，255, 260(常规做法和位运算做法), 261, 264, 269, 271, 274, 275, 276, 280, 284(细节), 289, 296, 300(细节),
		 * 301, 305, 306, 307, 311, 312, 318, 319, 325(细节), 326, 330, 332, 333(细节), 336, 341, 343(细节), 347, 
		 * 351, 352, 356，357(细节), 358, 365, 370, 
		 * 
		 **************************************************************************************************************************/
		
		/****************************************************************************************************
		 * 使用Arrays.hashCode()来解题: 356
		 * k个字符的处理：hash[256]的应用, 358
		 * matrix[][]如何用queue进行BFS -> 从(row,col)坐标转化为id
		 * 
		 ****************************************************************************************************/
		
		/**************************************************************************************************************************
		 * Round 4:
		 * Search: 109, 114, 133, 261, 301, 332, 364(细节), 366,  &&  126, 286, 310(细节)
		 * 
		 * Graph: 269
		 * 
		 * Tree: 95, 96, 156, 173, 236(细节), 255(两种方法＋细节), 272(两种方法), 333
		 * 
		 * DP: 32(两种做法＋细节), 85(细节), 91(细节), 131, 132, 139, 140, 188, 264(细节), 312, 343, 354, 357, 361(简便方法), 375, 376
		 * 
		 * LinkedList: 25, 138, 141(细节), 143(细节), 147, 237,
		 * 
		 * Binary Search: 29(细节), 69(细节), 162, 270, 275
		 * 
		 * Sort: 56(细节), 164(细节), 179(细节和实现), 296, 324(细节和实现)
		 * 
		 * Backtracking: 60, 79(细节), 93, 212, 254(细节)
		 * 
		 * Two Points: 80, 283(简便方法), 360
		 * 
		 * Stack, Heap, Queue: 331(细节), 341, && 215(实现和细节), 218, 253, 347, 358, 373, 378
		 * 
		 * Hashtable: 36(简便方法), 149(实现和细节), 170, 219(两种方法), 220(细节), 245, 248, 249(细节), 290(只用一个map), 311, 325(细节), 336, 347, 356
		 * 
		 * String: 5, 8(注意细节), 12, 13, 43(细节和实现), 68, 157, 158, 214, 227, 271
		 * 
		 * Array: 31(细节), 41, 48, 54(细节), 163, 228(细节和实现), 277(细节)
		 * 
		 * Bit Manipulation: 201, 260, 318, 371
		 * 
		 * Math: 168(细节),172, 258, 319, 365
		 * 
		 * Design: 146, 251, 281, 284, 379, 380
		 * 
		 **************************************************************************************************************************/
		
		
		
		int[] finish = finish_by_session;
		
		Arrays.sort(finish);
		int preBase = 0;
		System.out.println("number = " + finish.length);
		
		for(int i = 0; i < finish.length; i++){
			if(finish[i] / 10 != preBase){
				preBase = finish[i] / 10;
				System.out.println();
			}
			System.out.print(finish[i] + ", ");
		}
	}
	
	public int[] func(){
		return new int[]{-1, -1};
	}
}

class UndirectedGraphNode {
	int label;
	List<UndirectedGraphNode> neighbors;
	UndirectedGraphNode(int x) { 
		label = x; 
		neighbors = new ArrayList<UndirectedGraphNode>(); 
	}
}
