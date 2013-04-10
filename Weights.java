import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Weights {
	public static void main(String args[]) throws FileNotFoundException{
		HashMap hm = new HashMap();
		int n;
		File file = new File("input2.txt");
        Scanner s = new Scanner(file);
		n= s.nextInt();
		while(n!=0){
			//Clearing the Hashmap for every iteration
			hm.clear();
			//Reading the Weights
			int a[] = new int[n];
			for(int i = 0; i < n;i++){
				a[i] = s.nextInt();
			}
			
			//Adding the first Value
			hm.put(0,0);
			for(int i = 0;i < n;i++){
				ArrayList al =new ArrayList(hm.values());
				for (Object val : al) {
					//Calculating the possibilities of weights by adding the current weight and substracting also 
					 int v = (Integer)val;
			         hm.put(v+a[i], v+a[i]);
			        if(v-a[i]>0)
			        	hm.put(v-a[i], v-a[i]);
			        else
			        	hm.put(a[i]-v, a[i]-v);
			    }
			}
			//As 0 is not counted as the ouput excluding 0 we are printing the list
			System.out.println("No of possible Weights: "+(hm.size()-1));
			n= s.nextInt();
		}
	}
}
