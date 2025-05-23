import java.util.Random;


public class Algorithm_Sort {
	
	/************************ Select Sort ************************/
	// 时间O(n^2)，空间O(1)，稳定
	void select_sort(int[] p){
		for (int i = 0; i < p.length; i++) {
            for (int j = i + 1; j < p.length; j++) {
                if (p[i] > p[j]) {
                    swap(p, i, j);
                }
            }
        }
	}

	/*********************** Insert Sort **************************/
	// 时间O(n^2)，空间O(1)，稳定
	void insert_sort(int[] p) {      
		int j, key;
		for (int i = 1; i < p.length; i++) {
			j = i - 1;
			key = p[i];
			while (j >= 0 && p[j] > key) {
				p[j + 1] = p[j];
				j--;
			}
			p[j + 1] = key;
		}
	}

	
	/*********************** Bubble Sort **************************/
	// 时间O(n^2)，空间O(1)，稳定
	void bubble_sort(int[] p) {      
		for (int i = 0; i < p.length - 1; i++) {
			for (int j = p.length - 1; j >= i + 1; j--) {
				if (p[j] < p[j - 1]) {
					swap(p, j-1, j);
				}
			}
		}
	}
	
	
	/*********************** Merge Sort **************************/
	// 时间稳定在O(n*logn)，空间O(n)，稳定； 但如果n足够大，开辟space O(n)费时
	// Divide and Conquer; 先局部有序，再整体有序
	public void recursive_merge_sort(int[] p, int x, int y)
	{     
		if (x < y)
		{
			int m = (x+y) / 2;
			recursive_merge_sort(p, x, m);
			recursive_merge_sort(p, m+1, y);
			merge_sort(p, x, m, y);
		}
	}
	
	public void merge_sort(int[] p, int x, int m, int y)
	{
		int len1 = m-x+1;
		int len2 = y-(m+1)+1;
		int[] L = new int[len1];
		int[] R = new int[len2];
		
		for (int i = 0; i < len1; i++) 
		{
			L[i] = p[x+i];
		}
		
		for (int j = 0; j < len2; j++) 
		{
			R[j] = p[m+1+j];
		}
		
		int index1 = 0, index2 = 0;
		
		for (int k = x; k <= y; k++) 
		{
			if (index1 < len1 && index2 < len2)
			{
				p[k] = (L[index1] < R[index2]) ? L[index1++] : R[index2++];
			}
			else if (index1 < len1)
			{
				p[k] = L[index1++];
			}
			else 
			{
				p[k] = R[index2++];
			}
		}
	}

	
	/*********************** Random Quick Sort **************************/
	// 时间最理想O(n*logn)，最差O(n^2)，空间O(logn)，不稳定; worse case 是整个序列倒序，此时快速排序退化成冒泡排序
	// Divide and Conquer； 先整体有序，再局部有序； 如果不是worse case，其速度快于merge sort
	public void quickSort(int[] p, int x, int y) 
	{
		if (x < y) 
		{
			int position = randomized_partition(p, x, y); // 也可以用 random_partition
			quickSort(p, x, position - 1);                // 注意这里是position - 1 !!!
			quickSort(p, position + 1, y);
		}
	}
	
	public int randomized_partition(int[] p, int x, int y) 
	{
		int len = y - x + 1;
		int offset = new Random().nextInt(len); // 0~n-1间的随机数
		int temp = p[y];
		p[y] = p[x + offset];
		p[x + offset] = temp;
		return partition(p, x, y);
	}
	
