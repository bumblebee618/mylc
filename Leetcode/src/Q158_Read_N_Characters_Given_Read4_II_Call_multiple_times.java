/*******
 * 
The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
	The read function may be called multiple times.

 * */

public class Q158_Read_N_Characters_Given_Read4_II_Call_multiple_times {
	/***************************************************************************
	 * 此题是157的follow up, 需要将currentReadByte，currentHaveRead， tempBuf全局化
	 * 
	 ***************************************************************************/
	
	private int curTotalReadNum = 0;     // 设置成全局变量，防止上一次读取read4，没有全部用掉
    private int curHaveReadNum = 0;      // 偏移位，用于记录已经读取到了哪一位置，用于和currentTotalRead进行比较 
    private char[] readBuf = new char[4];  
    
    public int read(char[] buf, int n) {
        int alreadyReadNum = 0;
        
        while (alreadyReadNum < n)
        {
            if (curHaveReadNum == curTotalReadNum)
            {
                curHaveReadNum = 0;
                curTotalReadNum = read4(readBuf);
            }
            
            if (curTotalReadNum == 0)
            {
                break;
            }
            
            int len = Math.min(curTotalReadNum-curHaveReadNum, n-alreadyReadNum);
            
            for (int i = 0; i < len; i++)
            {
                buf[alreadyReadNum+i] = readBuf[curHaveReadNum+i];
            }
            
            curHaveReadNum += len;
            alreadyReadNum += len;
        }
        
        return alreadyReadNum;
    }

    
    
    
    
    
    
    /***************** function *********************/
    
    public int read4(char[] tempBuf){
    	return 1;
    }
}
