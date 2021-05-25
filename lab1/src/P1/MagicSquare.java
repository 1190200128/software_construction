package p1;
import   java.io.BufferedReader ;
import java.io.File;
import   java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class MagicSquare {

	public static void main(String[] args) {
		String [] text = {"D:\\ZHAN\\大二下\\软件构造实验\\lab1\\Spring2021_HITCS_SC_Lab1-master\\p1\\1.txt",
				          "D:\\ZHAN\\大二下\\软件构造实验\\lab1\\Spring2021_HITCS_SC_Lab1-master\\P1\\2.txt",
				          "D:\\ZHAN\\大二下\\软件构造实验\\lab1\\Spring2021_HITCS_SC_Lab1-master\\P1\\3.txt",
				          "D:\\ZHAN\\大二下\\软件构造实验\\lab1\\Spring2021_HITCS_SC_Lab1-master\\P1\\4.txt",
				          "D:\\ZHAN\\大二下\\软件构造实验\\lab1\\Spring2021_HITCS_SC_Lab1-master\\P1\\5.txt",
				          "D:\\ZHAN\\大二下\\软件构造实验\\lab1\\Spring2021_HITCS_SC_Lab1-master\\P1\\6.txt"
		                 };
		for (int i = 0;i < 5;i++)
		{
			boolean result1 = isLegalMagicSquare(text[i]);//判断是否为魔方
			if(result1 == true)
			{
				System.out.println("true,是Magic Square");
			}
			else {
				System.out.println("false,不是Magic Square");
			}
			
		}
		System.out.println("开始测试generateMagicSquare函数，请输入n:");
		Scanner s =new Scanner (System.in);
		int n =s.nextInt();
		boolean result2 =generateMagicSquare(n);//判断生成的矩阵是否为魔方
		s.close();
		if(result2 ==false)
		{
			System.out.println("false");
		}
		else {
			boolean result3 = isLegalMagicSquare(text[5]);
			if(result3 == true)
			{
				System.out.println(6+":true,是Magic Square");
			}
			else {
				System.out.println(6+":false,不是Magic Square");
			}
			
		}

	}
	
	private static boolean  isLegalMagicSquare(String address){
	    FileReader reader =null;
	    BufferedReader bufreader = null;
	    int[][] array = null;
	    int  sumdiagonal = 0;
	   boolean flag1 = false;
	    boolean flag2 = false;
	    try {
	    	String str;
	    	reader = new FileReader(address);
	    	bufreader = new BufferedReader(reader);
	    	List<String> strList = new ArrayList<String>();
	    	while((str = bufreader.readLine())!=null)
	    	{
	    		strList.add(str);
	    	}
	    	int lineNum = strList.size();
	    	String s =  strList.get(0);
	    	int columnNum = s.split("\t").length;
	    	array = new int[lineNum][columnNum];
	    	int [] sumlines = new int[lineNum];
	    	int [] sumcols = new int [columnNum];
	    	if(columnNum!=lineNum )
	    	{	 
	    		return false;
	    	}
	    	int count = 0;
	    	for(String linestr : strList)
	    	{
	    		try {
	    		String[] strs = linestr.split("\t");
	    		for(int i = 0;i<columnNum;i++)
	    		{
	    			array[count][i] = Integer.valueOf(strs[i]);
	    			
	    			if(array[count][i]<=0)
	    			{
	    	    		return false;
	    			}
	    			sumlines[count]=sumlines[count]+array[count][i];
	    			
	    		}
	    		
	    		if((count >=1) && (sumlines[count] != sumlines[count - 1]))
    			{
    				return false;
    			}
	    		count++;
	    		}
	    		catch (Exception e) {
	    			return false;
	    			
	    		}
	    	}
	    	flag1 = true;
	    	for (int i= 0;i<columnNum;i++)
	    	{
	    		for (int j = 0;j<lineNum;j++)
	    		{
	    			sumcols[i]=sumcols[i]+array[i][j];
	    		}
	    		if((i >= 1) && (sumcols[i] != sumcols [i-1]))
	    		{
    				return false;
	    		}
	    	}
	    	flag2 = true;
	    	if ((flag1 ==true )&&(flag2 == true))
	    	{
	    		for(int i = 0;i<lineNum;i++)
	    		{
	    			sumdiagonal = sumdiagonal + array [i][i];
	    		}
	    		if(sumdiagonal != sumcols[0])
	    		{
    				return false;
	    		}
	    	}
	    	
	    	
	    }
	   catch (Exception e) {
			return false;
	   }finally {
		   try {
		    	if(reader != null)
					reader.close();
			} catch (Exception e) {
				return false;
				
			}
		    try {
		    	if(bufreader != null)
		    		bufreader.close();
			} catch (Exception e) {
				return false;
				
			}
		   
	}
	    
	    return true;
	       
	}
	public static boolean generateMagicSquare(int n) {
		int magic[][] = new int[n][n];//矩阵数组
		int row = 0, col = n / 2, i, j, square = n * n;
		if (n%2==0)
		{
			return false;//判断n是否为偶数
		}
		if (n<=0)
		{
			return false;//判断n是否小于等于0
		}
		for (i = 1; i <= square; i++) {
		magic[row][col] = i;//给矩阵赋值	
		if (i % n == 0)//如果对角线上的一个元素被赋值，就开始下一行的赋值
		row++;
		else {
		if (row == 0)
		row = n - 1;//如果到第一行了就到最后一行
		else
		row--;//行数慢慢减少
		if (col == (n - 1))//如果到了最后一列就去第一列
		col = 0;
		else
		col++;
		}
		}
		for (i = 0; i < n; i++) {
		for (j = 0; j < n; j++)
		System.out.print(magic[i][j] + "\t");//将生成的矩阵赋值到二维数组
		System.out.println();
		}
		/*
		 * 将生成的矩阵读入到6.txt中
		 */
		 File file = new File("D:\\ZHAN\\大二下\\软件构造实验\\lab1\\Spring2021_HITCS_SC_Lab1-master\\P1\\6.txt");  
		 try  {
	        FileWriter out = new FileWriter(file);  
	    	 for(int a=0;a<n;a++){
		            for(int b=0;b<n;b++){
		                out.write(magic[a][b]+"\t");
		            }
		            out.write("\r\n");
		        }
		        out.close();
	     }
	     catch(Exception e)
	     {
	    	 return false;
	     }
	        
		return true;
		}


}

