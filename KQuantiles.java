import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class KQuantiles {
	static int[] inputArray;
	public static void main(String[] args) {
		
		List<Integer> result = new ArrayList<Integer>(); 
		try
		{
		FileReader fr = new FileReader("Input3.txt");
		BufferedReader br = new BufferedReader(fr); 
		String s;
		String[] split;
		
		s = br.readLine();
		
		split = s.split(" ");
				
		int n = Integer.parseInt(split[0]);
		int k =Integer.parseInt(split[1]);
		
		s = br.readLine();
		split = s.split(" ");
		inputArray = new int[split.length];
		for(int i=0; i<split.length; i++)
		{
			inputArray[i] = Integer.parseInt(split[i]);
		}
		br.close();
		fr.close();
		
		result = kthQuantiles(0, n, 0, k-1, k);
		
		for(Integer i: result)
		{
			System.out.println(i + "\t");
		}
		
		}
		catch(FileNotFoundException fne)
		{
			fne.printStackTrace();
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
		
	}
	
	public static List<Integer> kthQuantiles(int p, int r, int i, int j, int k)
	{
		int n = inputArray.length;
		int m = (int) Math.floor(((double)i+j)/2);
		int q = (int) Math.floor((double)m*((double)n/k));
		List<Integer> left = new ArrayList<Integer>();
		List<Integer> right = new ArrayList<Integer>();
		List<Integer> retrunArray = new ArrayList<Integer>();	
				
		q = q-p+1;
		q = select(p, r ,q);
		q=  partition( p, r, q);
		
		if(i<m)
			left = kthQuantiles(p, q-1, i, m-1, k);
		else if(m<j)
			right = kthQuantiles(q+1, r, i+1, j, k);
		
		for(Integer a: left)
		{
			retrunArray.add(a);
		}
		retrunArray.add(new Integer(inputArray[q]));
		
		for(Integer a:right)
		{
			retrunArray.add(a);
		}
	
		return retrunArray;
		
		
	}

	public static int partition(int p, int r, int q) {
		int x = inputArray[q];
		int i = p-1;
		for(int j=p; j<q-1; j++)
		{
			if(inputArray[j]<= x)
			{
				i++;
				swap(i, j);
			}
		}
		swap(i+1, r-1);
		return i+1;
	}

	public static void swap(int i, int j) {
		int tmp = inputArray[i];
		
		inputArray[i] = inputArray[j];
		inputArray[j] = tmp;		
		
	}

	public static int select(int p, int r, int i) {
				
		if(p==r)
			return p;
		int q= partition(p,r,i);
		int k = q-p+1;
		if(i == k)
			return q;
		else if(q<k)
			return select(p, q-1,i);
		else
			return select(q+1,r,i-k);
		}

}