	public int partition(int[] p, int left, int right) 
	{
		int index = left;
		int pivot = p[right];
		
		for (int i = left; i < right; i++) 
		{
			if (p[i] <= pivot) 
			{
				int temp = p[index];
				p[index] = p[i];
				p[i] = temp;
				index++;
			}
		}
		
		int temp = p[index];
		p[index] = p[right];
		p[right] = temp;
		return index;
	}
	
	
	/*********************** Quick Sort **************************/
	// 时间最理想O(n*logn)，最差O(n^2)，空间O(logn)，不稳定; worse case 是整个序列倒序，此时快速排序退化成冒泡排序
	// Divide and Conquer； 先整体有序，再局部有序； 如果不是worse case，其速度快于merge sort
	public void quickSort2(int[] x, int left, int right){   
		if(left >= right) return;                         
		int i = left, j = right;                  
		double pivot = (x[left]+x[right])/2.0;  // pivot必须用double
		while(i < j){
			while(i < right && x[i] < pivot) i++;  // 右边界的判定
			while(j > left && x[j] >= pivot) j--;  // 左边界的判定
			if(i < j){
				int temp = x[i];
				x[i] = x[j];
				x[j] = temp;
			}
		}
		if(j > left)                         // 分割
			quickSort2(x, left, j);
		if(right > j+1)
			quickSort2(x, j+1, right);		
	}
	
	
	/*********************** Heap Sort **************************/
	
	public void heapSort(int[] nums)      // 时间O(n*logn)，空间O(1)，不稳定
	{
		buildMaxHeap(nums);     // 构建堆,除了叶子外，其他叶子以上部分已经完成排序，
		int n = nums.length;    // 按照从小到大，至上而下排序
		
		for (int i = n-1; i >= 1; i--)    // 叶子无法排序，因此每次将位于root的最大的节点, O(n)
		{
			swap(nums, 0, i);             // 和最后的节点替换，然后重新进行排序
			heapify(nums, 0, i);
		}
	}

	public void buildMaxHeap(int[] nums)   // 仅完成叶子以上节点从大到小排序, O(nlogn)
	{
		int n = nums.length; 
		
		for (int i = n/2-1; i >= 0; i--)
		{
			heapify(nums, i, n);
		}
	}

	public void heapify(int[] nums, int parentId, int maxIndex)   // O(logn)
	{
		int leftChildId = 2 * parentId + 1;              // 左孩子的下标（如果存在的话）
		int rightChildId = 2 * parentId + 2;             // 右孩子的下标（如果存在的话）
		int largestElemId = parentId;   
		
		if (leftChildId < maxIndex && nums[leftChildId] > nums[parentId]) 
		{
			largestElemId = leftChildId;
		}
		
		if (rightChildId < maxIndex && nums[rightChildId] > nums[largestElemId]) 
		{
			largestElemId = rightChildId;
		}
		
		if (largestElemId != parentId) 
		{
			swap(nums, largestElemId, parentId);
			heapify(nums, largestElemId, maxIndex);
		}
	}
	
	public void swap(int[] x, int i, int j)
	{
		int temp = x[i];
		x[i] = x[j];
		x[j] = temp;
	}
	
	
	
	/***********************   count sort   **************************/	
	// 时间复杂度为O(n+k), 其中k为排序数的范围； 空间复杂度为O(n+k)
	// 限制:
	// (1). 必须知道待排序的数组的最大值range
	// (2). 待排序数组中的元素必须为非负值

	void countSort(int[] nums, int range) {
		int[] countArray = new int[range + 1];
		int[] tempArray = new int[nums.length];      // 与数组p一样长的临时数组
		
		for (int num : nums){
			countArray[num]++;
		}
		
		for (int i = 1; i < countArray.length; i++){
			countArray[i] += countArray[i-1];
		}
		
		for(int num : nums) {
            int pos = countArray[num] - 1;
            tempArray[pos] = num;
            countArray[num]--;
        }
		
		System.arraycopy(tempArray, 0, nums, 0, nums.length);
	}

	
	

    /***********************   radix sort   **************************/	
    // 基于count_sort的radix sort
	public void radixSort(int[] nums, int radix, int bit_num){    // array为待排序数; radix代表基数，如10进制radix就为10;
        int len = nums.length;                                    // bit_num代表排序元素的最大位数;
        int[] tempArray = new int[len];                           // 用于暂存元素 
        int divide = 1;
        
        for(int i = 0; i < bit_num; i++){
            int[] count = new int[radix];                         // 用于计数排序
            
            for(int j = 0; j < len; j++){
                int value = (nums[j] / divide) % radix;
                count[value]++;
            }
            
            for(int j = 1; j < radix; j++){
                count[j] += count[j - 1];
            }
            
            for(int j = len - 1; j >= 0; j--){
                int value = (nums[j] / divide) % radix;
                int position = count[value] - 1;
                tempArray[position] = nums[j];
                count[value]--;
            }
            
            divide = divide * radix;
            System.arraycopy(tempArray, 0, nums, 0, len);
        }
    }
	
	
	
	/***********************   main   **************************/
	
	public static void main(String[] args){
		Algorithm_Sort t = new Algorithm_Sort();
		
		int [] array = {2,5,3,3,3,2,35,2,1,36,6,4,9,6,7}; 
		
		int [] array0 = new int[array.length];
		for(int i = 0; i < array.length; ++i)
			array0[i] = array[i];
		t.select_sort(array0);
		System.out.print("Select Sort: ");
		for(int i = 0; i < array0.length; ++i)
			System.out.print(array0[i] + ", ");
		System.out.println();
		
		int [] array1 = new int[array.length];
		for(int i = 0; i < array.length; ++i)
			array1[i] = array[i];
		t.insert_sort(array1);
		System.out.print("Insert Sort: ");
		for(int i = 0; i < array1.length; ++i)
			System.out.print(array1[i] + ", ");
		System.out.println();
		
		int [] array2 = new int[array.length];
		for(int i = 0; i < array.length; ++i)
			array2[i] = array[i];
		t.bubble_sort(array2);
		System.out.print("Bubble Sort: ");
		for(int i = 0; i < array2.length; ++i)
			System.out.print(array2[i] + ", ");
		System.out.println();
		
		int [] array3 = new int[array.length];
		for(int i = 0; i < array.length; ++i)
			array3[i] = array[i];
		t.recursive_merge_sort(array3, 0, array3.length-1);
		System.out.print("Merge Sort:  ");
		for(int i = 0; i < array3.length; ++i)
			System.out.print(array3[i] + ", ");
		System.out.println();
		
		int [] array4 = new int[array.length];
		for(int i = 0; i < array.length; ++i)
			array4[i] = array[i];
		t.quickSort(array4, 0, array4.length-1);
		System.out.print("Quick Sort:  ");
		for(int i = 0; i < array4.length; ++i)
			System.out.print(array4[i] + ", ");
		System.out.println();
		
		int [] array5 = new int[array.length];
		for(int i = 0; i < array.length; ++i)
			array5[i] = array[i];
		t.quickSort(array5, 0, array5.length-1);
		System.out.print("Quick Sort2:  ");
		for(int i = 0; i < array5.length; ++i)
			System.out.print(array5[i] + ", ");
		System.out.println();
		
		int [] array6 = new int[array.length];
		for(int i = 0; i < array.length; ++i)
			array6[i] = array[i];
		t.heapSort(array6);
		System.out.print("Heap Sort:   ");
		for(int i = 0; i < array6.length; ++i)
			System.out.print(array6[i] + ", ");
		System.out.println();
		
		int [] array7 = new int[array.length];
		for(int i = 0; i < array.length; ++i)
			array7[i] = array[i];
		t.countSort(array7, 36);
		System.out.print("Count Sort:  ");
		for(int i = 0; i < array7.length; ++i)
			System.out.print(array7[i] + ", ");
		System.out.println();
		
		int [] array8 = new int[array.length];
		for(int i = 0; i < array.length; ++i)
			array8[i] = array[i];
		t.radixSort(array8, 10, 2);
		System.out.print("Radix Sort:  ");
		for(int i = 0; i < array8.length; ++i)
			System.out.print(array8[i] + ", ");
		System.out.println();
	}	
}
